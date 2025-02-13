package com.rideit.services;

import java.util.List;

import com.rideit.bike.BikeDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.OwnerWithBikeReqDto;
import com.rideit.pojo.Admin;

public interface IAdminService {
	List<OwnerWithBikeReqDto> getAllOwners();
	List<BikeDto> getAllBike();
	List<GetCustomerResponseDto> getAllCustomer();
	String registerAdmin(Admin registerDto);
	//
}
