package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {

    public static void export(String fileName, List data, Class cls) throws IOException {
        // 1.设置响应头
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        // 2.文件名编码格式设置，兼容Firefox
        fileName = URLEncoder.encode(fileName, "UTF-8");
        String agent = req.getHeader("USER-AGENT");
        boolean isFireFox = StringUtils.isEmpty(agent) ? false : agent.indexOf("Firefox") > -1;
        if (!isFireFox) {
            resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        } else {
            resp.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);
        }

        // 3.导出
        WriteCellStyle titleStyle = commonTitleStyle();
        WriteCellStyle contentStyle = commonContentStyle();
        EasyExcel.write(resp.getOutputStream(), cls)
                .registerWriteHandler(new HorizontalCellStyleStrategy(titleStyle, contentStyle))
                .autoCloseStream(true).sheet().doWrite(data);
    }


    /**
     * 默认标题样式
     *
     * @return com.alibaba.excel.write.metadata.style.WriteCellStyle
     */
    public static WriteCellStyle commonTitleStyle() {
        WriteCellStyle titleStyle = new WriteCellStyle();
        titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headFont = new WriteFont();
        headFont.setFontName("微软雅黑");
        headFont.setFontHeightInPoints((short) 14);
        headFont.setBold(false);
        titleStyle.setWriteFont(headFont);
        return titleStyle;
    }

    /**
     * 默认内容样式
     *
     * @return com.alibaba.excel.write.metadata.style.WriteCellStyle
     */
    public static WriteCellStyle commonContentStyle() {
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        return contentStyle;
    }

}
