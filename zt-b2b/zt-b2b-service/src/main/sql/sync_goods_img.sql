UPDATE GOODS
SET GOODS_IMG = (
	SELECT
		'[' || listagg ( '{"type":"img","code":"goods/' || tmp.goods_id || '/' || tmp.pic || '"}', ',' ) within GROUP ( ORDER BY pic ) || ']' GOODS_IMG
	FROM
		TMP_GOODS_PIC tmp
	WHERE
		tmp.goods_id = erp_goods_id
	GROUP BY
	GOODS_ID
	)