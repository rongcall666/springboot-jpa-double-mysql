package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.test1.UserRepository;
import com.example.demo.dao.test2.DepartmentRepository;
import com.example.demo.entity.test1.Users;
import com.example.demo.entity.test2.Department;

@RestController
public class Index {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/")
	public String index() {
		
		Users users = new Users();
		users.setName("haha");;
		userRepository.save(users);
		
		Department department = new Department();
		department.setName("kaifa");
		department.setTest("test");
		departmentRepository.save(department);
		return "success";
	}
	
}
