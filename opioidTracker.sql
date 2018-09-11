CREATE SCHEMA IF NOT EXISTS opioidTracker;
USE opioidTracker;

DROP TABLE IF EXISTS DrugCost;
DROP TABLE IF EXISTS DrugCount;
DROP TABLE IF EXISTS DrugData;
DROP TABLE IF EXISTS DeathData;
DROP TABLE IF EXISTS County;
DROP TABLE IF EXISTS BeneficiaryInfo;
DROP TABLE IF EXISTS ClaimInfo;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS SaveDoctor;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    UserName VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Phone VARCHAR(255),
    
    CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);

CREATE TABLE SaveDoctor(
    UserName VARCHAR(255) NOT NULL,
    DoctorId INT NOT NULL,
    Password VARCHAR(255) NOT NULL,
    
    CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName),
    CONSTRAINT fk_SaveDoctor_Username FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Doctor (
    DoctorId INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    Credentials VARCHAR(255),
    Street1 VARCHAR(255) NOT NULL,
	City VARCHAR(255) NOT NULL,
    Zip INT NOT NULL,
	State VARCHAR(255) NOT NULL,
    Speciality VARCHAR(255) NOT NULL,
    
    CONSTRAINT pk_Doctor_DoctorId PRIMARY KEY (DoctorId)
);

CREATE TABLE ClaimInfo (
    DoctorId INT NOT NULL,
    TotalClaimCount INT,
    30DayFill INT,
    BrandClaimCount INT,
    GenericClaim INT,
    OpioidClaim INT,
    ErOpioidClaim INT,
    AntiClaim INT,

    CONSTRAINT pk_ClaimInfo_DoctorId PRIMARY KEY (DoctorId),
	CONSTRAINT fk_ClaimInfo_DoctorId FOREIGN KEY (DoctorId)
    REFERENCES Doctor(DoctorId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE BeneficiaryInfo (
    DoctorId INT NOT NULL,
    BeneCount INT,
    OpiBenCount INT,
    ErOpiBenCount INT,
    AntibioBenCount INT,
    BenAgeL65 INT,
    BenAgeG65 DOUBLE,
	AvgAgeBen INT, 
    BenF INT,
    BenM INT,
    BenNDual INT,
    BenDual INT,
    BenAvgRisk DECIMAL,
    
    CONSTRAINT pk_BeneficiaryInfo_DoctorId PRIMARY KEY (DoctorId),
    CONSTRAINT fk_BeneficiaryInfo_DoctorId FOREIGN KEY (DoctorId)
    REFERENCES Doctor(DoctorId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE County (
    Zip INT NOT NULL,
    City VARCHAR(255) NOT NULL,
    State VARCHAR(8) NOT NULL,
    CountyName VARCHAR(255) NOT NULL,
    
    
    CONSTRAINT pk_County_Zip PRIMARY KEY (Zip)
);

CREATE TABLE DeathData (
	Year INT NOT NULL,
    Population BIGINT,
    DeathRateRangeLow INT, 
    DeathRateRangeHigh INT, 
	County VARCHAR(255) NOT NULL,
    State VARCHAR(8) NOT NULL,
    
    CONSTRAINT pk_DeathData_CountyState PRIMARY KEY (County,State)
    );

CREATE TABLE DrugData (
    DoctorId INT NOT NULL,
    TotalDaySupply INT,
    OpioidPrescriberRate DOUBLE,
    ErOpioidRate DECIMAL,
    
    CONSTRAINT pk_DrugData_DoctorId PRIMARY KEY (DoctorId),
    CONSTRAINT fk_DrugData_DoctorId FOREIGN KEY (DoctorId)
    REFERENCES Doctor(DoctorId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DrugCount (
DoctorId INT NOT NULL,
OpioidSupply INT,
ErSupply INT,
    
    CONSTRAINT pk_DrugCount_DoctorId PRIMARY KEY (DoctorId),
    CONSTRAINT fk_DrugCount_DoctorId FOREIGN KEY (DoctorId)
    REFERENCES DrugData(DoctorId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DrugCost (
    DoctorId INT NOT NULL,
    TotalDrugCost DOUBLE,
    BrandDrugCost DOUBLE,
    GenericDrugCost DOUBLE,
    OpioidDrugCost DOUBLE,
    ErOpioidDrugCost DOUBLE,
    AntiDrugCost DECIMAL,
    
    CONSTRAINT pk_DrugCost_DoctorId PRIMARY KEY (DoctorId),
	CONSTRAINT fk_DrugCost_DoctorId FOREIGN KEY (DoctorId)
    REFERENCES DrugData(DoctorId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

LOAD DATA INFILE '/tmp/Zipcodes.txt' INTO TABLE County
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/Death.csv' INTO TABLE DeathData
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n';
  
  LOAD DATA INFILE '/tmp/Doctor_1.csv' INTO TABLE Doctor
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  LOAD DATA INFILE '/tmp/DrugInfo.csv' INTO TABLE DrugData
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  LOAD DATA INFILE '/tmp/CostData.csv' INTO TABLE DrugCost
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  LOAD DATA INFILE '/tmp/BenInfo.csv' INTO TABLE BeneficiaryInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  LOAD DATA INFILE '/tmp/DrugCount.csv' INTO TABLE DrugCount
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
   LOAD DATA INFILE '/tmp/CountData.csv' INTO TABLE ClaimInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;