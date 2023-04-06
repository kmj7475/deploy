package com.yedam.app.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	EmpMapper empMapper;
	
	@Override
	public List<EmpVO> getAllList() {
		return empMapper.selectAllEmpList();
	}
	
	@Override
	public EmpVO getEmpInfo(int empId) {
		return empMapper.selectEmpInfo(empId);
	}

	@Override
	public int insertEmpInfo(EmpVO empVO) {
		// 사원번호 구함
		
		// 사원정보 등록
		int result = empMapper.insertEmpInfo(empVO);
		return result;
	}



}
