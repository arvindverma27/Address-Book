package com.address.book.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.address.book.dao.UserDAO;
import com.address.book.dto.UserDTO;
import com.address.book.service.UserService;
import com.address.book.util.DBUtil;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserServlet() {
		DBUtil dbUtil = new DBUtil();
		UserDAO userDAO = new UserDAO(dbUtil);
		this.userService = new UserService(userDAO);
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("UserServlet : init Method ");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("UserServlet: doGet method..");
		String task = request.getParameter("task");
		System.out.println("Task: " + task);
		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		} else if (task.equalsIgnoreCase("findById")) {
			findById(request, response);
		} else if (task.equalsIgnoreCase("searchContactByKeyword")) {
			searchContactByKeyword(request, response);
		} else if (task.equalsIgnoreCase("exportToPdf")) {
			try {
				exportToPdf(response);
			} catch (Exception e) {
				throw new ServletException("Error while exporting to PDF", e);
			}
		} else if (task.equalsIgnoreCase("exportToExcel")) {
			try {
				exportToExcel(response);
			} catch (Exception e) {
				throw new ServletException("Error while exporting to Excel", e);
			}
		} else if (task.equalsIgnoreCase("deleteById")) {
			deleteById(request, response);
		} else {
			System.out.println("No task matched.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doPost method..");
		String task = request.getParameter("task");
		System.out.println("Task: " + task);
		if (task.equalsIgnoreCase("addcontact")) {
			save(request, response);
		} else if (task.equalsIgnoreCase("update")) {
			update(request, response);
		} else {
			System.out.println("No task matched.");
		}
	}

	public void destroy() {
		System.out.println("destroy : Object Created");
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setEmail(request.getParameter("email"));
			userDTO.setMobileNumber(request.getParameter("mobileNumber"));
			userDTO.setGender(request.getParameter("gender"));
			userDTO.setAddress(request.getParameter("address"));
			userDTO.setCityName(request.getParameter("cityName"));

			int count = userService.save(userDTO);
			if (count > 0) {
				request.setAttribute("status", "success");
				request.setAttribute("message", "Contact Created successfully");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "unable to add contact");
				request.setAttribute("message", "error");
				request.setAttribute("redirectUrl", "add-contact.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "unable to add contact due to :" + e.getMessage());
			request.setAttribute("status", "failed");
			request.setAttribute("redirectUrl", "add-contact.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(Integer.parseInt(request.getParameter("Id")));
			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setEmail(request.getParameter("email"));
			userDTO.setMobileNumber(request.getParameter("mobileNumber"));
			userDTO.setGender(request.getParameter("gender"));
			userDTO.setAddress(request.getParameter("address"));
			userDTO.setCityName(request.getParameter("cityName"));

			int count = userService.update(userDTO);
			if (count > 0) {
				request.setAttribute("message", "Contact update successfully");
				request.setAttribute("status", "success");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "Contact not update. ");
				request.setAttribute("status", "error");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "unable to update user due to :" + e.getMessage());
			request.setAttribute("status", "failed");
			request.setAttribute("redirectUrl", "home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("Id"));
			int count = userService.deleteById(id);
			if (count > 0) {
				request.setAttribute("message", "Contact delete successfully");
				request.setAttribute("status", "success");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "Contact not found. ");
				request.setAttribute("status", "Error");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Unable to delete user due to :" + e.getMessage());
			request.setAttribute("status", "Failed");
			request.setAttribute("redirectUrl", "home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void findById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("Id"));
			UserDTO userDTO = userService.findById(id);
			if (userDTO != null) {
				request.setAttribute("status", "success");
				request.setAttribute("message", "Contact Found successfully");
				request.setAttribute("editUserDTO", userDTO);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "Contact not found. ");
				request.setAttribute("status", "error");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Unable to find Contact due to :" + e.getMessage());
			request.setAttribute("status", "Failed");
			request.setAttribute("redirectUrl", "home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void searchContactByKeyword(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String keyword = request.getParameter("keyword");
			List<UserDTO> userDTOContactList = userService.searchContactByKeyword(keyword);

			// AJAX live search
			String isAjax = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(isAjax)) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				Gson gson = new Gson();
				response.getWriter().write(gson.toJson(userDTOContactList));
				return;
			}

			// Normal search
			if (!userDTOContactList.isEmpty()) {
				request.setAttribute("status", "success");
				request.setAttribute("userDTOContactList", userDTOContactList);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "No Contact found");
				request.setAttribute("status", "Failed");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "unable to found  Contact due to :" + e.getMessage());
			request.setAttribute("status", "error");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			List<UserDTO> userDTOList = userService.findAll();
			if (!userDTOList.isEmpty()) {
				request.setAttribute("userDTOList", userDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "Failed to Find Contact ");
				request.setAttribute("status", "error");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Failed to find Contact due to :" + e.getMessage());
			request.setAttribute("status", "Failed");
			request.setAttribute("redirectUrl", "home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void exportToExcel(HttpServletResponse response) throws Exception {
		List<UserDTO> userDTOList = userService.findAll();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("userDTOList");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("First Name");
		header.createCell(2).setCellValue("Last Name");
		header.createCell(3).setCellValue("Email");
		header.createCell(4).setCellValue("MobileNumber");
		header.createCell(5).setCellValue("Gender");
		header.createCell(6).setCellValue("Address");
		header.createCell(7).setCellValue("City");

		int rowNum = 1;
		for (UserDTO userDTO : userDTOList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(userDTO.getId());
			row.createCell(1).setCellValue(userDTO.getFirstName());
			row.createCell(2).setCellValue(userDTO.getLastName());
			row.createCell(3).setCellValue(userDTO.getEmail());
			row.createCell(4).setCellValue(userDTO.getMobileNumber());
			row.createCell(5).setCellValue(userDTO.getGender());
			row.createCell(6).setCellValue(userDTO.getAddress());
			row.createCell(7).setCellValue(userDTO.getCityName());
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\"user.xlsx\"");
		workbook.write(response.getOutputStream());
		workbook.close();
	}

	private void exportToPdf(HttpServletResponse response) throws Exception {
		List<UserDTO> userDTOList = userService.findAll();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"user.pdf\"");

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			document.add(new Paragraph("Contact List", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(8);
			table.addCell("ID");
			table.addCell("First Name");
			table.addCell("Last Name");
			table.addCell("Email");
			table.addCell("MobileNumber");
			table.addCell("Gender");
			table.addCell("Address");
			table.addCell("City");

			for (UserDTO userDTO : userDTOList) {
				table.addCell(String.valueOf(userDTO.getId()));
				table.addCell(userDTO.getFirstName());
				table.addCell(userDTO.getLastName());
				table.addCell(userDTO.getEmail());
				table.addCell(userDTO.getMobileNumber());
				table.addCell(userDTO.getGender());
				table.addCell(userDTO.getAddress());
				table.addCell(userDTO.getCityName());
			}

			document.add(table);
			document.close();
		} catch (DocumentException e) {
			throw new IOException("Error generating PDF", e);
		}
	}
}
