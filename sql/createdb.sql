create database sys_db
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

use sys_db;

CREATE TABLE sys_info_tbl (
    SYS_ID INT NOT NULL AUTO_INCREMENT,
    SYS_TYPE VARCHAR(16) NOT NULL,
    SYS_PN VARCHAR(10) NOT NULL,
    SYS_SN VARCHAR(10),
    SP_MAC VARCHAR(20) NOT NULL,
    SP_IP VARCHAR(16) NOT NULL,
    SP_TELNET_IP VARCHAR(16) NOT NULL,
    SP_TELNET_PORT VARCHAR(6) NOT NULL,
    SYS_LABEL VARCHAR(20) NOT NULL,
    SYS_STATE INT NOT NULL,
    POSITION VARCHAR(10),
    LOG VARCHAR(256),
    PRIMARY KEY (SYS_ID)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE user_tbl (
    USER_ID INT NOT NULL AUTO_INCREMENT,
    USER_NAME VARCHAR(64) NOT NULL,
    USER_PASSWORD VARCHAR(32) NOT NULL,
    USER_FIRST_LOGIN INT NOT NULL,
	USER_LAST_LOGIN_DATE DATETIME NOT NULL,
	USER_STATUS INT NOT NULL,
    PRIMARY KEY (USER_ID)
)  ENGINE=INNODB;

CREATE TABLE role_tbl (
    ROLE_ID INT NOT NULL AUTO_INCREMENT,
    ROLE_NAME VARCHAR(20) NOT NULL,
    ROLE_DESC VARCHAR(30),
    PRIMARY KEY (ROLE_ID)
)  ENGINE=INNODB;

CREATE TABLE auth_tbl (
    AUTH_ID INT NOT NULL AUTO_INCREMENT,
    AUTH_NAME VARCHAR(30) NOT NULL,
    AUTH_DESC VARCHAR(30),
	AUTH_PARAM VARCHAR(50) not null,
    PRIMARY KEY (AUTH_ID)
)  ENGINE=INNODB;

CREATE TABLE user_role_tbl (
	USER_ROLE_ID INT NOT NULL AUTO_INCREMENT,
    ROLE_NAME INT NOT NULL,
    USER_NAME INT NOT NULL,
    PRIMARY KEY (USER_ROLE_ID)
)  ENGINE=INNODB;

CREATE TABLE role_auth_tbl (
    AUTH_ID INT NOT NULL,
    ROLE_ID INT NOT NULL,
    PRIMARY KEY (AUTH_ID , ROLE_ID),
    FOREIGN KEY (AUTH_ID)
        REFERENCES auth_tbl (AUTH_ID),
    FOREIGN KEY (ROLE_ID)
        REFERENCES role_tbl (ROLE_ID)
)  ENGINE=INNODB;

CREATE TABLE res_tbl (
    RES_ID INT NOT NULL AUTO_INCREMENT,
    RES_DATE DATETIME NOT NULL,
    RES_HOUR INT NOT NULL,
    PRIMARY KEY (RES_ID)
)  ENGINE=INNODB;

CREATE TABLE user_res_tbl (
    USER_ID INT NOT NULL,
    RES_ID INT NOT NULL,
    PRIMARY KEY (USER_ID, RES_ID),
    FOREIGN KEY (USER_ID)
        REFERENCES user_tbl (USER_ID),
    FOREIGN KEY (RES_ID)
        REFERENCES res_tbl (RES_ID)
)  ENGINE=INNODB;

CREATE TABLE sys_res_tbl (
    SYS_ID INT NOT NULL,
    RES_ID INT NOT NULL,
    PRIMARY KEY (SYS_ID, RES_ID),
    FOREIGN KEY (SYS_ID)
        REFERENCES sys_info_tbl (SYS_ID),
    FOREIGN KEY (RES_ID)
        REFERENCES res_tbl (RES_ID)
)  ENGINE=INNODB;

CREATE TABLE access_record_tbl (
	RECORD_ID INT NOT NULL,
    USER_NAME VARCHAR(50) NOT NULL,
    ACCESS_TYPE VARCHAR(30) NOT NULL,
    ACCESS_TIME DATETIME NOT NULL,
    ACCESS_IP VARCHAR(45) NOT NULL,
    PRIMARY KEY(RECORD_ID)
) ENGINE=INNODB;

INSERT INTO `user_tbl` (
    USER_NAME,USER_PASSWORD,USER_FIRST_LOGIN,USER_LAST_LOGIN_DATE,USER_STATUS
) VALUES (
    'alex', 'abc123', 1, '2017-12-04 00:00:00', 1
);

INSERT INTO role_tbl (
	ROLE_NAME, ROLE_DESC
) VALUES (
	'admin', 'system administrator'
);

INSERT INTO user_role_tbl (
	ROLE_NAME, USER_NAME
) VALUES (
	'admin', 'alex'
);