package com.ck.springboot.mapper;

import com.ck.springboot.pojo.AuditCenter;
import com.ck.springboot.pojo.District;
import com.ck.springboot.pojo.UnitName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditCenterMapper {
    int insertCenter(AuditCenter record);

    int insertSelective(AuditCenter record);

    AuditCenter selectByPrimaryKey(Integer id);
    
    List<AuditCenter> selectAllAuditCenter();

    int updateAuditCenter(AuditCenter record);

    int updateCenterByPrimaryKey(AuditCenter record);
    
    public List<String> selectAllAuditArea();
    
    public List<District> selectDistrict();//获取所有审方区域
    
	public int insertDistrict(District district);//增加区域
	
	public int deleteDistrict(@Param("districtId") Integer districtId);//删除审方区域
	
	public int updateDistrict(District district);//更新区域
	
    public List<UnitName> selectAllUnitName(@Param("id") Integer id);//获取所有审方医院
    
    public List<AuditCenter> selectAuditCenter(@Param("id") Integer id);//获取医院审方中心
    
    public int insertHospital(UnitName unitname);//增加医院
    
	public int updateHospital(UnitName unitname);//更新医院
	
//	public int selectHospitalCounts(UnitName unitname);//重命名时查询医院名是否重复**
    
    public int deleteHospital(Integer id);//删除审方医院
    
    public int insertAuditCenter(AuditCenter center);//审方中心
    
    int deleteCenterByPrimaryKey(Integer id); //删除审方中心
}