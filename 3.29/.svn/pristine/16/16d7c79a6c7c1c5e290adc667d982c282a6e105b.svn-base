/**
 * 
 */
package com.ck.springboot.service;

import com.ck.springboot.pojo.DurationLimit;

import java.io.IOException;
import java.util.List;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月11日  
*/
public interface DurationLimitService {
	public List<DurationLimit> selectByMedcineId(Integer medcineId);
	
	public List<DurationLimit> selectById(Integer Id);//编辑时通过主键获取用药天数限用信息
	
	public int deleteDurationById(Integer id);
	
	public int updateDurationById(DurationLimit dur) throws IOException;
	
	public int insertDuration(DurationLimit dur) throws IOException;
}
