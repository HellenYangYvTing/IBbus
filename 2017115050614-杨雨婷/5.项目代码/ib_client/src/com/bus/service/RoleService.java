package com.bus.service;

import java.util.List;

import com.bus.dao.model.Role;

public interface RoleService {
	
    List<Role> findAllRoles();
}
