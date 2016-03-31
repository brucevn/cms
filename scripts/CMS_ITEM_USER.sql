--drop table CMS_ITEM_USER_ROLE
create table CMS_ITEM_USER_ROLE(id number primary key,USER_GROUP varchar2(128),IS_USER NUMBER,TYPE_ID number,USER_ROLE VARCHAR2(20));
create sequence CMS_ITEM_USER_ROLE_SEQ start with 1 increment by 1;
