package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.rest.exception.UserNotFoundByIdException;
import com.example.rest.exceptionhandler.UserExceptionHandler;
import com.example.rest.mapper.UserMapper;
import com.example.rest.model.User;
import com.example.rest.repo.UserRepo;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;

@Service
public class UserService {
	
	private UserRepo userRepo;
	private UserMapper userMapper;

	public UserService(UserRepo userRepo, UserMapper userMapper) {
		super();
		this.userRepo = userRepo;
		this.userMapper = userMapper;
	}

	public UserResponse saveUser(UserRequest userRequest) {
		User user = userMapper.mapToUserEntity(userRequest,new User());
		user = userRepo.save(user);
		return userMapper.mapToUserResponse(user);
	}

	public UserResponse findUserById(int userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			return userMapper.mapToUserResponse(optional.get());
		}
		else throw new UserNotFoundByIdException("Failed to find user");
	}

	public UserResponse updateUser(UserRequest userRequest, int userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			User user = userMapper.mapToUserEntity(userRequest, optional.get());
			user = userRepo.save(user);
			return userMapper.mapToUserResponse(user);
		}
		else {
			throw new UserNotFoundByIdException("failed to update");
		}
	}

	public UserResponse deleteUser(int userId) {
		Optional<User> optional = userRepo.findById(userId);
		
		if(optional.isPresent()) {
			User user = optional.get();
			userRepo.delete(user);
			return userMapper.mapToUserResponse(user);
		}
		else {
			throw new UserNotFoundByIdException("Failed to delete user");
		}
	}

	public List<User> findAllUsers() {
		List<User> list = userRepo.findAll();
		return list;
	}

}
