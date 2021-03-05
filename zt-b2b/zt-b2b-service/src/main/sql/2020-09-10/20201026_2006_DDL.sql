/***************************************************************/
-- 功能描述：认证申请--》添加认证类型
-- 创建人员：sjl
-- 创建日期：2020-10-26
-- /**************************************************************/
alter table LICENCE_APPLY
	add CERTIFICATION_TYPE VARCHAR2(1024)
/

comment on column LICENCE_APPLY.CERTIFICATION_TYPE is '认证类型：LEGAL_PERSON   法人 CERTIGIER 授权人'
/