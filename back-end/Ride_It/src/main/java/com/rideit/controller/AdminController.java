package com.rideit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideit.dto.ApiResponse;
import com.rideit.pojo.Admin;
import com.rideit.services.IAdminService;

import jakarta.persistence.PostLoad;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@GetMapping("/getallowner")
	public ResponseEntity<?> getAllOwner(){
		try {
			return ResponseEntity.ok(adminService.getAllOwners());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/getallbikes")
	public ResponseEntity<?> getAllBikes(){
		try {
			return ResponseEntity.ok(adminService.getAllBike());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@GetMapping("/getallcustomer")
	public ResponseEntity<?> getAllCustomer(){
		try {
			return ResponseEntity.ok(adminService.getAllCustomer());
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerAdmin(@RequestBody Admin adminDetails){
		try {
			return ResponseEntity.ok(adminService.registerAdmin(adminDetails));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
}
