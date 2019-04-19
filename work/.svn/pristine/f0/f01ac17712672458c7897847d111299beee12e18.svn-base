package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.*;
import com.ck.springboot.service.MedcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MedcineController {
	@Autowired
    MedcineService medcineService;

	// 平台所有药品
	@RequestMapping(value = "/getallmedcine", method = RequestMethod.GET)
	public Map<String, Object> getAllMedcine(Integer standardSource, Integer type, Integer category,
			Integer subcategory, String name, @RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, Integer totalCount, Integer totalPage) throws Exception {
		
			Map<String, Object> map = new LinkedHashMap<>();
	
			List<MedcineOnly> medcine = medcineService.getAllMedcine(pageNo, pageSize, standardSource, type, category, subcategory, name);
			List<MedcineOnly> med = new ArrayList<MedcineOnly>();
			List<MedcineOnly> medPage = new ArrayList<MedcineOnly>();
			try {
				if (medcine.size() != 0) {
					med.add(medcine.get(0));
				}
				for (int i = 0; i < medcine.size(); i++) {
					for (int j = 0; j < med.size(); j++) {
						if (!(med.contains(medcine.get(i)))) {
							med.add(medcine.get(i));
						}
					}
				}
				if(med.size()>pageSize) {
				for(int j =(pageNo-1)*pageSize;j<pageNo*pageSize;j++) {
					medPage.add(med.get(j));
				}
				}else {
					for(int j = 0;j<med.size();j++) {
						medPage.add(med.get(j));
				}
				}
	//			totalCount = medcineService.getMedcineFilterCount(standardSource, type, category, subcategory, name);
				totalCount = med.size();
				if (totalCount % pageSize == 0) {
					totalPage = totalCount / pageSize;
				} else {
					totalPage = totalCount / pageSize + 1;
				}
	
				Page p = new Page(pageNo, pageSize, totalPage, totalCount);
	
				if (med.size() == 0) {
					map.put("code", 0);
					map.put("msg", "无药品信息");
					map.put("result", medPage);
				} else if (med.size() > 0) {
					map.put("code", 0);
					map.put("msg", "success");
					map.put("page", p);
					map.put("result", medPage);
				} else {
					map.put("code", 10000);
					map.put("msg", "获取药品信息失败");
				}
	
				return map;
			} catch (Exception e) {
				// TODO: handle exception
				map.put("code", 0);
				map.put("msg", "无药品信息");
				return map;
			}
		
		}

	// 机构所有药品
	@RequestMapping(value = "/getallmedcinehos", method = RequestMethod.GET)
	public Map<String, Object> getAllMedcineHos(Integer standardSource, Integer type, Integer category,
			Integer subcategory, String name, @RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, Integer totalCount, Integer totalPage)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<>();
		List<Medcine> medcine = medcineService.getAllMedcineHos(pageNo, pageSize, standardSource, type, category,
				subcategory, name);
		totalCount = medcineService.getMedcineHosFilterCount(standardSource, type, category, subcategory, name);

		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}

		Page p = new Page(pageNo, pageSize, totalPage, totalCount);
		if (medcine.size() == 0) {
			map.put("code", 0);
			map.put("msg", "无药品信息");
			map.put("result", medcine);
		} else if (medcine.size() > 0) {
			map.put("code", 0);
			map.put("msg", "success");
			map.put("page", p);
			map.put("result", medcine);
		} else {
			map.put("code", 10000);
			map.put("msg", "获取药品信息失败");
		}
		return map;
	}

	@RequestMapping(value = "/getallmedcinefold", method = RequestMethod.GET)
	public Map<String, Object> getAllMedcineFold(String name) throws Exception {

		Map<String, Object> map = new LinkedHashMap<>();
		List<Medcine> medcine = medcineService.getAllMedcineFold(name);
		if (medcine.size() == 0) {
			map.put("code", 0);
			map.put("msg", "无药品信息");
			map.put("result", medcine);
		} else if (medcine.size() > 0) {
			map.put("code", 0);
			map.put("msg", "success");
			map.put("result", medcine);
		} else {
			map.put("code", 10000);
			map.put("msg", "获取药品信息失败");
		}

		return map;
	}

	// 选取某药品作为平台规范标准
	@RequestMapping(value = "/getmedcinebyid", method = RequestMethod.GET)
	public Map<String, Object> getMedcineById(String name, String specification) throws Exception {
		Map<String, Object> map = new LinkedHashMap<>();
		List<Medcine> medcine = medcineService.getMedcineById(name, specification);
		List<Medcine> medcine1 = new ArrayList<Medcine>();
		if (medcine.size() != 0) {
			medcine1.add(medcine.get(0));
		}
		for (int i = 0; i < medcine.size(); i++) {
			for (int j = 0; j < medcine1.size(); j++) {
				if (medcine.get(i).equals(medcine1.get(j))) {

					if (!medcine1.get(j).getId().equals(medcine.get(i).getId())) {
						String id = medcine1.get(j).getId() + "," + medcine.get(i).getId();
						medcine1.get(j).setId(id);
					}
					if (!medcine1.get(j).getStandardSource().toString().equals(medcine.get(i).getStandardSource())) {
						String source = medcine1.get(j).getStandardSource() + "," + medcine.get(i).getStandardSource();
						medcine1.get(j).setStandardSource(source);
					}
					if (!medcine1.get(j).getUnitName().equals(medcine.get(i).getUnitName())) {
						String source = medcine1.get(j).getUnitName() + "," + medcine.get(i).getUnitName();
//						  medcine1.get(j).setUnitName(source);
						UnitName u = new UnitName();
						u.setUnitName(source);
						medcine1.get(j).setUnitname(u);
					}
				} else if (!medcine1.contains(medcine.get(i))) {
					medcine1.add(medcine.get(i));
				}
			}
		}
		if (medcine.size() == 0) {
			map.put("code", 0);
			map.put("msg", "该药品信息暂无其他医院标准");
			map.put("result", medcine1);
		} else if (medcine1.size() > 0) {
			map.put("code", 0);
			map.put("msg", "success");
			map.put("result", medcine1);
		} else {
			map.put("code", 10000);
			map.put("msg", "获取药品信息失败");
		}
		return map;
	}

	// 更新机构药品
	@RequestMapping(value = "/savemedcinehos", method = RequestMethod.POST)
	public RespBean saveMedcineHos(@RequestBody(required = false) List<Medcine> med) throws Exception {
		if (medcineService.saveMedcineHos(med) > 0) {
			return RespBean.ok("更新成功!");
		}
		return RespBean.error("更新失败!,请检查传入参数格式");
	}

	// 更新平台药品
	@RequestMapping(value = "/savemedcine", method = RequestMethod.POST)
	public RespBean saveMedcine(@RequestBody(required = false) List<Medcine> med) throws Exception {
		try {
			List<Medcine> med2 = new ArrayList<Medcine>();
			for (int i = 0; i < med.size(); i++) {
				if (med.get(i).getId().contains(",")) {
					String[] split = med.get(i).getId().split(",");

					for (int j = 0; j < split.length; j++) {
						Medcine m = new Medcine();
						m.setId(split[j]);
						m.setPlatformStandard(med.get(i).getPlatformStandard());
						med2.add(m);
					}
				} else {
					Medcine m = new Medcine();
					m.setId(med.get(i).getId());
					m.setPlatformStandard(med.get(i).getPlatformStandard());
					med2.add(m);
				}
			}
			if (medcineService.saveMedcine(med2) > 0) {
				return RespBean.ok("更新成功!");
			}
			return RespBean.error("更新失败!,请检查传入参数格式");
		} catch (Exception e) {
			return RespBean.error("更新失败!,请检查传入参数格式");
		}
	}
}
