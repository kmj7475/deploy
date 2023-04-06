package com.yedam.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@RequestMapping(value="/empList", method=RequestMethod.GET)
	//@GetMapping("empList")
	public String empAllList(Model model) {
		model.addAttribute("empList", empService.getAllList());
		return "empList";
	}
	
	@GetMapping("/empInsert")
	public String inputEmpForm() {
		
		return "empInsert";
	}
	
	@PostMapping("/empInsert")
	public String inputEmpProcess(EmpVO empVO) {
		empService.insertEmpInfo(empVO);
		return "redirect:empList";
	}
}
