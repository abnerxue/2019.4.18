package com.ck.springboot.poi;

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
import com.ck.springboot.pojo.PrescriptionHistory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 2018/1/16.
 */
public class PoiPreHis {

    public static ResponseEntity<byte[]> exportEmp2Excel(List<PrescriptionHistory> pres) {
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
            dsi.setCategory("历史处方信息");
            //3.2设置文档管理员
            dsi.setManager("春糠集团");
            //3.3设置组织机构
            dsi.setCompany("春糠集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("历史处方信息");
            //4.2.设置文档标题
            si.setTitle("历史处方信息");
            //4.3 设置文档作者
            si.setAuthor("春糠集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("历史处方信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));
            dateCellStyle.setAlignment(HorizontalAlignment.LEFT);
            dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //换行
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
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 15 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(5, 10 * 256);
            sheet.setColumnWidth(6, 15 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            sheet.setColumnWidth(8, 40 * 256);
            sheet.setColumnWidth(9, 10 * 256);
            sheet.setColumnWidth(10, 10 * 256);
            sheet.setColumnWidth(11, 20 * 256);
            sheet.setColumnWidth(12, 20 * 256);
            sheet.setColumnWidth(13, 20 * 256);
            sheet.setColumnWidth(14, 10 * 256);
            sheet.setColumnWidth(15, 18 * 256);
            sheet.setColumnWidth(16, 10 * 256);
            sheet.setColumnWidth(17, 20 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("序号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("初审结果");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("复审结果");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("开方医生");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("处方号");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("处方类型");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("用药理由");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("问题代码");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("问题标题");
            cell8.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell9.setCellValue("患者姓名");
            cell9.setCellStyle(headerStyle);
            HSSFCell cell10 = headerRow.createCell(10);
            cell10.setCellValue("科室");
            cell10.setCellStyle(headerStyle);
            HSSFCell cell11 = headerRow.createCell(11);
            cell11.setCellValue("就诊号");
            cell11.setCellStyle(headerStyle);
            HSSFCell cell12 = headerRow.createCell(12);
            cell12.setCellValue("诊断");
            cell12.setCellStyle(headerStyle);
            HSSFCell cell13 = headerRow.createCell(13);
            cell13.setCellValue("提交时间");
            cell13.setCellStyle(headerStyle);
            HSSFCell cell14 = headerRow.createCell(14);
            cell14.setCellValue("审核药师");
            cell14.setCellStyle(headerStyle);
            HSSFCell cell15 = headerRow.createCell(15);
            cell15.setCellValue("审核时间");
            cell15.setCellStyle(headerStyle);
            HSSFCell cell16 = headerRow.createCell(16);
            cell16.setCellValue("确认医师");
            cell16.setCellStyle(headerStyle);
            HSSFCell cell17 = headerRow.createCell(17);
            cell17.setCellValue("确认时间");
            cell17.setCellStyle(headerStyle);
         
            //6.装数据
            for (int i = 0; i < pres.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                PrescriptionHistory pre = pres.get(i);
//                String[] problemCode = null;
                List<String> listPro = new ArrayList<String>();
                List<String> listProName = new ArrayList<String>();
                List<String> listDia = new ArrayList<String>();
                HSSFCell hss0 = row.createCell(0);
                hss0.setCellStyle(cellStyle);
                hss0.setCellValue(pre.getId());
                
                HSSFCell hss1 = row.createCell(1);
                hss1.setCellStyle(cellStyle);
                hss1.setCellValue(pre.getAuditstate().getStateName());
                HSSFCell hss2 = row.createCell(2);
                hss2.setCellStyle(cellStyle);
                hss2.setCellValue(pre.getAuditstate().getReviewName());
                HSSFCell hss3= row.createCell(3);
                hss3.setCellStyle(cellStyle);
                hss3.setCellValue(pre.getHospitalstaff().getDoctorName());
                HSSFCell hss4 = row.createCell(4);
                hss4.setCellStyle(cellStyle);
                hss4.setCellValue(pre.getPrescriptionNumber());
                HSSFCell hss5 = row.createCell(5);
                hss5.setCellStyle(cellStyle);
                hss5.setCellValue(pre.getTypedic().getTypeName());
                HSSFCell hss6= row.createCell(6);
                hss6.setCellStyle(cellStyle);
                hss6.setCellValue(pre.getTypedic().getTypeName());

	            for (int j = 0; j < pre.getProblemcode().size(); j++) {
	            	listPro.add(pre.getProblemcode().get(j).getProblemCode());
	            	listProName.add(pre.getProblemcode().get(j).getProblemName());
	            }
	            String problemCode = String.join("\n", listPro);
	            HSSFCell pro = row.createCell(7);
	            pro.setCellStyle(cellStyle);
	            pro.setCellValue(problemCode);
	            String problemName = String.join("\n", listProName);
	            HSSFCell proname = row.createCell(8);
	            proname.setCellStyle(cellStyle);
	            proname.setCellValue(problemName);
                HSSFCell hss9 = row.createCell(9);
                hss9.setCellStyle(cellStyle);
                hss9.setCellValue(pre.getPatient().getPatientName());
                HSSFCell hss10 = row.createCell(10);
                hss10.setCellStyle(cellStyle);
                hss10.setCellValue(pre.getMedicaldept().getDeptName());
                HSSFCell hss11= row.createCell(11);
                hss11.setCellStyle(cellStyle);
                hss11.setCellValue(pre.getVisitNumber());
	            for (int d = 0; d < pre.getDiagnosis().size(); d++) {
	            	listDia.add(pre.getDiagnosis().get(d).getDiagnosisName());
	            }
	            String DiaName = String.join("\n", listDia);
	            HSSFCell hss12 = row.createCell(12);
	            hss12.setCellStyle(cellStyle);
	            hss12.setCellValue(DiaName);

                HSSFCell hss13 = row.createCell(13);
                hss13.setCellValue(str2Date(pre.getSubmmitTime()));
                hss13.setCellStyle(dateCellStyle);
                HSSFCell hss14= row.createCell(14);
                hss14.setCellStyle(cellStyle);
                hss14.setCellValue(pre.getHospitalstaff().getAuditPharmacistName());

                HSSFCell hss15 = row.createCell(15);
                hss15.setCellValue(str2Date(pre.getAuditTime()));
                hss15.setCellStyle(dateCellStyle);
                HSSFCell hss16= row.createCell(16);
                hss16.setCellStyle(cellStyle);
                hss16.setCellValue(pre.getHospitalstaff().getComfirmPharmacistName()); 
                HSSFCell hss17 = row.createCell(17);
                hss17.setCellValue(str2Date(pre.getSubmmitTime()));
                hss17.setCellStyle(dateCellStyle);
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("历史处方表.xls".getBytes("UTF-8"), "iso-8859-1"));
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

}
