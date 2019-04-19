package com.ck.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.CompatibilityMapper;
import com.ck.springboot.pojo.Compatibility;
import com.ck.springboot.pojo.CompatibilityCdC;
import com.ck.springboot.pojo.CompatibilityCdL;
import com.ck.springboot.pojo.CompatibilityLdL;
import com.ck.springboot.pojo.CompatibilityMdC;
import com.ck.springboot.pojo.CompatibilityMdL;
import com.ck.springboot.pojo.CompatibilityMdM;
import com.ck.springboot.service.CompatibilityService;
@Service
public class CompatibilityServicelpml implements CompatibilityService {
	@Autowired CompatibilityMapper compatibilityMapper;
	
	@Override
	public List<CompatibilityMdM> selectMdM(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdM(id);
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
	public List<CompatibilityMdC> selectMdC(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdC(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectLbl(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdL> selectMdL(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectMdL(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdM(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdC> selectCdM(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdM(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdC(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityCdC> selectCdC(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdC(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectCdL(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityCdL> selectCdL(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectCdL(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectLdM(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityMdL> selectLdM(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectLdM(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectLdC(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityCdL> selectLdC(Integer id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectLdC(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#selectLdL(java.lang.Integer)
	 */
	@Override
	public List<CompatibilityLdL> selectLdL(Integer Id) {
		// TODO Auto-generated method stub
		return compatibilityMapper.selectLdL(Id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityMdM(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityMdM(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityMdM(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityMdC(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityMdC(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityMdC(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityMdL(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityMdL(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityMdL(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityCdC(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityCdC(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityCdC(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityCdL(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityCdL(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityCdL(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#deleteCompatibilityLdL(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int deleteCompatibilityLdL(Integer id, Integer pairId) {
		// TODO Auto-generated method stub
		return compatibilityMapper.deleteCompatibilityLdL(id, pairId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityMdM(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityMdM(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityMdM(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityMdC(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityMdC(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityMdC(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityMdL(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityMdL(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityMdL(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityCdC(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityCdC(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityCdC(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityCdL(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityCdL(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityCdL(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#saveCompatibilityLdL(com.ck.springboot.pojo.Compatibility)
	 */
	@Override
	public int saveCompatibilityLdL(Compatibility com) {
		// TODO Auto-generated method stub
		return compatibilityMapper.saveCompatibilityLdL(com);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityMdM(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityMdM(Integer id,Integer pairId,Integer type,String medcineName,String medcinePairName) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityMdM(id, pairId, type,medcineName,medcinePairName);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityMdC(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityMdC(Integer id,Integer pairId,Integer type,String medcineName) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityMdC(id, pairId, type,medcineName);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityMdL(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityMdL(Integer id,Integer pairId,Integer type,String medcineName) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityMdL(id, pairId, type,medcineName);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityCdC(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityCdC(Integer id,Integer pairId,Integer type) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityCdC(id, pairId, type);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityCdL(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityCdL(Integer id,Integer pairId,Integer type) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityCdL(id, pairId, type);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.CompatibilityService#insertCompatibilityLdL(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertCompatibilityLdL(Integer id,Integer pairId,Integer type) {
		// TODO Auto-generated method stub
		return compatibilityMapper.insertCompatibilityLdL(id, pairId, type);
	}

}
