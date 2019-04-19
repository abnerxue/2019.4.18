package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.MedcineMapper;
import com.ck.springboot.pojo.CompatibilitySelect;
import com.ck.springboot.pojo.Medcine;
import com.ck.springboot.pojo.MedcineOnly;
import com.ck.springboot.pojo.pojomore.MedcineMore;
import com.ck.springboot.service.MedcineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class MedcineServiceImpl implements MedcineService {
	@Autowired
	MedcineMapper medcineMapper;
	
	@Override
	public List<Medcine> getAllMedcineFold(String name) {
		// TODO Auto-generated method stub
		return medcineMapper.getAllMedcineFold(name);
	}

	@Override
	public int getMedcineFilterCount(Integer standardSource, Integer type, Integer category, Integer subcategory,String name) {
		// TODO Auto-generated method stub
		return medcineMapper.getMedcineFilterCount(standardSource,type,category,subcategory,name);
	}

	@Override
	public List<MedcineOnly> getAllMedcine(Integer pageNo,Integer pageSize,Integer standardSource, Integer type, Integer category, Integer subcategory,
			String name) {
		// TODO Auto-generated method stub
		return medcineMapper.getAllMedcine(pageNo,pageSize,standardSource, type, category, subcategory, name);
	}

	@Override
	public List<Medcine> getAllMedcineHos(Integer pageNo,Integer pageSize,Integer standardSource, Integer type, Integer category,
			Integer subcategory, String name) {
		// TODO Auto-generated method stub
		return medcineMapper.getAllMedcineHos(pageNo,pageSize,standardSource, type, category, subcategory, name);
	}

	@Override
	public int getMedcineHosFilterCount(Integer standardSource, Integer type, Integer category, Integer subcategory,
			String name) {
		// TODO Auto-generated method stub
		return medcineMapper.getMedcineHosFilterCount(standardSource, type, category, subcategory, name);
	}

	@Override
	public int saveMedcineHos(List<Medcine> med) throws Exception {
		// TODO Auto-generated method stub
		try {
			return medcineMapper.saveMedcineHos(med);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
		
	}
	@Override
	public List<Medcine> getMedcineById(String name, String specification) {
		// TODO Auto-generated method stub
		return medcineMapper.getMedcineById(name, specification);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineService#saveMedcine(java.util.List)
	 */
	@Override
	public int saveMedcine(List<Medcine> med) throws Exception {
		// TODO Auto-generated method stub
		try {
			return medcineMapper.saveMedcine(med);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}

	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineService#selectMedcine()
	 */
	@Override
	public List<CompatibilitySelect> selectMedcine() {
		// TODO Auto-generated method stub
		return medcineMapper.selectMedcine();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineService#selectCategory()
	 */
	@Override
	public List<CompatibilitySelect> selectCategory() {
		// TODO Auto-generated method stub
		return medcineMapper.selectCategory();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineService#selectLabel()
	 */
	@Override
	public List<CompatibilitySelect> selectLabel() {
		// TODO Auto-generated method stub
		return medcineMapper.selectLabel();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineService#getMedcineNameById(java.lang.Integer)
	 */
	@Override
	public String getMedcineNameById(Integer id) {
		return medcineMapper.getMedcineNameById(id);
	}

	@Override
	public com.ck.springboot.pojo.pojomore.MedcineMore getMedcineByName(String commonName,String hospitalName) {
		List<com.ck.springboot.pojo.pojomore.MedcineMore> medcineList = medcineMapper.getMedcineByName(commonName,hospitalName);
		if (CollectionUtils.isEmpty(medcineList)){
			return null;
		}
		return medcineList.get(0);
	}
}