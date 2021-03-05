select * from bms_sa_doc a where a.zx_bh_flag = 11



update bms_sa_doc a

set
a.B2B_ORDER_ID = (

select b2b_order_id from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_AMOUNT_TOTAL  = (

select B2B_AMOUNT_TOTAL from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_AMOUNT_DISCOUNT  = (

select B2B_AMOUNT_DISCOUNT from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_AMOUNT_PAY   = (

select B2B_AMOUNT_PAY from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_AMOUNT_DELIVERY    = (

select B2B_AMOUNT_DELIVERY from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_ORDER_TYPE   = (

select B2B_ORDER_TYPE from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_PAY_TYPE   = (

select B2B_PAY_TYPE from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_ORDER_NO   = (

select B2B_ORDER_NO from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
),
a.B2B_STORE_ID     = (

select B2B_STORE_ID from gpcs_placesupply b where a.placesupplyid =  b.placesupplyid
)



 where a.zx_bh_flag = 11


update bms_sa_dtl a
   set B2B_ORDER_ID       =
       (select b.b2b_order_id
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_ORDER_DTL_ID   =
       (select b.b2b_order_id
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_AMOUNT_TOTAL   =
       (select b.B2B_AMOUNT_TOTAL
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_AMOUNT_DISCOUNT =
       (select b.B2B_AMOUNT_DISCOUNT
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_AMOUNT_PAY     =
       (select b.B2B_AMOUNT_PAY
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_AMOUNT_DELIVERY =
       (select b.B2B_AMOUNT_DELIVERY
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_NUM               =
       (select b.B2B_NUM
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_PRICE          =
       (select b.B2B_PRICE
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_PRICE_DISCOUNT =
       (select b.B2B_PRICE_DISCOUNT
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_ORDER_TYPE     =
       (select b.B2B_ORDER_TYPE
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid),
       B2B_ORDER_NO       =
       (select b.B2B_ORDER_NO
          from gpcs_placesupplydtl b, gpcs_placesupplydtl_st c
         where b.placesupplydtlid = c.placesupplydtlid
           and c.placesupplydtlstid = a.placesupplydtlstid)
 where exists (
        select 1
          from bms_sa_doc d
         where d.salesid = a.salesid
           and d.zx_bh_flag = 11)




           update bms_sa_dtl a
   set B2B_ORDER_ID       =
       (select b.b2b_order_id
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_ORDER_DTL_ID   =
       (select b.B2B_ORDER_DTL_ID
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_AMOUNT_TOTAL   =
       (select b.B2B_AMOUNT_TOTAL
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_AMOUNT_DISCOUNT =
       (select b.B2B_AMOUNT_DISCOUNT
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_AMOUNT_PAY     =
       (select b.B2B_AMOUNT_PAY
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_AMOUNT_DELIVERY =
       (select b.B2B_AMOUNT_DELIVERY
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_NUM              =
       (select b.B2B_NUM
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_PRICE         =
       (select b.B2B_PRICE
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_PRICE_DISCOUNT =
       (select b.B2B_PRICE_DISCOUNT
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_ORDER_TYPE    =
       (select b.B2B_ORDER_TYPE
          from bms_sa_doc b
         where b.salesid = a.salesid
),
       B2B_ORDER_NO       =
       (select b.B2B_ORDER_NO
          from bms_sa_doc b
         where b.salesid = a.salesid
)
 where exists (
        select 1
          from bms_sa_doc d
         where d.salesid = a.salesid
           and d.zx_bh_flag = 11)


1868,
28735,
4876,
1703,
5633,
40144,
12407,
19455,
34942,
5088,
94503,
33951,
4194,
648,
1105,
5794,:
15305,
69411,
69273,
18938,
73254,
71933,
57398,
1655,
50998,
5261,
5575,
820,
4229,
4193,
12168,
7566,
6903,
520,
2146,
4120,
6216,
12032,
38852,
8259,
2543,
4236
