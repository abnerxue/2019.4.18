/**
 * 
 */
package com.ck.springboot.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.DosageLimitMapper;
import com.ck.springboot.pojo.DosageLimit;
import com.ck.springboot.service.DosageLimitService;

/**
 * <p>
 * Description:
 * </p>
 * 
 * @author 胡高翔
 * @date 2019年3月11日
 */
@Service
public class DosageLimitServicelpml implements DosageLimitService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DosageLimitService#selectByMedcineId(java.lang.
	 * Integer)
	 */
	@Autowired
	DosageLimitMapper dosageLimitMapper;

	@Override
	public List<DosageLimit> selectByMedcineId(Integer medcineId) {
		// TODO Auto-generated method stub
		return dosageLimitMapper.selectByMedcineId(medcineId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ck.springboot.service.DosageLimitService#deleteDosageById(java.lang.
	 * Integer)
	 */
	@Override
	public int deleteDosageById(Integer id) {
		// TODO Auto-generated method stub
		return dosageLimitMapper.deleteDosageById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ck.springboot.service.DosageLimitService#updateDosageById(com.ck.
	 * springboot.pojo.DosageLimit)
	 */
	@Override
	public int updateDosageById(DosageLimit dos) throws IOException {
		// TODO Auto-generated method stub
		if (dos.getDosagePre() != null && dos.getDoesagePost() != null) {
			if (dos.getDosagePre().compareTo(dos.getDoesagePost()) == 1) {
				return -1;
			}
		}
		if (dos.getDosagePre() != null) {
			if (dos.getDosagePre().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		if (dos.getDoesagePost() != null) {
			if (dos.getDoesagePost().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		try {
			return dosageLimitMapper.updateDosageById(dos);
		} catch (Exception e) {
			// TODO: handle exception
			return -3;
		}

	}

	@Override
	public int insertDosage(DosageLimit dos) throws IOException {
		// TODO Auto-generated method stub
		if (dos.getDosagePre() != null && dos.getDoesagePost() != null) {
			if (dos.getDosagePre().compareTo(dos.getDoesagePost()) == 1) {
				return -1;
			}
		}
		if (dos.getDosagePre() != null) {
			if (dos.getDosagePre().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		if (dos.getDoesagePost() != null) {
			if (dos.getDoesagePost().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		try {
			return dosageLimitMapper.insertDosage(dos);
		} catch (Exception e) {
			// TODO: handle exception
			return -3;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DosageLimitService#selectById(java.lang.Integer)
	 */
	@Override
	public List<DosageLimit> selectById(Integer id) {
		// TODO Auto-generated method stub
		return dosageLimitMapper.selectById(id);
	}

}
