/***************************************************************/
-- 功能描述：反馈表-->添加状态
-- 创建人员：sjl
-- 创建日期：2020-10-07
-- /**************************************************************/
alter table FEEDBACK
	add STATUS VARCHAR2(20)
/

comment on column FEEDBACK.STATUS is '状态：UNTREATED 未处理 REPLIED 已回复'
/

