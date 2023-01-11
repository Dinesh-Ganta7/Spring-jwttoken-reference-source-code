package com.alibou.security.service;

import com.alibou.security.dto.UserDTO;

public interface UserService {

	public UserDTO getUser(String email);

}
