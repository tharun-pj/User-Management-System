package com.example.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.model.User;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;
import com.example.rest.service.UserService;
import com.example.rest.utility.CustomResponseBuilder;
import com.example.rest.utility.ErrorStructure;
import com.example.rest.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.media.*;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
public class UserController {
	
	private UserService userService;
	private CustomResponseBuilder responseBuilder;
	
	public UserController(UserService userService, CustomResponseBuilder responseBuilder) {
		super();
		this.userService = userService;
		this.responseBuilder = responseBuilder;
	}
	
	@Operation(description = "The API end point is used to save the user to the database "+"The end point will automatically create a unique identifier to avoid errors.",responses = {
			@ApiResponse(responseCode = "201", description = "User Created"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest);
		return responseBuilder.success(HttpStatus.CREATED, "user  created", userResponse);
	}
	
	@Operation(description = "The API end point is used to get the user from the database",responses = {
			@ApiResponse(responseCode = "302",description = "user found"),
			@ApiResponse(responseCode = "404",description = "failed to find the user",
			content = {
					@Content(schema = @Schema(anyOf = ErrorStructure.class))
			})
	})
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId) {
		UserResponse userResponse = userService.findUserById(userId);
		return responseBuilder.success(HttpStatus.FOUND, "user found", userResponse);
	}
	
	@Operation(description ="The API end point is used to update the user from the database", responses = {
			@ApiResponse(responseCode = "200",description = "user updated"),
			@ApiResponse(responseCode = "404",description = "failed to update user",
			content = {
					@Content(schema = @Schema(anyOf = ErrorStructure.class))
			})
	})
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest,@PathVariable int userId){
		UserResponse userResponse = userService.updateUser(userRequest,userId);
		return responseBuilder.success(HttpStatus.OK, "user updated", userResponse);
	}
	
	@Operation(description = "The API end point is used to delete the user from the database",responses = {
			@ApiResponse(responseCode = "200",description = "user deleted"),
			@ApiResponse(responseCode = "404",description = "failed to delete user",
			content = {
					@Content(schema = @Schema(anyOf = ErrorStructure.class))
			})
	})
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		UserResponse userResponse = userService.deleteUser(userId);
		return responseBuilder.success(HttpStatus.OK, "user deleted", userResponse);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(){
		List<User> allUsers = userService.findAllUsers();
		return responseBuilder.success(HttpStatus.FOUND, "user list found", allUsers);
	}
	
}
