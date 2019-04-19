package com.ck.springboot.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ck.springboot.mapper.AuditCenterMapper;
import com.ck.springboot.pojo.AuditCenter;
import com.ck.springboot.pojo.District;
import com.ck.springboot.pojo.UnitName;
import com.ck.springboot.service.AuditCenterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuditCenterServicelpml implements AuditCenterService {

	@Autowired AuditCenterMapper auditCenterMapper;
	
	@Override
	public List<AuditCenter> selectAllAuditCenter() {
		// TODO Auto-generated method stub
		return auditCenterMapper.selectAllAuditCenter();
	}

	@Override
	public int deleteCenterByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return auditCenterMapper.deleteCenterByPrimaryKey(id);
	}

	@Override
	public int insertCenter(AuditCenter center) {
		// TODO Auto-generated method stub
		return auditCenterMapper.insertCenter(center);
	}

	@Override
	public int updateAuditCenter(AuditCenter center) throws IOException {
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.updateAuditCenter(center);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public List<String> selectAllAuditArea() {
		// TODO Auto-generated method stub
		return auditCenterMapper.selectAllAuditArea();
	}

	@Override
	public int insertHospital(UnitName unitname)  throws IOException{
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.insertHospital(unitname);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public int insertAuditCenter(AuditCenter center) throws IOException{
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.insertAuditCenter(center);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int deleteHospital(Integer id) {
		// TODO Auto-generated method stub
		return auditCenterMapper.deleteHospital(id);
	}

	@Override
	public int updateHospital(UnitName unitname) throws IOException{
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.updateHospital(unitname);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public List<UnitName> selectAllUnitName(Integer id) {
		// TODO Auto-generated method stub
		return auditCenterMapper.selectAllUnitName(id);
	}
	@Override
	public List<District> selectDistrict(){
		// TODO Auto-generated method stub
		return auditCenterMapper.selectDistrict();
	}

	@Override
	public List<AuditCenter> selectAuditCenter(Integer id) {
		// TODO Auto-generated method stub
		return auditCenterMapper.selectAuditCenter(id);
	}

	@Override
	public int insertDistrict(District district) throws IOException{
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.insertDistrict(district);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteDistrict(Integer districtId) {
		// TODO Auto-generated method stub
		return auditCenterMapper.deleteDistrict(districtId);
	}
	@Override
	public int updateDistrict(District district) throws IOException {
		// TODO Auto-generated method stub
		try {
			return auditCenterMapper.updateDistrict(district);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
	}
}
