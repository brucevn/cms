--DROP TABLE CMS_CATEGORIES;
CREATE TABLE CMS_CATEGORY(CATEGORY_ID NUMBER NOT NULL PRIMARY KEY,
CATEGORY_NAME varchar2(128) NOT NULL,
PARENT_ID NUMBER,
STATUS VARCHAR2(10) NOT NULL,
LANGUAGE varchar2(2) NOT NULL);
ALTER TABLE CMS_CATEGORY ADD UPDATED_DATE DATE;
CREATE SEQUENCE CMS_CATEGORY_seq START WITH 1 INCREMENT BY 1;
/
