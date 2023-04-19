package com.clevercinema.services;

import com.clevercinema.dto.RegisterDto;

public interface UserService {

	boolean save(RegisterDto userDto);
	
}
