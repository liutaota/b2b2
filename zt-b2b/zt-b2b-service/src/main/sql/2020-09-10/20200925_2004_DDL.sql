/***************************************************************/
-- 功能描述：积分记录表
-- 创建人员：sjl
-- 创建日期：2020-09-25
-- /**************************************************************/
create table SCORE_RECORD
(
	ID NUMBER(18) not null
		constraint SCORE_RECORD_pk
			primary key,
	CONTENT VARCHAR2(500),
	CHAN_SCORE NUMBER(20),
	MEMBER__ID NUMBER(18),
	FROM_TYPE VARCHAR2(50),
	FROM_ID NUMBER(18),
	CREATE_TIME DATE,
	ORIGN_SCORE NUMBER(18),
	REMARK VARCHAR2(1024)
)
/

comment on table SCORE_RECORD is '用户积分记录'
/

comment on column SCORE_RECORD.ID is '主键id'
/

comment on column SCORE_RECORD.CONTENT is '内容'
/

comment on column SCORE_RECORD.CHAN_SCORE is '增或减积分'
/

comment on column SCORE_RECORD.MEMBER__ID is '用户id'
/

comment on column SCORE_RECORD.FROM_TYPE is '来源类型：1-订单 2-新用户 3-后台减少'
/

comment on column SCORE_RECORD.FROM_ID is '来源id'
/

comment on column SCORE_RECORD.CREATE_TIME is '添加时间'
/

comment on column SCORE_RECORD.ORIGN_SCORE is '原本积分'
/

comment on column SCORE_RECORD.REMARK is '备注'
/

create sequence SEQ_SCORE_RECORD
/