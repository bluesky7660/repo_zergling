package com.exion.infra.user;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	public List<UserDto> selectUser();
	public int insert(UserDto userDto);
	public int insertUser(UserDto userDto);
}
