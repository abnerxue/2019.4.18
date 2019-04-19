package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.CourseOfTreatment;
import com.ck.springboot.service.MedcineRestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MedcineRestrictionController {

	@Autowired
    MedcineRestrictionService medcineRestrictionService;

	@GetMapping(value = "/selectlimitbydiagnosisid")
	public Map<String, Object> selectByDiagnosisId(Integer diagnosisId) {

		Map<String, Object> map = new LinkedHashMap<>();
		List<MedcineRestriction> medcinerestriction = medcineRestrictionService.selectByDiagnosisId(diagnosisId);
		if (medcinerestriction.size() == 0) {
			map.put("code", 0);
			map.put("msg", "无诊断限制信息");
			map.put("result", medcinerestriction);
		} else if (medcinerestriction.size() > 0) {
			map.put("code", 0);
			map.put("msg", "success");
			map.put("result", medcinerestriction);
		} else {
			map.put("code", 10000);
			map.put("msg", "获取诊断限制信息失败");
		}
		return map;
	}

	@GetMapping(value = "/selecttreatmentbydiagnosisid")
	public Map<String, Object> selectTreatmentByDiagnosisId(Integer diagnosisId) {

		Map<String, Object> map = new LinkedHashMap<>();
		List<CourseOfTreatment> treatment = medcineRestrictionService.selectTreatmentByDiagnosisId(diagnosisId);
		if (treatment.size() == 0) {
			map.put("code", 0);
			map.put("msg", "无诊断疗程信息");
			map.put("result", treatment);
		} else if (treatment.size() > 0) {
			map.put("code", 0);
			map.put("msg", "success");
			map.put("result", treatment);
		} else {
			map.put("code", 10000);
			map.put("msg", "获取诊断疗程信息失败");
		}
		   return map; 
	}

	@RequestMapping(value = "/deletediagnosisidmedcine", method = RequestMethod.POST)
	public RespBean deleteDiagnosisidMedcine(Integer id) {
		if (medcineRestrictionService.deleteDiagnosisidMedcine(id) == 1) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}

	@RequestMapping(value = "/updattreatmentbyid", method = RequestMethod.POST)
	public RespBean updateTreatmentById(CourseOfTreatment course) {
		if (medcineRestrictionService.updateTreatmentById(course) == 1) {
			return RespBean.ok("更新成功!");
		}
		return RespBean.error("更新失败!");
	}

	@RequestMapping(value = "/insertdiagnsislimit", method = RequestMethod.POST)
	public RespBean insertDiagnsis(Integer diagnosisId, Integer medcineId) {
		Integer action = 2;
		if (medcineRestrictionService.repeatVerification(diagnosisId, medcineId) > 0) {
			return RespBean.error("添加失败,该诊断限用药物中已存在该药物!");
		} else {
			if (medcineRestrictionService.insertDiagnsis(diagnosisId, medcineId, action) == 1) {
				return RespBean.ok("添加成功!");
			} else {
				return RespBean.error("添加失败!");
			}
		}
	}

	@RequestMapping(value = "/savediagnsislimit", method = RequestMethod.POST)
	public RespBean updateDiagnsisRestriction(@RequestBody List<MedcineRestriction> diagnsisrestriction) {
		if (medcineRestrictionService.updateDiagnsisRestriction(diagnsisrestriction) > 0) {
			return RespBean.ok("更新成功!");
		} else {
			return RespBean.error("更新失败!");
		}
	}
}
