package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.DeptMapper;
import com.ck.springboot.pojo.Dept;
import com.ck.springboot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class DeptServicelpml implements DeptService {
	@Autowired
    DeptMapper deptMapper;

	@Override
	public List<Dept> selectAllDept() {
		// TODO Auto-generated method stub
		return deptMapper.selectAllDept();
	}

	@Override
	public int updateDept(Dept dept) throws IOException {
		// TODO Auto-generated method stub
		try {
			return deptMapper.updateDept(dept);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int deleteDept(Integer deptId) {
		// TODO Auto-generated method stub
		return deptMapper.deleteDept(deptId);
	}

	@Override
	public int insertDept(Dept dept) throws IOException {
		// TODO Auto-generated method stub
		try {
			return deptMapper.insertDept(dept);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
		
	}

}
