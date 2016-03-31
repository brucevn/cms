create table CMS_WORK_FOLLOW(id number primary key not null,name varchar2(128) not null,description varchar2(1024));
create sequence cms_work_follow_seq start with 1 increment by 1;
create table CMS_WORK_FOLLOW_STEPS(ID number primary key not null,WORK_FOLLOW_ID NUMBER NOT NULL,STEP_NAME varchar2(512) not null);
create sequence cms_work_follow_steps_seq start with 1 increment by 1;
create TABLE cms_step_owners(id NUMBER PRIMARY KEY,STEP_ID NUMBER,OWNER NUMBER NOT NULL,OWNER_TYPE varchar2(10));
alter TABLE CMS_STEP_OWNERS ADD ORDER_NUMBER NUMBER;
CREATE SEQUENCE cms_step_owners_seq START WITH 1 INCREMENT BY 1;
alter TABLE CMS_WORK_FOLLOW_STEPS ADD ORDER_NUMBER DATE DEFAULT SYSDATE;
alter TABLE CMS_WORK_FOLLOW ADD UPDATED_DATE DATE DEFAULT SYSDATE;
