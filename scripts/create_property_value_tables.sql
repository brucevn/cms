CREATE TABLE PROPERTY_VALUE_NUMBER(
ID NUMBER PRIMARY KEY NOT NULL,
ITEM_ID NUMBER,
PROPERTY_ID NUMBER,
PROPERTY_VALUE NUMBER
);
CREATE SEQUENCE PROPERTY_VALUE_NUMBER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PROPERTY_VALUE_TEXT(
ID NUMBER PRIMARY KEY NOT NULL,
ITEM_ID NUMBER,
PROPERTY_ID NUMBER,
PROPERTY_VALUE VARCHAR2(4000)
);
CREATE SEQUENCE PROPERTY_VALUE_TEXT_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PROPERTY_VALUE_LONGTEXT(
ID NUMBER PRIMARY KEY NOT NULL,
ITEM_ID NUMBER,
PROPERTY_ID NUMBER,
PROPERTY_VALUE CLOB
);
CREATE SEQUENCE PROPERTY_VALUE_LONGTEXT_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PROPERTY_VALUE_DATE(
ID NUMBER PRIMARY KEY NOT NULL,
ITEM_ID NUMBER,
PROPERTY_ID NUMBER,
PROPERTY_VALUE DATE
);
CREATE SEQUENCE PROPERTY_VALUE_DATE_SEQ START WITH 1 INCREMENT BY 1;

