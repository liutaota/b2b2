/***************************************************************/
-- 功能描述：修改认证申请时间
-- 创建人员：sjl
-- 创建日期：2020-10-26
-- /**************************************************************/
alter table LICENCE_APPLY rename column SIGN_DATE to SIGN_TIME
/

alter table LICENCE_APPLY rename column VALID_ON_DATE to VALID_ON_TIME
/

alter table LICENCE_APPLY rename column VALID_END_DATE to VALID_END_TIME
/

alter table LICENCE_APPLY rename column CREATE_DATE to CREATE_TIME
/

alter table LICENCE_APPLY rename column AUDIT_DATE to AUDIT_TIME
/

alter table LICENCE_APPLY
	add CERTIFICATION_TYPE VARCHAR2(1024)
/

comment on column LICENCE_APPLY.CERTIFICATION_TYPE is '认证类型'
/


/***************************************************************/
-- 功能描述：修改证照时间
-- 创建人员：sjl
-- 创建日期：2020-10-26
-- /**************************************************************/
alter table LICENCE rename column SIGN_DATE to SIGN_TIME
/

alter table LICENCE rename column VALID_ON_DATE to VALID_ON_TIME
/

alter table LICENCE rename column VALID_END_DATE to VALID_END_TIME
/

alter table LICENCE rename column CREATE_DATE to CREATE_TIME
/

alter table LICENCE
	add CERTIFICATION_TYPE VARCHAR2(1024)
/

comment on column LICENCE.CERTIFICATION_TYPE is '认证类型'
/

