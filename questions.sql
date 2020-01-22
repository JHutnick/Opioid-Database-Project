USE opioidTracker;

SELECT * FROM DrugCost;
SELECT * FROM DrugCount;
SELECT * FROM DrugData;
SELECT * FROM DeathData;
SELECT * FROM County;
SELECT * FROM BeneficiaryInfo;
SELECT * FROM ClaimInfo;
SELECT * FROM Doctor;
SELECT * FROM SaveDoctor;
SELECT * FROM Users;


#1) What state has the highest total opioid claim count divided by the total number of doctors who prescribed opioids in that state?
SELECT 
    State,
    SUM(claiminfo.Opioidclaim) AS opioid_sum,
    COUNT(*) AS DR_CNT,
    SUM(claiminfo.Opioidclaim) / COUNT(*) as ratio
FROM
    doctor
        INNER JOIN
    claiminfo ON Doctor.DoctorId = ClaimInfo.DoctorId
GROUP BY State
ORDER BY ratio desc;

#2) List out the top 500 of doctors with the most opioid claims counts?  
SELECT 
    Doctor.DoctorId,
    Doctor.FirstName,
    Doctor.LastName,
	Doctor.City,
    Doctor.State,
    ClaimInfo.OpioidClaim
FROM
    Doctor
        INNER JOIN
    ClaimInfo ON Doctor.DoctorId = ClaimInfo.DoctorId
GROUP BY Doctor.DoctorId
ORDER BY ClaimInfo.OpioidClaim DESC
LIMIT 500;


#3) List out the top 500 of all doctor who supplied the most drugs in terms of opioid drug cost and opioid claim count?  (multiply two numbers together)
SELECT 
    doctor.DoctorId,
    City,
    State,
    SUM(drugcost.OpioidDrugCost + claiminfo.OpioidClaim) AS total_
FROM
    doctor
        INNER JOIN
    drugcost ON doctor.DoctorId = drugcost.DoctorId
        INNER JOIN
    claiminfo ON doctor.DoctorId = claiminfo.DoctorId
GROUP BY doctor.DoctorId
ORDER BY total_ DESC
LIMIT 500;

#4) List out the top 500 of all doctors who have the highest antibiotic claim count.  What is the correlation between the doctors in question 2?
SELECT 
    Doctor.DoctorId,
    Doctor.FirstName,
    Doctor.LastName,
	Doctor.City,
    Doctor.State,
    ClaimInfo.AntiClaim
FROM
    Doctor
        INNER JOIN
    ClaimInfo ON Doctor.DoctorId = ClaimInfo.DoctorId
GROUP BY Doctor.DoctorId
ORDER BY ClaimInfo.AntiClaim DESC
LIMIT 500;

#5) Top 100 counties that have the highest drug related death rates?
SELECT *
FROM deathdata
ORDER BY deathdata.DeathRateRangeLow desc, deathdata.Population desc
LIMIT 100;

#6) What is the correlation on a state level between the average age category for beneficiaries and the opioid day supply?
SELECT 
    Doctor.DoctorId,
    Doctor.FirstName,
    Doctor.LastName,
    Doctor.City,
    Doctor.State,
    BeneficiaryInfo.AvgAgeBen,
    DrugCount.OpioidSupply
FROM
    Doctor
        LEFT OUTER JOIN
    BeneficiaryInfo ON Doctor.DoctorId = BeneficiaryInfo.DoctorId
        LEFT OUTER JOIN
    DrugCount ON Doctor.DoctorId = DrugCount.DoctorId;
    
#7) On a state by state basis, what is the average in claim counts between the different claim counts (Total vs Brand vs Generic vs Opioid).  What about for the Drug costs?
SELECT 
    State,
    SUM(claiminfo.BrandClaimCount) AS Brand,
    Sum(claiminfo.GenericClaim) AS Generic,
    sum(claiminfo.OpioidClaim) AS Opioid,
    sum(claiminfo.ErOpioidClaim) AS ER_Opioid
FROM
    doctor
        INNER JOIN
    claiminfo ON Doctor.DoctorId = ClaimInfo.DoctorId
GROUP BY State
ORDER BY State ASC;

SELECT 
    State,
    round(SUM(drugcost.BrandDrugCost),2) AS Brand,
    round(SUM(drugcost.GenericDrugCost),2) AS Generic,
    round(SUM(drugcost.OpioidDrugCost),2) AS Opioid,
    round(SUM(drugcost.ErOpioidDrugCost),2) AS ER_Opioid
FROM
    doctor
        INNER JOIN
    drugcost ON Doctor.DoctorId = drugcost.DoctorId
GROUP BY State
ORDER BY State ASC;

#8) What are the top 100 counties that have the highest death rate?  
SELECT 
    DeathData.County,
    SUM(DeathData.DeathRateRangeLow) AS DeathRate
FROM
    DeathData
GROUP BY DeathData.County
ORDER BY DeathRate DESC
LIMIT 100;

#9) Looking at the overall data, what is the correlation between the opioid beneficiary count and the beneficiary count > 65?

SELECT 
    FORMAT((COUNT(*) * SUM(OpiBenCount * BenAgeG65) - 
    SUM(OpiBenCount) * SUM(BenAgeG65)) / (SQRT(COUNT(*) * 
    SUM(OpiBenCount * OpiBenCount) - SUM(OpiBenCount) * 
    SUM(OpiBenCount)) * SQRT(COUNT(*) * SUM(BenAgeG65 * BenAgeG65) - 
    SUM(BenAgeG65) * SUM(BenAgeG65))),
        2) AS P_corr
FROM
    beneficiaryinfo;

# Based off this calculation
-- SELECT N, Slope, avgY - slope*avgX AS Intercept, Correlation, CoeffOfReg
-- FROM (
--   SELECT                                     
--     N, avgX, avgY, slope, intercept, Correlation,
--     FORMAT( 1 - SUM((BenAgeG65 - intercept - slope*OpiBenCount)*(BenAgeG65 - intercept - slope*OpiBenCount))/ ((N-1)*varY), 5 ) AS CoeffOfReg
--   FROM beneficiaryinfo AS t2
--   JOIN (
--     SELECT N, avgX, avgY, varY, slope, Correlation, avgY - slope*avgX AS intercept
--     FROM (
--       SELECT N, avgX, avgY, varY, 
--         FORMAT(( N*sumXY - sumX*sumY ) / ( N*sumsqX - sumX*sumX ), 5 ) AS slope,
--         FORMAT(( sumXY - n*avgX*avgY ) / ( (N-1) * SQRT(varX) * SQRT(varY)), 5 ) AS Correlation
--       FROM (
--         SELECT                               
--           COUNT(OpiBenCount)    AS N,
--           AVG(OpiBenCount)      AS avgX,
--           SUM(OpiBenCount)      AS sumX,
--           SUM(OpiBenCount*OpiBenCount)    AS sumsqX,
--           VAR_SAMP(OpiBenCount) AS varX,
--           AVG(BenAgeG65)      AS avgY,
--           SUM(BenAgeG65)      AS sumY,
--           SUM(BenAgeG65*BenAgeG65)    AS sumsqY,
--           VAR_SAMP(BenAgeG65) AS varY,
--           SUM(OpiBenCount*BenAgeG65)    AS sumXY
--         FROM beneficiaryinfo
--       ) AS sums
--     ) AS calc
--   ) stats
-- ) combined;
-- 


#10) Top 100 counties that have the highest opioid day supply?

SELECT 
    County.CountyName, County.State, SUM(DrugCount.OpioidSupply) AS TotalCountySupply
FROM
    County
        JOIN 
    DrugCount
        JOIN
    Doctor ON Doctor.DoctorId = DrugCount.DoctorId
        AND Doctor.Zip = County.Zip
GROUP BY County.CountyName, County.State
ORDER BY TotalCountySupply DESC
LIMIT 100;



