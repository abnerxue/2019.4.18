package com.ck.springboot.service;

import java.util.List;
import com.ck.springboot.pojo.Config;



public interface ConfigService {
	
	public List<Config> selectAllConfig();
	public List<Config> selectAllRangeConfig();
//	public int updateconfig(Config record);
	
	public int updateConfig(List<Config> config);//批量更新
	public int updateRangeConfig(List<Config> config);//批量更新---范围 设置
}
