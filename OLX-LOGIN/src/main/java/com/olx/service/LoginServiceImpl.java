package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.olx.dto.Users;
import com.olx.entity.BlackListedTokensDocument;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidCredentialsException;
import com.olx.repository.BlackListTokenRepo;
import com.olx.repository.UserRepo;
import com.olx.security.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BlackListTokenRepo blackListTokenRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	public String authenticate(Users users) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
		} catch (AuthenticationException exception) {
			throw new InvalidCredentialsException(exception.toString());
		}
		String jwt = jwtUtil.generateToken(users.getUsername());
		return jwt;
	}

	
	 @Override public boolean logout(String authToken) { 
		 BlackListedTokensDocument blackListedTokensDocument = 
				 blackListTokenRepo.findByAuthToken(authToken);
		 if(blackListedTokensDocument != null) 
			 return false; 
		 BlackListedTokensDocument newToken = new BlackListedTokensDocument(authToken);
		 blackListTokenRepo.save(newToken); 
		 return true; 
	 }
	 

	@Override
	public Users registerUser(Users users) {
		UserEntity userEntity = convertDTOIntoEntity(users);
		userEntity = userRepo.save(userEntity);
		return convertEntityIntoDTO(userEntity);
	}

	@Override
	public boolean validate(String authToken) {
		try {
			authToken = authToken.substring(7);
			String username = jwtUtil.extractUsername(authToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return jwtUtil.validateToken(authToken, userDetails);
		} catch (InvalidAuthTokenException ie) {
			throw new InvalidAuthTokenException(authToken);
		}
			
	}
		
	
	@Override
	public Users getUserDetail(String authToken) {
		boolean validate = validate(authToken);
		if (validate) {
			String username = jwtUtil.extractUsername(authToken.substring(7));
			List<UserEntity> userEntity = userRepo.findByUsername(username);
			return convertEntityIntoDTO(userEntity.get(0));
		}
		else {
			throw new InvalidAuthTokenException(authToken);
		}
	}
	
	@Override
	public List<Users> getAllUsers(String authToken) {
		try {
			List<UserEntity> userEntity = userRepo.findAll();
			List<Users> userDto = convertEntityListIntoDTOList(userEntity);
			return userDto;
		} catch (InvalidAuthTokenException ie) {
			throw new InvalidAuthTokenException(authToken);
		}
	}
		

	@Override
	public Users getUserById(String authToken, int id) throws InvalidAuthTokenException{
		UserEntity userEntity = userRepo.findById(id);
		Users users = convertEntityIntoDTO(userEntity);
		return users;
	}
	
	@Override
	public Users getUserByUsername(String authToken) throws InvalidCredentialsException{
		String username = jwtUtil.extractUsername(authToken);
		UserEntity userEntity = (UserEntity) userRepo.findByUsername(username);
		Users users = convertEntityIntoDTO(userEntity);
		return users;
	}

	
	private List<Users> convertEntityListIntoDTOList(List<UserEntity> userEntityList) {
		List<Users> userDtoList = new ArrayList<Users>();
			for(UserEntity userEntity: userEntityList) {
				Users usersDto = convertEntityIntoDTO(userEntity);
				userDtoList.add(usersDto);
			}
		return userDtoList;
	}
	
	private UserEntity convertDTOIntoEntity(Users users) {
		UserEntity userEntity = modelMapper.map(users, UserEntity.class);
		return userEntity;
	}
	
	private Users convertEntityIntoDTO(UserEntity userEntity) {
		Users users = modelMapper.map(userEntity, Users.class);
		return users;
	}







}
