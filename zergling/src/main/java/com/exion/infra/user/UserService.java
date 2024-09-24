package com.exion.infra.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	public List<UserDto> selectUser(UserVo vo){
		List<UserDto> users = userDao.selectUser(vo);
		return users;
	}
	public List<UserDto> selectXdmUser(UserVo vo){
		List<UserDto> users = userDao.selectXdmUser(vo);
		return users;
	}
	public int listCount(UserVo vo) {
		return userDao.listCount(vo);
	}
	public int insert(UserDto userDto) {
		return userDao.insert(userDto);
	}
	
	public UserDto selectOne(UserDto userDto) {
		return userDao.selectOne(userDto);
	}
	public int insertUser(UserDto userDto) {
		return userDao.insertUser(userDto);
	}
	public int update(UserDto userDto) {
		System.out.println("그룹이름: "+userDto.getSeq());
		return userDao.update(userDto);
	}
}
