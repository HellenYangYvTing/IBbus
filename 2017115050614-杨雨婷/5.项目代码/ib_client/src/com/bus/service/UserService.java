package com.bus.service;

import java.util.List;
import java.util.Map;

import com.bus.dao.model.User;

public interface UserService {

	public String login(User user);
	
	public List<String> getUserAuthorityList(String userName);
	
	public String save(Map<String,List<User>> dataMapList);
	
	public List<User> find(User condition);
	
	public void changePass(String userCode, String pass);
	
	/*public User findUserByName(String userName);*/
}
