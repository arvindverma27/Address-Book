<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.address.book.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Contacts</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, #cfe2f3, #e0f7fa);
	margin: 0;
	padding: 0;
	overflow-x: hidden;
}

/* Navbar */
.navbar {
	background-color: #1e3a8a;
	color: #fff;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 25px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	position: sticky;
	top: 0;
	z-index: 100;
	min-height: 50px;
}

.navbar .logo a {
	font-size: 22px;
	font-weight: bold;
	letter-spacing: 1px;
	color: #fff;
	text-decoration: none;
	display: flex;
	align-items: center;
	gap: 8px;
}

.navbar .logo a:hover {
	color: #00bfff;
}

.navbar .nav-links {
	display: flex;
	gap: 0;
	flex-wrap: wrap;
}

/* White line between navbar items */
.navbar a.nav-link {
	color: white;
	text-decoration: none;
	font-size: 18px;
	display: flex;
	align-items: center;
	gap: 12px;
	transition: color 0.3s ease;
	padding-right: 20px;
	margin-right: 20px;
	border-right: 2px solid rgba(255, 255, 255, 0.8);
}

.navbar a.nav-link:last-child {
	border-right: none;
	margin-right: 0;
	padding-right: 0;
}

.navbar a.nav-link:hover {
	color: #00bfff;
}

/* Main Content */
.main-content {
	max-width: 98%;
	margin: 30px auto;
	padding: 0 20px;
	text-align: center;
}

h1, h2 {
	text-align: center;
	color: #1e3a8a;
	margin-bottom: 20px;
}

/* Buttons */
.findall-btn {
	display: inline-block;
	background-color: #4169E1;
	color: #fff;
	font-size: 16px;
	padding: 15px 40px;
	border-radius: 10px;
	font-weight: bold;
	cursor: pointer;
	transition: 0.3s;
	border: none;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.findall-btn:hover {
	background-color: #00008B;
	transform: translateY(-2px);
}

.export-section {
	text-align: center;
	margin-top: 25px;
}

.export-btn {
	background-color: #007bff;
	color: #fff;
	padding: 12px 30px;
	font-size: 17px;
	font-weight: bold;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	margin: 0 10px;
	transition: 0.3s;
}

.export-btn:hover {
	background-color: #0056b3;
}

/* Table */
table {
	width: 100%;
	margin: 30px auto;
	border-collapse: collapse;
	background: #ffffff;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
	font-size: 16px;
	text-align: center;
}

table th, table td {
	padding: 14px 10px;
	border-bottom: 1px solid #e1e1e1;
	text-align: center;
	vertical-align: middle;
}

table th {
	background-color: #e8efff;
	font-weight: bold;
	color: #1e3a8a;
	font-size: 17px;
}

table tr:nth-child(even) {
	background-color: #f9fbff;
}

table tr:hover {
	background-color: #eef5ff;
}

/* Action Buttons */
.action-btn {
	display: flex;
	justify-content: center;
	gap: 8px;
}

.action-btn input[type="submit"] {
	padding: 8px 14px;
	font-size: 14px;
	border-radius: 6px;
	border: none;
	cursor: pointer;
	color: #fff;
	transition: 0.3s;
}

.edit-btn {
	background-color: #28a745;
}

.edit-btn:hover {
	background-color: #218838;
}

.delete-btn {
	background-color: #dc3545;
}

.delete-btn:hover {
	background-color: #a71d2a;
}

/* Edit Form */
.edit-form {
	background: #fff;
	padding: 25px;
	border-radius: 10px;
	max-width: 700px;
	margin: 40px auto;
	box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15);
}

.edit-form table {
	width: 100%;
	border: none;
}

.edit-form table td {
	padding: 10px;
	text-align: left;
}

.edit-form input[type="text"] {
	width: 100%;
	padding: 8px 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 15px;
}

.edit-form input[type="submit"], .edit-form input[type="reset"] {
	width: 48%;
	padding: 10px;
	font-size: 15px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-weight: bold;
}

.edit-form input[type="submit"] {
	background-color: #007bff;
	color: white;
}

.edit-form input[type="reset"] {
	background-color: #6c757d;
	color: white;
}

.edit-form input[type="submit"]:hover {
	background-color: #0056b3;
}

.edit-form input[type="reset"]:hover {
	background-color: #495057;
}

/* Responsive */
@media ( max-width : 768px) {
	.navbar .nav-links {
		gap: 10px;
		justify-content: center;
	}
	.navbar a.nav-link {
		border-right: none;
		margin: 0;
		padding: 0;
	}
}
</style>
</head>

<body>

	<div class="navbar">
		<div class="logo">
			<a href="home.jsp"><i class="fa-solid fa-address-book"></i>
				Address Book</a>
		</div>
		<div class="nav-links">
			<a href="home.jsp" class="nav-link"><i class="fa-solid fa-house"></i>
				Home</a> <a href="add-contact.jsp" class="nav-link"><i
				class="fa-solid fa-user-plus"></i> Add Contact</a> <a
				href="user-list.jsp" class="nav-link"><i
				class="fa-solid fa-users"></i> View All contact</a> <a
				href="about-us.jsp" class="nav-link"><i
				class="fa-solid fa-circle-info"></i> About Us</a>
		</div>
	</div>

	<div class="main-content">
		<%
		List<UserDTO> userDTOList = (List<UserDTO>) request.getAttribute("userDTOList");
		boolean hideFindAll = userDTOList != null && !userDTOList.isEmpty();
		%>

		<!-- Find All Button -->
		<div id="findAllSection"
			style="text-align: center; margin-bottom: 20px; <%=hideFindAll ? "display:none;" : ""%>">
			<form action="UserServlet" method="get">
				<input type="hidden" name="task" value="findAll" /> <input
					type="submit" name="findAll" value="Find All Contacts"
					class="findall-btn" />
			</form>
		</div>

		<%
		if (userDTOList != null && !userDTOList.isEmpty()) {
			int serialNo = 1;
		%>

		<h2>Contact List</h2>
		<table>
			<tr>
				<th>S NO.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile Number</th>
				<th>Gender</th>
				<th>Address</th>
				<th>City</th>
				<th>Action</th>
			</tr>

			<%
			for (UserDTO userDTO : userDTOList) {
			%>
			<tr>
				<td><%=serialNo++%></td>
				<td><%=userDTO.getFirstName()%></td>
				<td><%=userDTO.getLastName()%></td>
				<td><%=userDTO.getEmail()%></td>
				<td><%=userDTO.getMobileNumber()%></td>
				<td><%=userDTO.getGender()%></td>
				<td><%=userDTO.getAddress()%></td>
				<td><%=userDTO.getCityName()%></td>
				<td class="action-btn">
					<form action="UserServlet" method="get">
						<input type="hidden" name="task" value="findById" /> <input
							type="hidden" name="Id" value="<%=userDTO.getId()%>" /> <input
							type="submit" name="Edit" value="Edit" class="edit-btn" />
					</form>
					<form action="UserServlet" method="get"
						onsubmit="return confirm('Are you sure you want to delete this contact?');">
						<input type="hidden" name="task" value="deleteById" /> <input
							type="hidden" name="Id" value="<%=userDTO.getId()%>" /> <input
							type="submit" name="delete" value="Delete" class="delete-btn" />
					</form>
				</td>
			</tr>
			<%
			}
			%>
		</table>

		<!-- Export Buttons -->
		<div class="export-section">
			<form action="UserServlet" method="get" style="display: inline;">
				<input type="hidden" name="task" value="exportToExcel" /> <input
					type="submit" value="Export to Excel" class="export-btn" />
			</form>
			<form action="UserServlet" method="get" style="display: inline;">
				<input type="hidden" name="task" value="exportToPdf" /> <input
					type="submit" value="Export to PDF" class="export-btn" />
			</form>
		</div>
		<%
		}
		%>

		<%
		if (request.getAttribute("editUserDTO") != null) {
			UserDTO editUserDTO = (UserDTO) request.getAttribute("editUserDTO");
		%>
		<h1>Edit Contact</h1>
		<form action="UserServlet" method="post" class="edit-form">
			<input type="hidden" name="task" value="update" /> <input
				type="hidden" name="Id" value="<%=editUserDTO.getId()%>" />
			<table>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstName"
						value="<%=editUserDTO.getFirstName()%>" required></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName"
						value="<%=editUserDTO.getLastName()%>" required></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email"
						value="<%=editUserDTO.getEmail()%>" required></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNumber"
						value="<%=editUserDTO.getMobileNumber()%>" required></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><input type="text" name="gender"
						value="<%=editUserDTO.getGender()%>" required></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address"
						value="<%=editUserDTO.getAddress()%>" required></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="cityName"
						value="<%=editUserDTO.getCityName()%>" required></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="submit" value="Update" /> <input type="reset" value="Reset" />
					</td>
				</tr>
			</table>
		</form>
		<%
		}
		%>
	</div>

</body>
</html>
