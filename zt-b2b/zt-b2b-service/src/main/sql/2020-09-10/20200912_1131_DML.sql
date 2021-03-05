/***************************************************************/
-- 功能描述：默认页面首页/广告活动会场/楼层新品和近效期
-- 创建人员：sjl
-- 创建日期：2020-09-11
-- /**************************************************************/
INSERT INTO B2B.ADV_POSITION (ID, AP_DISPLAY, CLICK_NUM, CREATE_TIME, ADV_START_DATE, ADV_END_DATE, IS_DEL, IS_USE, ADV_NAME, IMG_NUM, META_DATA, UPDATE_TIME, CREATE_USER_ID, UPDATE_USER_ID) VALUES (1, 'CENTER_MORE_BIG', 0, TO_DATE('2020-08-27 11:24:38', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-09-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-09-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 'ON', '首页活动会场', 3, '{}', TO_DATE('2020-09-10 12:00:29', 'YYYY-MM-DD HH24:MI:SS'), 119, 120);

INSERT INTO B2B.FLOOR (ID, TYPE, TITLE, META_DATA, CREATE_TIME, UPDATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, IS_DEL, GOODS_NUM, FLOOR_KEY, HUE, IS_USE, FLOOR_SORT) VALUES (1, 'SINGLE_LINE', '新品上架', '{}', TO_DATE('2020-09-09 09:21:02', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-09-09 19:16:34', 'YYYY-MM-DD HH24:MI:SS'), 115, 115, 0, 1, 'XPSJ', null, 'ON', 13);
INSERT INTO B2B.FLOOR (ID, TYPE, TITLE, META_DATA, CREATE_TIME, UPDATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, IS_DEL, GOODS_NUM, FLOOR_KEY, HUE, IS_USE, FLOOR_SORT) VALUES (2, 'SINGLE_LINE', '近效期商品', '{}', TO_DATE('2020-09-09 09:21:40', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-09-09 19:16:33', 'YYYY-MM-DD HH24:MI:SS'), 115, 115, 0, 1, 'JXQSP', null, 'ON', 14);

INSERT INTO B2B.WEB_PAGE (ID, TITLE, UPDATE_TIME, CREATE_TIME, META_DATA, CREATE_USER_ID, UPDATE_USER_ID, IS_DEL, HINT_ICON, SORT_NUM, IS_USE) VALUES (1, '首页', TO_DATE('2020-09-10 22:41:32', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-08-30 15:04:26', 'YYYY-MM-DD HH24:MI:SS'), '{"pageSetList":[{"type":"FLOOR","floorId":2,"floorTitle":"近效期商品"},{"type":"FLOOR","floorId":1,"floorTitle":"新品上架"}],"classifySetList":[],"visibleSet":[{"type":"ALL_VISIBLE","memberSetList":[]}]}', 119, 111, 0, 'HOT', null, 'ON');


INSERT INTO B2B.DELIVERY_AMOUNT (ID, AREA_ID, AREA_NAME, DA_AMOUNT, DA_NAME, TYPE, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID, IS_USE) VALUES (1, 'null', '全国', 300, '默认金额', 'PROVINCE', TO_DATE('2020-08-21 12:01:40', 'YYYY-MM-DD HH24:MI:SS'), 120, TO_DATE('2020-09-11 16:37:47', 'YYYY-MM-DD HH24:MI:SS'), 120, 'ON');