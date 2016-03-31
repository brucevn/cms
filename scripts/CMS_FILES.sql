CREATE TABLE CMS_FILE
(
    FILE_ID    NUMBER PRIMARY KEY NOT NULL,
    FILE_NAME  VARCHAR2(50),
    FILE_TYPE  VARCHAR2(50),
    IS_IMAGE   NUMBER(1),
    OWNER      VARCHAR2(50),
    FOLDER_ID  NUMBER,
    CONTENT    BLOB,
    IS_PUBLIC     NUMBER(1)
)
;
ALTER TABLE CMS_FILE ADD FILE_EXT VARCHAR2(10);
ALTER TABLE CMS_FILE ADD FILE_SIZE NUMBER;
ALTER TABLE CMS_FILE ADD CREATED_DATE DATE;
ALTER TABLE CMS_FILE MODIFY FILE_NAME varchar2(512);
ALTER TABLE CMS_FILE MODIFY FILE_TYPE varchar2(512);
CREATE SEQUENCE CMS_FILE_SEQ START WITH 1 INCREMENT BY 1;

