package com.ck.springboot.poi;

import com.ck.springboot.pojo.PrescriptionHistorySee;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 2018/1/16.
 */
public class PoiPreHisT {
    public static ResponseEntity<byte[]> exportEmp2Excel(List<PrescriptionHistorySee> pres) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
//            3.1文档类别
            dsi.setCategory("所有处方信息");
            //3.2设置文档管理员
            dsi.setManager("春糠集团");
            //3.3设置组织机构
            dsi.setCompany("春糠集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("所有处方信息");
            //4.2.设置文档标题
            si.setTitle("所有处方信息");
            //4.3 设置文档作者
            si.setAuthor("春糠集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("所有处方信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));
            dateCellStyle.setAlignment(HorizontalAlignment.LEFT);
            dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //换行--垂直居中--左对齐
			HSSFCellStyle cellStyle = workbook.createCellStyle();       
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
			headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            sheet.setColumnWidth(2, 20 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(5, 10 * 256);
            sheet.setColumnWidth(6, 5 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            sheet.setColumnWidth(8, 10 * 256);
            sheet.setColumnWidth(9, 10 * 256);
            sheet.setColumnWidth(10, 20 * 256);
            sheet.setColumnWidth(11, 20 * 256);
            sheet.setColumnWidth(12, 15 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("序号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("就医单位名称");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("审方中心名称");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("处方号");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("就诊号");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("患者姓名");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("性别");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("就诊科室");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("医师姓名");
            cell8.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell9.setCellValue("审核药师姓名");
            cell9.setCellStyle(headerStyle);
            HSSFCell cell10 = headerRow.createCell(10);
            cell10.setCellValue("处方开具时间");
            cell10.setCellStyle(headerStyle);
            HSSFCell cell11 = headerRow.createCell(11);
            cell11.setCellValue("处方审核时间");
            cell11.setCellStyle(headerStyle);
            HSSFCell cell12 = headerRow.createCell(12);
            cell12.setCellValue("审核等级");
            cell12.setCellStyle(headerStyle);
            //6.装数据
            for (int i = 0; i < pres.size(); i++) {
            	 HSSFRow row = sheet.createRow(i + 1);
                 PrescriptionHistorySee pre = pres.get(i);
                 HSSFCell hss0 = row.createCell(0);
                 hss0.setCellStyle(cellStyle);
                 hss0.setCellValue(pre.getId());
                 HSSFCell hss1 = row.createCell(1);
                 hss1.setCellStyle(cellStyle);
                 hss1.setCellValue(pre.getUnitName());
                 
                 HSSFCell hss2= row.createCell(2);
                 hss2.setCellStyle(cellStyle);
                 hss2.setCellValue(pre.getAuditCenterName());

                 HSSFCell hss3 = row.createCell(3);
                 hss3.setCellStyle(cellStyle);
                 hss3.setCellValue(pre.getPrescriptionNumber());
                 HSSFCell hss4 = row.createCell(4);
                 hss4.setCellStyle(cellStyle);
                 hss4.setCellValue(pre.getVisitNumber());

                 HSSFCell hss5 = row.createCell(5);
                 hss5.setCellStyle(cellStyle);
                 hss5.setCellValue(pre.getPatientName());

                 HSSFCell hss6 = row.createCell(6);
                 hss6.setCellStyle(cellStyle);
                 hss6.setCellValue(pre.getPatientSex());

                 HSSFCell hss7 = row.createCell(7);
                 hss7.setCellStyle(cellStyle);
                 hss7.setCellValue(pre.getDeptName());

                 HSSFCell hss8 = row.createCell(8);
                 hss8.setCellStyle(cellStyle);
                 hss8.setCellValue(pre.getDoctorName());
                 
                 HSSFCell hss9 = row.createCell(9);
                 hss9.setCellStyle(cellStyle);
                 hss9.setCellValue(pre.getAuditPharmacistName());
                 HSSFCell hss10 = row.createCell(10);
                 hss10.setCellStyle(dateCellStyle);
                 hss10.setCellValue(str2Date(pre.getSubmmitTime()));

                 HSSFCell hss11 = row.createCell(11);
                 hss11.setCellStyle(dateCellStyle);
                 hss11.setCellValue(str2Date(pre.getAuditTime()));

                 HSSFCell audittime = row.createCell(11);
                 row.createCell(11).setCellValue(str2Date(pre.getAuditTime()));
                 audittime.setCellStyle(dateCellStyle);

                 HSSFCell hss12 = row.createCell(12);
                 hss12.setCellStyle(cellStyle);
                 hss12.setCellValue(pre.getReviewName());
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("所有处方表.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }
    public static String str2Date(Date date) {
		String resDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);	
		return resDate;

    }
    public static String id2Str(Integer id) {
    	String sex = null;
    	if(id == 0) {
    		sex = "男";
    	}else if(id == 1) {
    		sex = "女";
    	}else {
    		sex = "其他";
    	}
		return sex;
    }
}
