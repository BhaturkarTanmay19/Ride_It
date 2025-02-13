package com.rideit.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rideit.bike.BikeDto;
import com.rideit.dao.AdminDao;
import com.rideit.dao.BikeDao;
import com.rideit.dao.CustomerDao;
import com.rideit.dao.OwnerDao;
import com.rideit.dto.BikeForOwnerDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.OwnerWithBikeReqDto;
import com.rideit.pojo.Admin;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Customer;
import com.rideit.pojo.Owner;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class AdminService implements IAdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public List<OwnerWithBikeReqDto> getAllOwners() {
		List<Owner> olist=ownerDao.findAllOwnerWithBikes();
//		System.out.println(olist);
//		if(olist!=null){
//			System.out.println(olist);
//		}
		List<OwnerWithBikeReqDto> ownerDtoList = new ArrayList<>();
		for(Owner ownerDetails: olist) {	
			System.out.println(ownerDetails);
			List<BikeForOwnerDto> bikeForOwnerDto = new ArrayList<>();
			OwnerWithBikeReqDto ownerDto = mapper.map(ownerDetails, OwnerWithBikeReqDto.class);
			ownerDto.setOwnerName(ownerDetails.getName());
			
			for(Bike b: ownerDetails.getBikes()) {
				BikeForOwnerDto dto = mapper.map(b, BikeForOwnerDto.class);
				bikeForOwnerDto.add(dto);
			}
			ownerDto.setBikes(bikeForOwnerDto);
			ownerDtoList.add(ownerDto);
		
		}
		return ownerDtoList;
	}

	@Override
	public List<BikeDto> getAllBike() {
		List<Bike> bikeList = bikeDao.findAll();
		List<BikeDto> bikeListDto = new ArrayList<>();
		for(Bike bike: bikeList) {
			BikeDto bikeDto = mapper.map(bike, BikeDto.class);
			bikeDto.setOwnerId(bike.getOwner().getOwnerId());
			bikeListDto.add(bikeDto);
		}
		return bikeListDto;
	}

	@Override
	public List<GetCustomerResponseDto> getAllCustomer() {
		List<Customer> customerList = customerDao.findAll();
		List<GetCustomerResponseDto> customerDtoList = new ArrayList<>();
		for(Customer customer: customerList) {
			GetCustomerResponseDto customerDto = mapper.map(customer, GetCustomerResponseDto.class);
//			customerList.add(customer);
//			customerDto.setProfilePhoto(customer);;
			customerDtoList.add(customerDto);
		}
		return customerDtoList;
	}

	@Override
	public String registerAdmin(Admin registerDto) {
		registerDto.setPassword(encoder.encode(registerDto.getPassword()));
		adminDao.save(registerDto);
		return "Admin registerd";
	}

}
