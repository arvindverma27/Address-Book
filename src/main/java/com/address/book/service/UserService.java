package com.address.book.service;

import java.util.List;

import com.address.book.dao.UserDAO;
import com.address.book.dto.UserDTO;

public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	public int save(UserDTO userDTO) throws Exception {

		int count = userDAO.save(userDTO);

		if (count > 0) {

		}

		return count;
	}

	public int update(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);

	}

	public UserDTO findById(int id) throws Exception {
		return userDAO.findById(id);

	}

//	public UserDTO findByMobileNumber(String MobileNumber) throws Exception {
//		return userDAO.findByMobileNumber(MobileNumber);
//
//	}
	
	public List<UserDTO> searchContactByKeyword(String keyword) throws Exception {
		return userDAO.searchContactByKeyword(keyword);

	}


	
	public List<UserDTO> findAll() throws Exception {
		return userDAO.findAll();

	}

	public int deleteById(int id) throws Exception {
		return userDAO.deleteById(id);

	}

	
}
