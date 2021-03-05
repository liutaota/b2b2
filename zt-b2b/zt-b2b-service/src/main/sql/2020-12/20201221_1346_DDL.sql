/***************************************************************/
-- 功能描述: 更换数据类型
-- 创建人员：tang
-- 创建日期：2020-12-21
-- /**************************************************************/

      -- Add/modify columns
      alter table ORDER_INFO add order_amount1 NUMBER(16,2);
      alter table ORDER_INFO add goods_amount1 NUMBER(16,2);
      alter table ORDER_INFO add actually_money1 NUMBER(16,2);
      alter table ORDER_INFO add refund_amount1 NUMBER(16,2);
      alter table ORDER_INFO add rpt_amount1 NUMBER(16,2);
      alter table ORDER_INFO add rec_money1 NUMBER(16,2);
      alter table ORDER_INFO add erp_amount1 NUMBER(16,2);
      alter table ORDER_INFO add erp_order_amount1 NUMBER(16,2);



update ORDER_INFO set  order_amount1=order_amount/10000,
     goods_amount1 =goods_amount/10000,
    actually_money1 =actually_money/10000,
   refund_amount1 =refund_amount/10000,
     rpt_amount1 =rpt_amount/10000,
      rec_money1=rec_money/10000,
       erp_amount1=erp_amount/10000,
      erp_order_amount1 =erp_order_amount/10000;


update ORDER_INFO set  order_amount=null,
     goods_amount=null,
     actually_money=null,
    refund_amount=null,
      rpt_amount=null,
      rec_money=null,
       erp_amount=null,
      erp_order_amount=null;

      -- Add/modify columns
      alter table ORDER_INFO modify order_amount NUMBER(16,2);
      alter table ORDER_INFO modify goods_amount NUMBER(16,2);
      alter table ORDER_INFO modify actually_money NUMBER(16,2);
      alter table ORDER_INFO modify refund_amount NUMBER(16,2);
      alter table ORDER_INFO modify rpt_amount NUMBER(16,2);
      alter table ORDER_INFO modify rec_money NUMBER(16,2);
      alter table ORDER_INFO modify erp_amount NUMBER(16,2);
      alter table ORDER_INFO modify erp_order_amount NUMBER(16,2);



update ORDER_INFO set  order_amount=order_amount1,
     goods_amount=goods_amount1,
     actually_money=actually_money1,
    refund_amount=refund_amount1,
      rpt_amount=rpt_amount1,
      rec_money=rec_money1,
       erp_amount=erp_amount1,
      erp_order_amount=erp_order_amount1;




      --goodsGoods

      -- Add/modify columns
      alter table ORDER_GOODS add goods_price1 NUMBER(16,4);
      alter table ORDER_GOODS add goods_pay_price1 NUMBER(16,4);
      alter table ORDER_GOODS add goods_num1 NUMBER(16,4);
      alter table ORDER_GOODS add sell_num1 NUMBER(16,4);
      alter table ORDER_GOODS add refund_num1 NUMBER(16,4);
      alter table ORDER_GOODS add erp_leastsaleqty1 NUMBER(16,4);
      alter table ORDER_GOODS add amount_num1 NUMBER(16,2);
      alter table ORDER_GOODS add amount_pay1 NUMBER(16,2);


      update  ORDER_GOODS set goods_price1 =goods_price/10000,
       goods_pay_price1 =goods_pay_price/10000,
       goods_num1 =goods_num/10000,
       sell_num1 =sell_num/10000,
       refund_num1 =refund_num/10000,
       erp_leastsaleqty1=erp_leastsaleqty/10000,
       amount_num1 =amount_num/10000,
       amount_pay1 =amount_pay/10000;


       update  ORDER_GOODS set goods_price=null,
        goods_pay_price=null,
        goods_num=null,
        sell_num=null,
        refund_num=null,
        erp_leastsaleqty=null,
        amount_num=null,
        amount_pay=null;


        -- Add/modify columns
        alter table ORDER_GOODS modify goods_num NUMBER(16,4);
        alter table ORDER_GOODS modify goods_price NUMBER(16,4);
        alter table ORDER_GOODS modify goods_pay_price NUMBER(16,4);
        alter table ORDER_GOODS modify sell_num NUMBER(16,4);
        alter table ORDER_GOODS modify refund_num NUMBER(16,4);
        alter table ORDER_GOODS modify erp_leastsaleqty NUMBER(16,4);
        alter table ORDER_GOODS modify amount_num NUMBER(16,2);
        alter table ORDER_GOODS modify amount_pay NUMBER(16,2);



        update  ORDER_GOODS set goods_price=goods_price1,
         goods_pay_price=goods_pay_price1,
         goods_num=goods_num1,
         sell_num=sell_num1,
         refund_num=refund_num1,
         erp_leastsaleqty=erp_leastsaleqty1,
         amount_num=amount_num1,
         amount_pay=amount_pay1;


         --cart

         -- Add/modify columns
         alter table CART modify goods_price1 NUMBER(16,4);

update  cart set GOODS_PRICE1=goods_price/10000;

update  cart set GOODS_PRICE=null;

update  cart set GOODS_PRICE=GOODS_PRICE1;




--FAVORITES


-- Add/modify columns
alter table FAVORITES add log_price1 NUMBER(16,4);
update  FAVORITES set LOG_PRICE1=LOG_PRICE/10000;

update  FAVORITES set LOG_PRICE=null;


-- Add/modify columns
alter table FAVORITES modify log_price NUMBER(16,4);

update  FAVORITES set LOG_PRICE=LOG_PRICE1;





-- Add/modify columns
alter table REC_DOC add total1 NUMBER(16,2);
alter table REC_DOC add premoney1 NUMBER(16,2);
alter table REC_DOC add refund_money1 NUMBER(16,2);


update  REC_DOC set total1=total/10000,
PREMONEY1=PREMONEY/10000,
refund_money1=refund_money/10000;

update  REC_DOC set total=null,
PREMONEY=null,
refund_money=null;


-- Add/modify columns
alter table REC_DOC modify total NUMBER(16,2);
alter table REC_DOC modify premoney NUMBER(16,2);
alter table REC_DOC modify refund_money NUMBER(16,2);

update  REC_DOC set total=total1,
PREMONEY=PREMONEY1,
refund_money=refund_money1;



--REC_DTL


-- Add/modify columns
alter table REC_DTL add total_line1 NUMBER(16,2);
alter table REC_DTL add unt_price1 NUMBER(16,4);
alter table REC_DTL add goods_qty1 NUMBER(16,4);
alter table REC_DTL add erp_leastsaleqty1 NUMBER(16,4);

update  REC_DTL set total_line1=total_line/10000,
unt_price1=unt_price/10000,
goods_qty1=goods_qty/10000,
erp_leastsaleqty1=erp_leastsaleqty/10000;


update  REC_DTL set total_line=null,
unt_price=null,
goods_qty=null,
erp_leastsaleqty=null;

-- Add/modify columns
alter table REC_DTL modify total_line NUMBER(16,2);
alter table REC_DTL modify unt_price NUMBER(16,4);
alter table REC_DTL modify goods_qty NUMBER(16,4);
alter table REC_DTL modify erp_leastsaleqty NUMBER(16,4);

update  REC_DTL set total_line=total_line1,
unt_price=unt_price1,
goods_qty=goods_qty1,
erp_leastsaleqty=erp_leastsaleqty1;


-- Add/modify columns
alter table STATEMENT add totel1 NUMBER(16,2);
alter table STATEMENT add pay_amount1 NUMBER(16,2);

update  STATEMENT set TOTEL1=TOTEL/10000,
PAY_AMOUNT1=PAY_AMOUNT/10000;


update  STATEMENT set TOTEL=null,
PAY_AMOUNT=null;


-- Add/modify columns
alter table STATEMENT modify totel NUMBER(16,2);
alter table STATEMENT modify pay_amount NUMBER(16,2);


update  STATEMENT set TOTEL=TOTEL1,
PAY_AMOUNT=PAY_AMOUNT1;


-- Add/modify columns
alter table REFUND_RETURN add refund_amount1 NUMBER(16,2);


update  REFUND_RETURN set REFUND_AMOUNT1=REFUND_AMOUNT/10000;

update  REFUND_RETURN set REFUND_AMOUNT=null;

-- Add/modify columns
alter table REFUND_RETURN modify refund_amount NUMBER(16,2);

update  REFUND_RETURN set REFUND_AMOUNT=REFUND_AMOUNT1;



-- Add/modify columns
alter table REFUND_DETAIL add refund_amount1 NUMBER(16,2);
alter table REFUND_DETAIL add pay_amount1 NUMBER(16,2);
alter table REFUND_DETAIL add pd_amount1 NUMBER(16,2);
alter table REFUND_DETAIL add goods_num1 NUMBER(16,4);
alter table REFUND_DETAIL add goods_price1 NUMBER(16,4);
alter table REFUND_DETAIL add goods_pay_price1 NUMBER(16,4);


update  REFUND_DETAIL set  refund_amount1=refund_amount/10000,
 pay_amount1 =pay_amount/10000,
 pd_amount1 =pd_amount/10000,
 goods_num1=goods_num/10000,
 goods_price1 =goods_price/10000,
 goods_pay_price1 =goods_pay_price/10000;


 update  REFUND_DETAIL set  refund_amount=null,
  pay_amount=null,
  pd_amount=null,
  goods_num=null,
  goods_price=null,
  goods_pay_price=null;

  -- Add/modify columns
  alter table REFUND_DETAIL modify refund_amount NUMBER(16,2);
  alter table REFUND_DETAIL modify pay_amount NUMBER(16,2);
  alter table REFUND_DETAIL modify pd_amount NUMBER(16,2);
  alter table REFUND_DETAIL modify goods_num NUMBER(16,4);
  alter table REFUND_DETAIL modify goods_price NUMBER(16,4);
  alter table REFUND_DETAIL modify goods_pay_price NUMBER(16,4);


  update  REFUND_DETAIL set  refund_amount=refund_amount1,
   pay_amount=pay_amount1,
   pd_amount=pd_amount1,
   goods_num=goods_num1,
   goods_price=goods_price1,
   goods_pay_price=goods_pay_price1;


   -- Add/modify columns
   alter table COUPON_LOG add order_amount1 NUMBER(16,2);
   alter table COUPON_LOG add coupon_amount1 NUMBER(16,2);
   alter table COUPON_LOG add order_final_amount1 NUMBER(16,2);

   update  COUPON_LOG set  ORDER_AMOUNT1=ORDER_AMOUNT/10000,
    COUPON_AMOUNT1=COUPON_AMOUNT/10000,
    ORDER_FINAL_AMOUNT1=ORDER_FINAL_AMOUNT/10000;

    update  COUPON_LOG set  ORDER_AMOUNT=null,
     COUPON_AMOUNT=null,
     ORDER_FINAL_AMOUNT=null;

     -- Add/modify columns
     alter table COUPON_LOG modify order_amount NUMBER(16,2);
     alter table COUPON_LOG modify coupon_amount NUMBER(16,2);
     alter table COUPON_LOG modify order_final_amount NUMBER(16,2);

     update  COUPON_LOG set  ORDER_AMOUNT=ORDER_AMOUNT1,
      COUPON_AMOUNT=COUPON_AMOUNT1,
      ORDER_FINAL_AMOUNT=ORDER_FINAL_AMOUNT1;

      -- Add/modify columns
      alter table COUPON add full_amount1 NUMBER(16,2);
      alter table COUPON add reduce_amount1 NUMBER(16,2);
      alter table COUPON add discount1 NUMBER(16,4);


      update  COUPON set  full_amount1=full_amount/10000,
       reduce_amount1=reduce_amount/10000,
       discount1=discount/10000;

       update  COUPON set  full_amount=null,
        reduce_amount=null,
        discount=null;

        -- Add/modify columns
        alter table COUPON modify full_amount NUMBER(16,2);
        alter table COUPON modify reduce_amount NUMBER(16,2);
        alter table COUPON modify discount NUMBER(16,4);

        update  COUPON set  full_amount=full_amount1,
         reduce_amount=reduce_amount1,
         discount=discount1;


         -- Add/modify columns
         alter table DAY_BILL modify order_amount NUMBER(16,2);
         alter table DAY_BILL modify payable_amount NUMBER(16,2);
         alter table DAY_BILL modify case_amount NUMBER(16,2);
         alter table DAY_BILL modify monthly_amount NUMBER(16,2);
         alter table DAY_BILL modify online_amount NUMBER(16,2);
         alter table DAY_BILL modify refund_amount NUMBER(16,2);
         alter table DAY_BILL modify reduction_amount NUMBER(16,2);
         alter table DAY_BILL modify promotion_amount NUMBER(16,2);




--ACTIVITY_GOODS
         -- Add/modify columns
         alter table ACTIVITY_GOODS add condition_price1 NUMBER(18,4);
         alter table ACTIVITY_GOODS add goods_price1 NUMBER(18,4);

         update  ACTIVITY_GOODS set CONDITION_PRICE1=CONDITION_PRICE/10000,
         GOODS_PRICE1=GOODS_PRICE/10000;

         update  ACTIVITY_GOODS set CONDITION_PRICE=null,
         GOODS_PRICE=null;

         -- Add/modify columns
         alter table ACTIVITY_GOODS modify condition_price NUMBER(18,4);
         alter table ACTIVITY_GOODS modify goods_price NUMBER(18,4);


         update  ACTIVITY_GOODS set CONDITION_PRICE=CONDITION_PRICE1,
         GOODS_PRICE=GOODS_PRICE1;

         update  ACTIVITY_GOODS set CONDITION_NUM=CONDITION_NUM/10000;

         -- Add/modify columns
         alter table ACTIVITY_STRATEGY add amount_satisfied1 NUMBER(18,4);
         alter table ACTIVITY_STRATEGY add reduced_amount1 NUMBER(18,4);
         alter table ACTIVITY_STRATEGY add discount1 NUMBER(18,4);
         alter table ACTIVITY_STRATEGY add cash1 NUMBER(18,4);


         update  ACTIVITY_STRATEGY set amount_satisfied1=amount_satisfied/10000,
         reduced_amount1=reduced_amount/10000,
         discount1=discount/10000,
         cash1=cash/10000;

         update  ACTIVITY_STRATEGY set amount_satisfied=null,
         reduced_amount=null,
         discount=null,
         cash=null;

         -- Add/modify columns
         alter table ACTIVITY_STRATEGY modify amount_satisfied NUMBER(18,4);
         alter table ACTIVITY_STRATEGY modify reduced_amount NUMBER(18,4);
         alter table ACTIVITY_STRATEGY modify discount NUMBER(18,4);
         alter table ACTIVITY_STRATEGY modify cash NUMBER(18,4);

         update  ACTIVITY_STRATEGY set amount_satisfied=amount_satisfied1,
         reduced_amount=reduced_amount1,
         discount=discount1,
         cash=cash1;

         update  ACTIVITY_STRATEGY set MEET_QUANTITY=MEET_QUANTITY/10000,
         GIFT_NUM=GIFT_NUM/10000;


-- Add/modify columns
alter table ACTIVITY_GIFT add goods_price1 NUMBER(18,4);

update  ACTIVITY_GIFT set GIFT_NUM=GIFT_NUM/10000,
GOODS_PRICE1=GOODS_PRICE/10000;


-- Add/modify columns
alter table ACTIVITY_GIFT modify goods_price NUMBER(18,4);

update  ACTIVITY_GIFT set
GOODS_PRICE=null;

update  ACTIVITY_GIFT set
GOODS_PRICE=GOODS_PRICE1;


-- Add/modify columns
alter table ACTIVITY_STRATEGY add meet_quantity1 NUMBER(18,4);
alter table ACTIVITY_STRATEGY add gift_num1 NUMBER(18,4);

update ACTIVITY_STRATEGY set meet_quantity=meet_quantity*10000,
gift_num=gift_num*10000;


update ACTIVITY_STRATEGY set  meet_quantity1 =meet_quantity/10000,
 gift_num1 =gift_num/10000;

-- Add/modify columns
alter table ACTIVITY_STRATEGY modify meet_quantity NUMBER(18,4);
alter table ACTIVITY_STRATEGY modify gift_num NUMBER(18,4);


update ACTIVITY_STRATEGY set meet_quantity=meet_quantity1,
gift_num=gift_num1;

-- Add/modify columns
alter table ACTIVITY_GOODS add condition_num1 NUMBER(18,4);
update ACTIVITY_GOODS set CONDITION_NUM1=CONDITION_NUM;
-- Add/modify columns
alter table ACTIVITY_GOODS modify condition_num NUMBER(18,4);

-- Add/modify columns
alter table ACTIVITY_GIFT add gift_num1 NUMBER(18,4);
update ACTIVITY_GIFT set GIFT_NUM1=GIFT_NUM;

update ACTIVITY_GIFT set GIFT_NUM=null;

-- Add/modify columns
alter table ACTIVITY_GIFT modify gift_num NUMBER(18,4);

update ACTIVITY_GIFT set GIFT_NUM=GIFT_NUM1;

update REFUND_DETAIL set goods_num=goods_num*10000;
