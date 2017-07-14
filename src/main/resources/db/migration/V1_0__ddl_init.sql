CREATE TABLE recommendation (
  ID int(11) NOT NULL AUTO_INCREMENT,
  RECOMMENDER_ID int(11) NOT NULL,
  COMPANY_ID int(11) NOT NULL,
  START_PRICE double NOT NULL,
  TARGET_PRICE double DEFAULT NULL,
  UPSIDE double DEFAULT NULL,
  DURATION bigint(20) DEFAULT NULL,
  RECOMMENDATION_STATUS varchar(20) NOT NULL,
  SCORE double NOT NULL,
  IS_ACTIVE TINYINT(1) NOT NULL,
  CREATED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE company (
  ID int(11) NOT NULL AUTO_INCREMENT,
  PAID_UP_VALUE int(11) NOT NULL,
  ISIN_NUMBER varchar(20) NOT NULL,
  SYMBOL varchar(20) NOT NULL,
  NAME_OF_COMPANY TINYBLOB NOT NULL,
  SERIES varchar(10) NOT NULL,
  MARKET_LOT int(11) NOT NULL,
  FACE_VALUE int(11) NOT NULL,
  CREATED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE recommender (
  ID int(11) NOT NULL AUTO_INCREMENT,
  RECOMMENDED_BY_ID int(11) NOT NULL,
  FB_ID varchar(20) DEFAULT NULL,
  BROKER_NAME TINYBLOB DEFAULT NULL,
  WEBSITE_URL TINYBLOB DEFAULT NULL,
  RECOMMENDER_TYPE varchar(20) NOT NULL,
  CREATED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE user (
  ID int(11) NOT NULL AUTO_INCREMENT,
  FB_ID varchar(20) DEFAULT NULL,
  FIRST_NAME TINYBLOB DEFAULT NULL,
  LAST_NAME TINYBLOB DEFAULT NULL,
  CREATED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE bhav (
  ID int(11) NOT NULL AUTO_INCREMENT,
  SYMBOL varchar(20) NOT NULL,
  SERIES varchar(10) NOT NULL,
  OPEN double NOT NULL,
  HIGH double DEFAULT NULL,
  LOW double DEFAULT NULL,
  CLOSE double DEFAULT NULL,
  LAST double NOT NULL,
  PREV_CLOSE double NOT NULL,
  TOTAL_TRADED_QUANTITY int(15) NOT NULL,
  TOTAL_TRADED_VALUE double NOT NULL,
  TIMESTAMP varchar(45) NOT NULL,
  ISIN varchar(45) NOT NULL,
  TOTAL_TRADES varchar(45) NOT NULL,
  CREATED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE equities (
  symbol varchar(45) NOT NULL,
  name_of_company varchar(45) NOT NULL,
  series varchar(45) DEFAULT NULL,
  paid_up_value int(11) DEFAULT NULL,
  market_lot int(11) DEFAULT NULL,
  isin_number varchar(45) NOT NULL,
  face_value int(11) DEFAULT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8;

CREATE TABLE scheduler_job (
  ID int(11) NOT NULL AUTO_INCREMENT,
  STATUS varchar(20) NOT NULL,
  ERROR_MESSAGE TINYBLOB DEFAULT NULL,
  COMPLETED_DTS bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
