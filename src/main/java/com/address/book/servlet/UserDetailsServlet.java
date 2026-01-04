package com.address.book.servlet;

import java.util.List;

import com.address.book.dao.UserDAO;
import com.address.book.dto.UserDTO;
import com.address.book.service.UserService;
import com.address.book.util.DBUtil;

public class UserDetailsServlet {

	private UserService userService;

	public UserDetailsServlet() {
		DBUtil dbUtil = new DBUtil();

		UserDAO userDAO = new UserDAO(dbUtil);

		this.userService = new UserService(userDAO);

	}

	public void save() {

		try {

			UserDTO userDTO = new UserDTO();

			
			
			userDTO.setFirstName("ambika");
			userDTO.setLastName("Nagar");
			userDTO.setEmail("ambika123@gmail.com");
			userDTO.setMobileNumber("5343890231");
			userDTO.setGender("Female");
			userDTO.setAddress("Bholaram Indore");
			userDTO.setCityName("Indore");
			
			int count = userService.save(userDTO);

			if (count > 0) {
				System.out.println("User Created SuccessFully");
			} else {
				System.out.println("User Not Created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update() {

		try {

			UserDTO userDTO = new UserDTO();

			

		
			userDTO.setId(3);

			userDTO.setCityId(3);
				
			
		
			int count = userService.update(userDTO);

			

			if (count > 0) {
				System.out.println("User Updated SuccessFully");
			} else {
				System.out.println("Failed To Update User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteById() {

		try {


			int id = 1;

			int count = userService.deleteById(id);

			if (count > 0) {
				System.out.println("User deleted SuccessFully");
			} else {
				System.out.println("Failed To Delete User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void findById() {

		try {


			int id = 1;

			UserDTO userDTO = userService.findById(id);

			if ( userDTO != null) {
				System.out.println("User found SuccessFully");
				
				System.out.println(" FirstName : " +userDTO.getFirstName());
				System.out.println("LastName :" + userDTO.getLastName());
				System.out.println("Email :" + userDTO.getEmail());
				System.out.println("Mobile Number :" + userDTO.getMobileNumber());
				System.out.println("Gender :" + userDTO.getGender());
				System.out.println("Address :" + userDTO.getAddress());
				System.out.println("CityName :" + userDTO.getCityName());
				
				
			} else {
				System.out.println("Failed To Found User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
//	public void findByMobileNumber() {
//
//		try {
//
//
//			String MobieNumber = "6751";
//
//			UserDTO userDTO = userService.findByMobileNumber(MobieNumber);
//
//			if ( userDTO != null) {
//				System.out.println("User found SuccessFully");
//				
//				System.out.println("FirstName : " +userDTO.getFirstName());
//				System.out.println("LastName :" + userDTO.getLastName());
//				System.out.println("Email :" + userDTO.getEmail());
//				System.out.println("Mobile Number :" + userDTO.getMobileNumber());
//				System.out.println("Gender :" + userDTO.getGender());
//				System.out.println("Address :" + userDTO.getAddress());
//				System.out.println("CityName :" + userDTO.getCityName());
//				
//				
//			} else {
//				System.out.println("Failed To Found User");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	
//	}
	
	public void searchContactByKeyword() {

		try {


			String keyword = "c";

			List<UserDTO> userDTOList = userService.searchContactByKeyword(keyword);

			if( !userDTOList.isEmpty()) {
				
				for (UserDTO userDTO : userDTOList) {
				System.out.println("User found SuccessFully");
				
				System.out.println("FirstName : " +userDTO.getFirstName());
				System.out.println("LastName :" + userDTO.getLastName());
				System.out.println("Email :" + userDTO.getEmail());
				System.out.println("Mobile Number :" + userDTO.getMobileNumber());
				System.out.println("Gender :" + userDTO.getGender());
				System.out.println("Address :" + userDTO.getAddress());
				System.out.println("CityName :" + userDTO.getCityName());
				
				
				System.out.println("---------------------------------");
				
				}
				
				
			}  else{
				System.out.println("Failed To Found User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	
	
	public void findAll() {

		try {


			

			List<UserDTO> userDTOList = userService.findAll();

			if( !userDTOList.isEmpty()) {
				
				for (UserDTO userDTO : userDTOList) {
				System.out.println("User found SuccessFully");
				
				System.out.println("FirstName : " +userDTO.getFirstName());
				System.out.println("LastName :" + userDTO.getLastName());
				System.out.println("Email :" + userDTO.getEmail());
				System.out.println("Mobile Number :" + userDTO.getMobileNumber());
				System.out.println("Gender :" + userDTO.getGender());
				System.out.println("Address :" + userDTO.getAddress());
				System.out.println("CityName :" + userDTO.getCityName());
				
				
				System.out.println("---------------------------------");
				
				}
				
				
			}  else{
				System.out.println("Failed To Found User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	
	
	public static void main(String[] args) {
		
		UserDetailsServlet main = new UserDetailsServlet();
		
		//main.save();
		//main.update();
		//main.findById();
		//main.findByMobileNumber();
		main.searchContactByKeyword();
		//main.findAll();
	}

}
