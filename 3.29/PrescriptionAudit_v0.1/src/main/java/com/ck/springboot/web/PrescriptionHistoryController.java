package com.ck.springboot.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.poi.PoiPreHis;
import com.ck.springboot.poi.PoiPreHisT;
import com.ck.springboot.poi.PoiPreHisF;
import com.ck.springboot.pojo.Page;
import com.ck.springboot.pojo.PrescriptionHistory;
import com.ck.springboot.pojo.PrescriptionHistoryAndPatient;
import com.ck.springboot.pojo.PrescriptionHistoryDetail;
import com.ck.springboot.pojo.PrescriptionHistoryPending;
import com.ck.springboot.pojo.PrescriptionHistorySee;
import com.ck.springboot.service.PrescriptionHistoryService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PrescriptionHistoryController {
	@Autowired
	PrescriptionHistoryService prescriptionHistoryService;
	//历史处方
	@RequestMapping(value = "/getallprescriptionhistory", method = RequestMethod.POST)
	 public Map<String, Object> getAllPrescriptionHistory(
			 Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,
			 String patientName,Integer typeId,String problemCode,
			 Integer diagnosisId,Integer stateId,Integer reviewStateId,
			 Integer deptId,String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) throws Exception {
		Map<String, Object> map = new LinkedHashMap<>();
		
		List<PrescriptionHistory> prescriptionhistory = prescriptionHistoryService.getAllPrescriptionHistory(pageNo,pageSize,auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,reviewStateId,deptId,beginDateScope);
		totalCount = prescriptionHistoryService.getPrescriptionHistoryCount(auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,reviewStateId,deptId,beginDateScope);

		   if (totalCount%pageSize == 0){
			   totalPage = totalCount/pageSize;
		   }else {
			   totalPage = totalCount/pageSize + 1;
		   }
		   
		   Page p = new Page(pageNo,pageSize,totalPage,totalCount);
		   if (prescriptionhistory.size()==0){
			   map.put("code",0);
			   map.put("msg","无历史处方信息");
			   map.put("result", prescriptionhistory);
		   }else if(prescriptionhistory.size()>0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("page", p);
			   map.put("result", prescriptionhistory);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取历史处方信息失败");
			   map.put("result", prescriptionhistory);
		   }
		   return map;
  }
	//待处理所有处方
	@RequestMapping(value = "/getpendinghistory", method = RequestMethod.POST)
	 public Map<String, Object> getPendingHistory(Integer auditCenterId,
			 Integer prescriptionNumber,
			 Integer reviewStateId,
			 String typeId,
			 String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) throws Exception {
		Map<String, Object> map = new LinkedHashMap<>();
		   List<PrescriptionHistoryPending> pre = prescriptionHistoryService.getPendingHistory(pageNo,pageSize,auditCenterId,prescriptionNumber,reviewStateId,typeId,beginDateScope);
		   totalCount = prescriptionHistoryService.getPendingHistoryCount(auditCenterId,prescriptionNumber,reviewStateId,typeId,beginDateScope);
		   if (totalCount%pageSize == 0){
			   totalPage = totalCount/pageSize;
		   }else {
			   totalPage = totalCount/pageSize + 1;
		   }
		   Page p = new Page(pageNo,pageSize,totalPage,totalCount);
		   if (pre.size()==0){
			   map.put("code",0);
			   map.put("msg","无待处理历史处方信息");
			   map.put("result", pre);
		   }else if(pre.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("page", p);
		   map.put("result", pre);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取待处理处方信息失败"); 
		   }    
		   return map;
 }
	//导出历史处方信息
    @RequestMapping(value = "/exportprescriptionhistory", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp(Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,
				 String patientName,Integer typeId,String problemCode,
				 Integer diagnosisId,Integer stateId,Integer reviewStateId,
				 Integer deptId,String beginDateScope,
				 	@RequestParam(defaultValue = "1") Integer pageNo,
			        @RequestParam(defaultValue = "10") Integer pageSize,
			        Integer  totalCount,Integer totalPage) {
	    	
	    	pageSize = prescriptionHistoryService.getPrescriptionHistoryCount(auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,reviewStateId,deptId,beginDateScope);
	        return PoiPreHis.exportEmp2Excel(prescriptionHistoryService.getAllPrescriptionHistory(pageNo,pageSize,
	        		auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,
	        		reviewStateId,deptId,beginDateScope));
	    }
	//问题处方
	@GetMapping(value = "/getallproblemhistory")
	 public Map<String, Object> getAllPrescriptionHistorySeeF(Integer auditCenterId,
			 Integer reviewStateId,
			 String prescriptionNumber,
			 String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) throws Exception {

		Map<String, Object> map = new LinkedHashMap<>();
		
		List<PrescriptionHistorySee> pre = prescriptionHistoryService.getAllPrescriptionHistorySeeF(pageNo,pageSize,auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
		totalCount = prescriptionHistoryService.getPrescriptionHistoryCountF(auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
		   if (totalCount%pageSize == 0){
			   totalPage = totalCount/pageSize;
		   }else {
			   totalPage = totalCount/pageSize + 1;
		   }

		   Page p = new Page(pageNo,pageSize,totalPage,totalCount);

		   if (pre.size()==0){
			   map.put("code",0);
			   map.put("msg","无历史处方信息");
			   map.put("result", pre);
		   }else if(pre.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("page", p);
		   map.put("result", pre);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取历史处方信息失败");
		   }
		    
		   return map;		
 }
	//导出问题处方处方信息
    @RequestMapping(value = "/exportallproblemhistory", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmpF(Integer auditCenterId,Integer reviewStateId,
			 String prescriptionNumber,String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) {
	    	
	    	pageSize = prescriptionHistoryService.getPrescriptionHistoryCountF(auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
	        return PoiPreHisF.exportEmp2Excel(prescriptionHistoryService.getAllPrescriptionHistorySeeF(pageNo,pageSize,auditCenterId,reviewStateId,prescriptionNumber,beginDateScope));
	    }
	//所有处方
	@GetMapping(value = "/getallhistory")
	 public Map<String, Object> getAllPrescriptionHistorySeeT(Integer auditCenterId,
			 Integer reviewStateId,
			 String prescriptionNumber,
			 String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) throws Exception {
			Map<String, Object> map = new LinkedHashMap<>();
		   List<PrescriptionHistorySee> pre = prescriptionHistoryService.getAllPrescriptionHistorySeeT(pageNo,pageSize,auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
		   totalCount = prescriptionHistoryService.getPrescriptionHistoryCountT(auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
		   if (totalCount%pageSize == 0){
			   totalPage = totalCount/pageSize;
		   }else {
			   totalPage = totalCount/pageSize + 1;
		   }
		   Page p = new Page(pageNo,pageSize,totalPage,totalCount);
		   if (pre.size()==0 || pre.isEmpty()){
			   map.put("code",0);
			   map.put("msg","无历史处方信息");
			   map.put("result", pre);
		   }else if(pre.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("page", p);
		   map.put("result", pre);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取历史处方信息失败"); 
		   }    
		   return map;
 }
	//导出所有处方信息
    @RequestMapping(value = "/exportallhistory", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmpT(Integer auditCenterId,Integer reviewStateId,
			 String prescriptionNumber,String beginDateScope,
			 	@RequestParam(defaultValue = "1") Integer pageNo,
		        @RequestParam(defaultValue = "10") Integer pageSize,
		        Integer  totalCount,Integer totalPage) throws IOException {
	    	
	    	pageSize = prescriptionHistoryService.getPrescriptionHistoryCountT(auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
	    	List<PrescriptionHistorySee> pres = prescriptionHistoryService.getAllPrescriptionHistorySeeT(pageNo,pageSize,auditCenterId,reviewStateId,prescriptionNumber,beginDateScope);
	        return PoiPreHisT.exportEmp2Excel(pres);
	    }
    //通过处方主键查询处方详情
  	@RequestMapping(value = "/getprehisbyid", method = RequestMethod.GET)
  	 public Map<String, Object> getPreHisById(Integer id,String dateTime) throws Exception {
  		Map<String, Object> map = new LinkedHashMap<>();
  		Map<String, Object> map1 = new LinkedHashMap<>();
  		List<PrescriptionHistoryDetail> pre = prescriptionHistoryService.getPreHisById(id);
  		
  		java.sql.Timestamp dTimeBefore=null;
  		java.sql.Timestamp dTimeLater=null;
  		if(dateTime!=null) {
  			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
  			Date dTime=f.parse(dateTime);
  			Calendar time2 = Calendar.getInstance();
  			time2.setTime(dTime);
  			time2.add(Calendar.DATE, 1);
  			dTimeBefore = new java.sql.Timestamp(dTime.getTime());//00:00
  			dTimeLater = new java.sql.Timestamp(time2.getTime().getTime());//24:00
  		}
  		
  		int checkDays=prescriptionHistoryService.getHistoryCheckDays();//历史审核天数
  		String idNumber=null;
  		String socialSecurityNumber=null;
  		Calendar time = Calendar.getInstance();
  		Date nowDate=null;
  		for(PrescriptionHistoryDetail p : pre) {
  			idNumber=p.getPatient().getIdNumber();
  			socialSecurityNumber=p.getPatient().getSocialSecurityNumber();
  			nowDate=p.getAuditTime();
  			time.setTime(nowDate);
  			time.add(Calendar.DATE, -checkDays);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
  		}
  		Date beforeDate=time.getTime();
  		
  		java.sql.Timestamp nowDate2 = new java.sql.Timestamp(nowDate.getTime());
  		java.sql.Timestamp beforeDate2 = new java.sql.Timestamp(beforeDate.getTime());
  		
  		List<PrescriptionHistoryAndPatient> phpList=prescriptionHistoryService.getPrescriptionHistoryAndPatient(idNumber, socialSecurityNumber,nowDate2,beforeDate2,dTimeBefore,dTimeLater);
  		//根据所得到的历史处方查询问题处方
  		for(PrescriptionHistoryAndPatient php:phpList) {
  			String identityNumber1 = php.getIdNumber();//获取身份证号
  			String socialNumber1 = php.getSocialSecurityNumber();//获取社保号
  			String prescriptonNumber2 = php.getPrescriptionMedcine().get(0).getPrescriptionNumber();
  			if(prescriptionHistoryService.getCountsByIdNumber(identityNumber1, prescriptonNumber2)>0) {
  				php.setIsProblem(1);
  			}else if(prescriptionHistoryService.getCountsBySocialSecurityNumber(socialNumber1, prescriptonNumber2)>0) {
  				php.setIsProblem(1);
  			}
  		}

  		map1.put("checkday", checkDays);
  		map1.put("detail", pre);
  		map1.put("list", phpList);
  		if(pre.size()==0&&phpList.size()==0) {
  			map.put("code",0);
  			map.put("msg","获取处方详情成功");
  			map.put("result", map1);
  		}else if(pre.size()>0&&phpList.size()>0) {
  			map.put("code",0);
  			map.put("msg","获取处方详情成功");
  			map.put("result", map1);
  		}else if(pre.size()>0&&phpList.size()==0) {
  			map.put("code",0);
  			map.put("msg","获取处方详情成功");
  			map.put("result", map1);
  		}else {
  			 map.put("code",10000);
  			 map.put("msg","获取处方详情失败");
  			 map.put("result", map1);
  		}
  		return map;
  		
    }
}
