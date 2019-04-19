/**
 * 
 */
package com.ck.springboot.service;

import com.ck.springboot.pojo.DosageLimit;

import java.io.IOException;
import java.util.List;


/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月11日  
*/
public interface DosageLimitService {
	
	public List<DosageLimit> selectByMedcineId(Integer medcineId);
	
	public List<DosageLimit> selectById(Integer id);//编辑时通过主键获取限用信息
	
	public int deleteDosageById(Integer id);
	
	public int updateDosageById(DosageLimit dos) throws IOException;
	
	public int insertDosage(DosageLimit dos) throws IOException;
} 
