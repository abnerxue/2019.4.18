/**
 * 
 */
package com.ck.springboot.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.DurationLimitMapper;
import com.ck.springboot.pojo.DurationLimit;
import com.ck.springboot.service.DurationLimitService;

/**
 * <p>
 * Description:
 * </p>
 * 
 * @author 胡高翔
 * @date 2019年3月11日
 */
@Service
public class DurationLimitServicelpml implements DurationLimitService {

	@Autowired
	DurationLimitMapper durationLimitMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DurationLimitService#selectByMedcineId(java.lang.
	 * Integer)
	 */
	@Override
	public List<DurationLimit> selectByMedcineId(Integer medcineId) {
		// TODO Auto-generated method stub
		return durationLimitMapper.selectByMedcineId(medcineId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DurationLimitService#deleteDurationById(java.lang.
	 * Integer)
	 */
	@Override
	public int deleteDurationById(Integer id) {
		// TODO Auto-generated method stub
		return durationLimitMapper.deleteDurationById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DurationLimitService#updateDurationById(com.ck.
	 * springboot.pojo.DurationLimit)
	 */
	@Override
	public int updateDurationById(DurationLimit dur) throws IOException {
		if (dur.getLowerbound() != null && dur.getUpperbound() != null) {
			if (dur.getLowerbound().compareTo(dur.getUpperbound()) == 1) {
				return -1;
			}
		}
		if (dur.getLowerbound() != null) {
			if (dur.getLowerbound().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		if (dur.getUpperbound() != null) {
			if (dur.getUpperbound().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		try {
			return durationLimitMapper.updateDurationById(dur);
		} catch (Exception e) {
			// TODO: handle exception
			return -3;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ck.springboot.service.DurationLimitService#insertDuration(com.ck.
	 * springboot.pojo.DurationLimit)
	 */
	@Override
	public int insertDuration(DurationLimit dur) throws IOException {
		if (dur.getLowerbound() != null && dur.getUpperbound() != null) {
			if (dur.getLowerbound().compareTo(dur.getUpperbound()) == 1) {
				return -1;
			}
		}
		if (dur.getLowerbound() != null) {
			if (dur.getLowerbound().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}
		if (dur.getUpperbound() != null) {
			if (dur.getUpperbound().compareTo(BigDecimal.ZERO) == -1) {
				return -2;
			}
		}

		try {
			return durationLimitMapper.insertDuration(dur);
		} catch (Exception e) {
			return -3;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.springboot.service.DurationLimitService#selectById(java.lang.Integer)
	 */
	@Override
	public List<DurationLimit> selectById(Integer id) {
		// TODO Auto-generated method stub
		return durationLimitMapper.selectById(id);
	}
}
