/***************************************************************/
-- 功能描述: 抽奖表--备注
-- 创建人员：tang
-- 创建日期：2021-01-04
-- /**************************************************************/

    -- Add/modify columns
    alter table LOTTERY_LOG add remark VARCHAR2(100);
    -- Add comments to the columns
    comment on column LOTTERY_LOG.remark
      is '备注';


      -- Add/modify columns
      alter table LOTTER_DAIL_QUALIFICATIONS add status VARCHAR2(50);
      -- Add comments to the columns
      comment on column LOTTER_DAIL_QUALIFICATIONS.status
        is '抽奖状态';

        -- Add/modify columns
        alter table LOTTER_DAIL_QUALIFICATIONS add order_id NUMBER(18);
        -- Add comments to the columns
        comment on column LOTTER_DAIL_QUALIFICATIONS.order_id
          is '订单id';

          -- Add/modify columns
          alter table GOODS add lot_num NUMBER(18);
          alter table GOODS add remark VARCHAR2(1024);
          -- Add comments to the columns
          comment on column GOODS.lot_num
            is '抽奖数量';
          comment on column GOODS.remark
            is '备注';


            -- Add/modify columns
            alter table LOTTERY_DIAL_DETAIL add prize_num NUMBER(18);
            -- Add comments to the columns
            comment on column LOTTERY_DIAL_DETAIL.prize_num
              is '数量';

              -- Add/modify columns
              alter table ORDER_GOODS add prize_id NUMBER(18);
              -- Add comments to the columns
              comment on column ORDER_GOODS.prize_id
                is '奖品id';

