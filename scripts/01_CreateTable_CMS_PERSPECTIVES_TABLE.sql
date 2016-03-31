--DROP TABLE CMS_PERSPECTIVE;
CREATE TABLE CMS_PERSPECTIVE(PERSPECTIVE_ID NUMBER NOT NULL PRIMARY KEY,
PERSPECTIVE_NAME varchar2(128) NOT NULL,
PARENT_ID NUMBER,
STATUS varchar2(20) NOT NULL,
LANGUAGE varchar2(2) NOT NULL);
ALTER TABLE CMS_PERSPECTIVE ADD UPDATED_DATE DATE;
CREATE SEQUENCE CMS_PERSPECTIVE_seq START WITH 1 INCREMENT BY 1;
/