package com.ck.springboot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.ConfigMapper;
import com.ck.springboot.pojo.Config;
import com.ck.springboot.service.ConfigService;

@Service
public class ConfigServicelpml implements ConfigService{
	@Autowired ConfigMapper configMapper;

	@Override
	public List<Config> selectAllConfig() {
		// TODO Auto-generated method stub
		return configMapper.selectAllConfig();
	}

	@Override
	public int updateConfig(List<Config> config) {
		// TODO Auto-generated method stub
		return configMapper.updateConfig(config);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.ConfigService#selectAllRangeConfig()
	 */
	@Override
	public List<Config> selectAllRangeConfig() {
		// TODO Auto-generated method stub
		return configMapper.selectAllRangeConfig();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.ConfigService#updateRangeConfig(java.util.List)
	 */
	@Override
	public int updateRangeConfig(List<Config> config) {
		// TODO Auto-generated method stub
		return configMapper.updateRangeConfig(config);
	}
}
