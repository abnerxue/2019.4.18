package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.CompatibilityMapper;
import com.ck.springboot.pojo.*;
import com.ck.springboot.service.CompatibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityServicelpml implements CompatibilityService {
	@Autowired
    CompatibilityMapper compatibilityMapper;
	
	@Override
	public List<CompatibilityMdM> selectMdM(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdM(Id);
	}

	@Override
	public int deleteCompatibilityById(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityById(id);
	}

	@Override
	public int insertCompatibility(Integer medcinePairId,
			 Integer medcineId,String reactionDescription,Integer action) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibility(medcinePairId,medcineId,reactionDescription,action);
	}

	@Override
	public int saveCompatibility(List<CompatibilityMdM> com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibility(com);
	}

	@Override
	public int repeatVerification(Integer medcinePairId, Integer medcineId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.repeatVerification(medcinePairId, medcineId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCategory(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdC> selectMdC(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdC(Id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectLbl(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdL> selectMdL(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdL(Id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdM(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdC> selectCdM(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdM(Id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdC(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityCdC> selectCdC(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdC(Id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdL(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityCdL> selectCdL(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdL(Id);
	}

}
