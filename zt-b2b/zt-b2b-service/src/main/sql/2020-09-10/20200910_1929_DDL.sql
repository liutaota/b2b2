/***************************************************************/
-- 功能描述：MEMBER_APPLY 用户注册申请-->确保企业名称和详细地址的长度
-- 创建人员：sjl
-- 创建日期：2020-09-10
-- /**************************************************************/
alter table MEMBER_APPLY modify COMPANY_NAME VARCHAR2(500);
/
alter table MEMBER_APPLY modify ADDRESS VARCHAR2(500);
/