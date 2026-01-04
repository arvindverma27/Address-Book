package com.address.book.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


@WebServlet("/UserAPIservlet")
public class UserAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	private Gson gson;

	public UserAPIServlet() {
		DBUtil dbUtil = new DBUtil();

		UserDAO userDAO = new UserDAO(dbUtil);

		this.userService = new UserService(userDAO);

	}


	
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("UserServlet : init Method ");
		
	}

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("UserServlet: doGet method..");
		String task = request.getParameter("task");
		System.out.println("Task: " + task);
		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		} else if (task.equalsIgnoreCase("findById")) {
			findById(request, response);
//		} else if (task.equalsIgnoreCase("findByMobileNumber")) {
//			findByMobileNumber(request, response);
		}  else if(task.equalsIgnoreCase("findByFirstAndLastName")){
			searchContactByKeyword(request, response);
		
		}else if(task.equalsIgnoreCase("exportToPdf")) {
			try {
				exportToPdf(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else if(task.equalsIgnoreCase("exportToExcel")) {
				try {
					exportToExcel(response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(task.equalsIgnoreCase("deleteById")){
			deleteById(request, response);
			 
		}else {
			System.out.println("No task matched.");
		}

	}

		
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet: doPost method..");
		String task = request.getParameter("task");
		System.out.println("Task: " + task);
		if(task.equalsIgnoreCase("addcontact")) {
			save(request, response);
		}else if(task.equalsIgnoreCase("update")) {
			update(request, response);
		}else {
			System.out.println("No task matched.");
		}
		
	}

	


    public void destroy() {
    	System.out.println("destroy : Object Created");
    	
    	
		
	}
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	
		Map<String, Object> resp = new HashMap<String, Object>();
		int statuscode =0;
		
		
		try {
			
			String body = request.getReader().lines().collect(Collectors.joining());
			UserDTO userDTO = gson.fromJson(body, UserDTO.class);
		
			int count = userService.save(userDTO);
			if(count > 0) {
				System.out.println("User created successfully");
				resp.put("status", "success");
				resp.put("message", "User Created successfully ");
				resp.put("data", null);
				statuscode = 200;
				
				
			}else {
				System.out.println("unable to create user.");
				resp.put("status", "error");
				resp.put("message", "unable to create user ");
				resp.put("data", null);
				statuscode = 500;
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		
			resp.put("status", "error");
			resp.put("message", "failed to create User Due to  : " + e.getMessage());
			
			resp.put("data", null);
			statuscode = 500;
			
			
			
		}
		response.setStatus(statuscode);
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(resp));
		writer.close();
		
	}

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	Map<String, Object> resp = new HashMap<String, Object>();
		int statuscode =0;
		
		
		try {
			String body = request.getReader().lines().collect(Collectors.joining());
			UserDTO userDTO = gson.fromJson(body, UserDTO.class);
		
			
			int count = userService.update(userDTO);
			
			if(count > 0) {
				System.out.println("User update successfully");
				resp.put("status", "success");
				resp.put("message", "User update successfully ");
				resp.put("data", null);
				statuscode = 200;
				
				
			}else {
				System.out.println("No User found.");
				resp.put("status", "error");
				resp.put("message", "No User found.");
				resp.put("data", null);
				statuscode = 500;
				
				
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			resp.put("status", "error");
			resp.put("message", "failed to update User : " + e.getMessage());
			
			resp.put("data", null);
			statuscode = 500;
			
			
			
			
		}
		response.setStatus(statuscode);
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(resp));
		writer.close();
		
	}
    private void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	Map<String, Object> resp = new HashMap<String, Object>();
		int statuscode =0;
		
		
		try {
			
			
			int id =Integer.parseInt(request.getParameter("UserId"));
			
			int count = userService.deleteById(id);
			if(count > 0) {
				System.out.println("User delete successfully");
				resp.put("status", "success");
				resp.put("message", "User delete successfully ");
				resp.put("data", null);
				statuscode = 200;
				
				
			}else {
				System.out.println("No User found.");
				resp.put("status", "error");
				resp.put("message", "No User found.");
				resp.put("data", null);
				statuscode = 500;
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();

			resp.put("status", "error");
			resp.put("message", "failed to deleted User : " + e.getMessage());
			
			resp.put("data", null);
			statuscode = 500;
			
		}
		response.setStatus(statuscode);
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(resp));
		writer.close();
		
	}
    private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	Map<String, Object> resp = new HashMap<String, Object>();
		int statuscode =0;
		
		
		try {
		
			
             int id =Integer.parseInt(request.getParameter("UserId"));
			
			
			UserDTO userDTO = userService.findById(id);
			if(userDTO != null) {
				System.out.println("User Found successfully");
				resp.put("status", "success");
				resp.put("message", "User Found successfully ");
				resp.put("data", userDTO);
				statuscode = 200;
				
				
			}else {
				System.out.println("No User found.");
				resp.put("status", "error");
				resp.put("message", "No User found.");
				resp.put("data", null);
				statuscode = 500;
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			resp.put("status", "error");
			resp.put("message", "failed to find by id Due User : " + e.getMessage());
			
			resp.put("data", null);
			statuscode = 500;

		}
		response.setStatus(statuscode);
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(resp));
		writer.close();
		
	}
//    private void findByMobileNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    	Map<String, Object> resp = new HashMap<String, Object>();
//		int statuscode =0;
//		
//		
//		try {
//		
//			
//			
//		String MobileNumber = request.getParameter("MobileNumber");
//		
//			UserDTO userDTO = userService.findByMobileNumber(MobileNumber);
//			if(userDTO != null) {
//				System.out.println("User Found successfully");
//				resp.put("status", "success");
//				resp.put("message", "User Found successfully ");
//				resp.put("data", userDTO);
//				statuscode = 200;
//				
//				
//			}else {
//				System.out.println("No User found.");
//				resp.put("status", "error");
//				resp.put("message", "No User found.");
//				resp.put("data", null);
//				statuscode = 500;
//				
//				
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//	
//			resp.put("status", "error");
//			resp.put("message", "failed to Find by Mobile User : " + e.getMessage());
//			
//			resp.put("data", null);
//			statuscode = 500;
//			
//		}
//		response.setStatus(statuscode);
//		PrintWriter writer = response.getWriter();
//		writer.append(gson.toJson(resp));
//		writer.close();
//	}
	
    private void searchContactByKeyword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resp = new HashMap<>();
        int statusCode = 0;

        String keyword = request.getParameter("name");
        

        try {
            List<UserDTO> userDTOList = userService.searchContactByKeyword(keyword);

            if (!userDTOList.isEmpty()) {
                resp.put("status", "success");
                resp.put("message", "User found successfully");
                resp.put("data", userDTOList);
                statusCode = 200;
            } else {
                resp.put("status", "error");
                resp.put("message", "No user found.");
                resp.put("data", null);
                statusCode = 404;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.put("status", "error");
            resp.put("message", "Failed to find user: " + e.getMessage());
            resp.put("data", null);
            statusCode = 500;
        }

        response.setStatus(statusCode);
        PrintWriter writer = response.getWriter();
        writer.append(gson.toJson(resp));
        writer.close();
    }

   
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String, Object> resp = new HashMap<String, Object>();
		int statuscode =0;
		
		
		try {
		
			
			List<UserDTO> userDTOList = userService.findAll();
			if(!userDTOList.isEmpty()) {
				System.out.println("User Found successfully");
				resp.put("status", "success");
				resp.put("message", "User Found successfully ");
				resp.put("data", userDTOList);
				statuscode = 200;
				
				
			}else {
				System.out.println("No User found.");
				resp.put("status", "error");
				resp.put("message", "No User found.");
				resp.put("data", null);
				statuscode = 500;
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			resp.put("status", "error");
			resp.put("message", "failed to found All  User due to  : " + e.getMessage());
			
			resp.put("data", null);
			statuscode = 500;
			
		}
		response.setStatus(statuscode);
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(resp));
		writer.close();
		
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
			row.createCell(6).setCellValue(userDTO.getCityName());

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

			document.add(new Paragraph("User List", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
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
