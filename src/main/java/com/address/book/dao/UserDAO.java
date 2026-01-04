package com.address.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.address.book.dto.UserDTO;
import com.address.book.util.DBUtil;

public class UserDAO {

	private final String INSERT_USER = "INSERT INTO user (city_id, first_name, last_name, email, mobile_no, gender, address) "
			+ "SELECT city.id, ?, ?, ?, ?, ?, ? " + "FROM city WHERE city.city_name = ?";

	private final String UPDATE_USER = "update user set first_name=?, last_name=?, email=?, mobile_no=?, gender=?, address=?, city_id = COALESCE((SELECT id FROM city WHERE city_name = ?), city_id) WHERE id = ?";

//	private final String SELECT_USER_BY_MOBILE = "SELECT user.id, user.first_name, user.last_name, user.email, user.mobile_no, "
//			+ "user.gender, user.address, user.city_id, city.city_name " + "FROM user "
//			+ "INNER JOIN city ON user.city_id = city.id " + "WHERE user.mobile_no = ?";

	private final String DELETE_USER = "delete from user where id = ?";

	private final String SELECT_USER_BY_ID = "SELECT user.id, user.first_name, user.last_name, user.email, user.mobile_no, "
			+ "user.gender, user.address, user.city_id, city.city_name " + "FROM user "
			+ "INNER JOIN city ON user.city_id = city.id " + "WHERE user.id = ?";

	private final String SELECT_USER_BY_KEYWORD = "SELECT user.id, user.first_name, user.last_name, user.email, user.mobile_no, "
			+ "user.gender, user.address, city.city_name " + "FROM user " + "LEFT JOIN city ON user.city_id = city.id "
			+ "WHERE user.first_name LIKE ? " + "OR user.last_name LIKE ? " + "OR city.city_name LIKE ?"
			+ "OR user.mobile_no LIKE ?";

	private final String FIND_ALL = "SELECT user.id, user.first_name, user.last_name, user.email, user.mobile_no, "
			+ "user.gender, user.address, city.city_name " + "FROM `user` "
			+ "INNER JOIN city ON user.city_id = city.id";

	private DBUtil dbUtil;

	public UserDAO(DBUtil dbUtil) {

		this.dbUtil = dbUtil;
	}

	public int save(UserDTO userDTO) throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		int count = 0;

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(INSERT_USER);

			pstmt.setString(1, userDTO.getFirstName());
			pstmt.setString(2, userDTO.getLastName());
			pstmt.setString(3, userDTO.getEmail());
			pstmt.setString(4, userDTO.getMobileNumber());
			pstmt.setString(5, userDTO.getGender());
			pstmt.setString(6, userDTO.getAddress());
			pstmt.setString(7, userDTO.getCityName());

			count = pstmt.executeUpdate();

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection);
		}
		return count;

	}

	public int update(UserDTO userDTO) throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		int count = 0;

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(UPDATE_USER);

		
			pstmt.setString(1, userDTO.getFirstName());
			pstmt.setString(2, userDTO.getLastName());
			pstmt.setString(3, userDTO.getEmail());
			pstmt.setString(4, userDTO.getMobileNumber());
			pstmt.setString(5, userDTO.getGender());
			pstmt.setString(6, userDTO.getAddress());
			pstmt.setString(7, userDTO.getCityName());
			pstmt.setInt(8, userDTO.getId());

			count = pstmt.executeUpdate();

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection);
		}
		return count;

	}

	public UserDTO findById(int id) throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		UserDTO userDTO = null;

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(SELECT_USER_BY_ID);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				userDTO = new UserDTO();

				userDTO.setId(rs.getInt("id"));
				userDTO.setFirstName(rs.getString("first_name"));
				userDTO.setLastName(rs.getString("last_name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setMobileNumber(rs.getString("mobile_no"));
				userDTO.setGender(rs.getString("gender"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCityName(rs.getString("city_name"));

			}

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection, rs);
		}
		return userDTO;

	}

//	public UserDTO findByMobileNumber(String MobileNumber) throws Exception {
//
//		Connection connection = null;
//
//		PreparedStatement pstmt = null;
//
//		ResultSet rs = null;
//
//		UserDTO userDTO = null;
//
//		try {
//
//			connection = dbUtil.getConnection();
//
//			pstmt = connection.prepareStatement(SELECT_USER_BY_MOBILE);
//
//			pstmt.setString(1, MobileNumber);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//
//				userDTO = new UserDTO();
//				userDTO.setId(rs.getInt("id"));
//				userDTO.setFirstName(rs.getString("first_name"));
//				userDTO.setLastName(rs.getString("last_name"));
//				userDTO.setEmail(rs.getString("email"));
//				userDTO.setMobileNumber(rs.getString("mobile_no"));
//				userDTO.setGender(rs.getString("gender"));
//				userDTO.setAddress(rs.getString("address"));
//				userDTO.setCityName(rs.getString("city_name"));
//
//			}
//
//		} catch (Exception e) {
//
//			throw e;
//		} finally {
//
//			dbUtil.close(pstmt, connection, rs);
//		}
//		return userDTO;
//
//	}

	public List<UserDTO> searchContactByKeyword(String keyword) throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<UserDTO> userDTOContactList = new ArrayList<UserDTO>();

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(SELECT_USER_BY_KEYWORD);

			String pattern = "%" + keyword + "%";
			pstmt.setString(1, pattern);
			pstmt.setString(2, pattern);
			pstmt.setString(3, pattern);
			pstmt.setString(4, pattern);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				UserDTO userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setFirstName(rs.getString("first_name"));
				userDTO.setLastName(rs.getString("last_name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setMobileNumber(rs.getString("mobile_no"));
				userDTO.setGender(rs.getString("gender"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCityName(rs.getString("city_name"));
				userDTOContactList.add(userDTO);

			}

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection, rs);
		}
		return userDTOContactList;

	}

	public List<UserDTO> findAll() throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<UserDTO> userDTOList = new ArrayList<UserDTO>();

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(FIND_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				UserDTO userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setFirstName(rs.getString("first_name"));
				userDTO.setLastName(rs.getString("last_name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setMobileNumber(rs.getString("mobile_no"));
				userDTO.setGender(rs.getString("gender"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCityName(rs.getString("city_name"));
				userDTOList.add(userDTO);

			}

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection, rs);
		}
		return userDTOList;

	}

	public int deleteById(int id) throws Exception {

		Connection connection = null;

		PreparedStatement pstmt = null;

		UserDTO userDTO = null;

		int count = 0;

		try {

			connection = dbUtil.getConnection();

			pstmt = connection.prepareStatement(DELETE_USER);

			pstmt.setInt(1, id);

			count = pstmt.executeUpdate();

		} catch (Exception e) {

			throw e;
		} finally {

			dbUtil.close(pstmt, connection);
		}
		return count;

	}

}
