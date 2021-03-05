/***************************************************************/
-- 功能描述：申请表时间命名统一规则
-- 创建人员：sjl
-- 创建日期：2020-10-23
-- /**************************************************************/
-- LICENCE_APPLY
alter table LICENCE_APPLY rename column VALID_ON_DATE to VALID_ON_TIME
/

alter table LICENCE_APPLY rename column VALID_END_DATE to VALID_END_TIME
/

alter table LICENCE_APPLY rename column CREATE_DATE to CREATE_TIME
/

alter table LICENCE_APPLY rename column AUDIT_DATE to AUDIT_TIME
/

alter table LICENCE_APPLY rename column SIGN_DATE to SIGN_TIME
/

-- LICENCE
alter table LICENCE rename column SIGN_DATE to SIGN_TIME
/

alter table LICENCE rename column VALID_ON_DATE to VALID_ON_TIME
/

alter table LICENCE rename column VALID_END_DATE to VALID_END_TIME
/

alter table LICENCE rename column CREATE_DATE to CREATE_TIME
/
