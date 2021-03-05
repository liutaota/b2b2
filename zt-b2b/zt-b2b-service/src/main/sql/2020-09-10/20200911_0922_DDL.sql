/***************************************************************/
-- 功能描述：厂家排序默认为1的问题，每次新增拿最大加1，厂家排序给number(5)
-- 创建人员：sjl
-- 创建日期：2020-09-11
-- /**************************************************************/
alter table FACTORY drop column FACTORY_SORT
/

alter table FACTORY
	add FACTORY_SORT NUMBER(5)
/

comment on column FACTORY.FACTORY_SORT is '厂家排序'
/
