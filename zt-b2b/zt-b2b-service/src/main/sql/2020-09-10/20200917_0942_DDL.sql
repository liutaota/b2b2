/***************************************************************/
-- 功能描述：后台专用商品视图
-- 创建人员：tang
-- 创建日期：2020-09-17
-- /**************************************************************/


SELECT REGEXP_SUBSTR(t.classname_3, '[^\-]+$') classname, --货品分类名
       t.b2b_shop_flag isputaway,
       t.classname_3, --货品完整分类名
       t.accflag,
       t.medicinetypename, --剂型
       t.goodsname, --商品名称
       t.goodspinyin,
       t.goodsunit, --基本单位
       c.price * 10000 price_2, --批发二价
       c3.price * 10000 price_try, --商品指导价
       t.approvedocno, --批准文号
       e.factoryname, -- 厂家名称
       e.factoryid, --厂家id
       t.goodsid, --产品编号
       t.goodstype, --规格
       t.goodsid_gps, --商品定位， X为新品
       t.barcode, --条形码
       t.currencyname, --通用名
       t.credate, --创建时间
       t.classn_row_1, --分类编号1
       t.classname_1, --分类1
       t.classname_2, --分类2
       t.classn_row_2, --分类编号2
       t.classn_row_3, --分类号
       t.busiscope, --经营范围id
       x1.classname busiscope_name,
       (CASE
         WHEN sum(d.goodsqty) is null THEN
          0
         WHEN sum(d.goodsqty) < 0 THEN
          0
         ELSE
          sum(d.goodsqty)
       END) goodsqty, --商品可销库存
       nvl((CASE

             WHEN 1 < 2 THEN
              to_char((SELECT b.invaliddate
                        FROM (SELECT a2.goodsid,
                                     a2.goodsqty,
                                     a.proddate,
                                     a.invaliddate
                                FROM gdztzs2.bms_st_qty_lst a2,
                                     gdztzs2.bms_lot_def    a
                               WHERE a2.goodsstatusid = 1
                                 AND a2.storageid IN (1, 732,691)
                                 AND a2.goodsid = a.goodsid(+)
                                 AND a2.lotid = a.lotid(+)
                                 AND a.invaliddate IS NOT NULL
                               ORDER BY a.invaliddate) b
                       WHERE ROWNUM = 1
                         AND b.goodsid = d.goodsid),
                      'yyyy-mm-dd')
             ELSE
              ' '
           END),
           0000 - 00 - 00) invaliddate, --商品最近的一个效期
       f.ddlname otcflag, --OTC标志
       f1.ddlname storagecondition, --运输条件
       t.prodarea, --产地
       f2.ddlname transcondition, --运输条件
       --货品说明
       t.dosage, --用法用量
       t.usegoodstime, --用药时间
       t.diagnosticinfo, --诊断信息
       t.transporttime, --运输时限
       t.treatment, --疗程说明
       t.memo, --备注
       t1.supplyerid, --采购ID
       t2.employeename, --采购员中名称
       (CASE
         WHEN sum(t1.zx_b2b_num_limit) IS NULL THEN
          1
         ELSE
          sum(t1.zx_b2b_num_limit)
       END) zx_b2b_num_limit, --B2B最小销售数量
       d.storageid, --保管帐ID
       d.storagename --保管帐名称
  FROM gdztzs2.pub_goods t,
       gdztzs2.pub_entry_goods t1,
       gdztzs2.pub_employee t2,
       (SELECT a.goodsid, a.price
          FROM gdztzs2.bms_entry_goods_price a
         WHERE a.priceid IN (105)
           AND a.entryid = 1) c,
       (SELECT a.goodsid, a.price
          FROM gdztzs2.bms_entry_goods_price a
         WHERE a.priceid IN (68)
           AND a.entryid = 1) c3,

       (SELECT n.goodsid,
               n.storageid,
               nn.storagename,
               sum(n.goodsqty) - nvl(e.outqty, 0) goodsqty
          FROM gdztzs2.bms_st_qty_lst n,
               gdztzs2.bms_st_def nn,
               (SELECT e.goodsid, sum(e.outqty) outqty
                  FROM gdztzs2.bms_st_io_doc_tmp e
                 WHERE e.inoutflag = 0
                   and e.entryid = 1
                   AND storageid IN (1, 732,691)
                 GROUP BY e.goodsid) e
         WHERE n.storageid IN (1, 732)
           AND n.goodsid = e.goodsid(+)
           AND n.goodsstatusid = 1
           and n.storageid = nn.storageid(+)
         GROUP BY n.goodsid, n.storageid, e.outqty, nn.storagename) d,

       gdztzs2.pub_factory e,
       (SELECT a.ddlid, a.ddlname
          FROM gdztzs2.sys_ddl_dtl a
         WHERE a.sysid = 5140) f,
       (SELECT a.ddlid, a.ddlname
          FROM gdztzs2.pub_ddl_dtl a
         WHERE a.sysid = 1) f1,
       (SELECT a.ddlid, a.ddlname
          FROM gdztzs2.pub_ddl_dtl a
         WHERE a.sysid = 2) f2,
       gdztzs2.pub_goods_class x1
 WHERE t.goodsid = d.goodsid(+)
   AND t.goodsid = c.goodsid(+)
   AND t.goodsid = c3.goodsid(+)
 --  AND t.b2b_shop_flag = 1 --b2b_shop_flag=1 只显示上架的商品

   AND t.factoryid = e.factoryid(+)
   AND t.otcflag = f.ddlid(+)
   AND t.storagecondition = f1.ddlid(+)
   AND t.transcondition = f2.ddlid(+)
   and t1.entryid = 1
   and t.goodsid = t1.goodsid(+)
   and t1.supplyerid = t2.employeeid(+)
   and x1.classid(+) = t.busiscope


 GROUP BY
  t.b2b_shop_flag,
	t.goodsid,
          t.accflag,
          d.goodsid,
          c.price,
          t.goodsname,
          t.approvedocno,
          e.factoryname,
          e.factoryid,
          t.goodsunit,
          t.goodstype,
          c.price,
          c3.price,
          t.otcflag,
          f.ddlname,
          t.classname_3,
          t.medicinetypename,
          t.storagecondition,
          f1.ddlname,
          t.prodarea,
          t.transcondition,
          f2.ddlname,
          t.dosage,
          t.usegoodstime,
          t.diagnosticinfo,
          t.transporttime,
          t.treatment,
          t.memo,
          t.goodsid_gps,
          t.barcode,
          t.classn_row_3,
          t.currencyname,
          t.credate,
          t.classn_row_1,
          t.classname_1,
          t.classn_row_2,
          t.classname_2,
          t.busiscope,
          t2.employeename,
          t1.supplyerid,
          t1.zx_b2b_num_limit,
          d.storageid,
          d.storagename,
          t.goodspinyin,
          x1.classname
 order by goodsqty desc