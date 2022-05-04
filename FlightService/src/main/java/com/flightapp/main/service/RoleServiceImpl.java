package com.flightapp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.main.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

}
