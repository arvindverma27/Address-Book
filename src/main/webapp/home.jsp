<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.address.book.dto.UserDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Home</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
* {
	box-sizing: border-box;
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

/* White vertical line between navbar items */
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

/* Search Bar */
.search-bar {
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	width: 90%;
	border-radius: 10px;
	display: flex;
	justify-content: center;
	box-shadow: 0 2px 6px rgba(0, 12, 0, 0.1);
}

.search-bar input[type="text"] {
	padding: 10px;
	width: 200px;
	max-width: 70%;
	border-radius: 8px;
	border: 3px solid #ccc;
}

.search-bar input[type="submit"] {
	padding: 8px 15px;
	margin-left: 5px;
	border-radius: 4px;
	border: none;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	font-weight: bold;
}

.search-bar input[type="submit"]:hover {
	background-color: #0056b3;
}

/* Table */
.table-container {
	width: 90%;
	margin: 20px auto;
	overflow-x: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: #fff;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	min-width: 600px;
}

th {
	background: #007bff;
	color: white;
	padding: 12px;
	text-align: left;
	white-space: nowrap;
}

td {
	padding: 10px;
	border-bottom: 1px solid #eee;
	white-space: nowrap;
}

tr:nth-child(even) {
	background: #f9f9f9;
}

tr:hover {
	background: #f1f1f1;
}

.action-btn {
	display: flex;
	justify-content: center;
	gap: 10px;
	flex-wrap: wrap;
}

.action-btn input[type="submit"] {
	padding: 6px 14px;
	font-size: 13px;
	font-weight: bold;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: all 0.3s ease;
	color: #fff;
}

.edit-btn {
	background: #28a745;
}

.edit-btn:hover {
	background: #218838;
}

.delete-btn {
	background: #dc3545;
}

.delete-btn:hover {
	background: #c82333;
}

.no-result {
	text-align: center;
	color: red;
	margin-top: 20px;
	font-weight: bold;
}

.welcome {
	text-align: center;
	margin-top: 50px;
	
}

.welcome h1 {
	color: #007bff;
}

/* Edit Form */
.edit-form {
	width: 60%;
	max-width: 600px;
	margin: 40px auto;
	background: #fff;
	padding: 25px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.edit-form table {
	width: 100%;
	border-collapse: collapse;
}

.edit-form td {
	padding: 10px;
	font-weight: bold;
}

.edit-form input[type="text"] {
	width: 100%;
	padding: 8px;
	border-radius: 6px;
	border: 1px solid #ccc;
}

.edit-form input[type="submit"], .edit-form input[type="reset"] {
	padding: 10px 20px;
	border: none;
	background: #007bff;
	color: white;
	font-weight: bold;
	border-radius: 6px;
	cursor: pointer;
	margin-right: 10px;
}

.edit-form input[type="submit"]:hover {
	background: #0056b3;
}

.edit-form input[type="reset"] {
	background: #6c757d;
}

.edit-form input[type="reset"]:hover {
	background: #5a6268;
}

h1.edit-heading {
	text-align: center;
	color: #007bff;
	margin-top: 30px;
	margin-bottom: 10px;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.navbar {
		flex-direction: column;
		align-items: flex-start;
		gap: 10px;
	}
	.navbar .nav-links {
		width: 100%;
		justify-content: flex-start;
		flex-wrap: wrap;
	}
	.navbar a.nav-link {
		border-right: none;
		margin: 0;
		padding: 0;
	}
	.search-bar {
		flex-direction: column;
		align-items: stretch;
	}
	.search-bar input[type="text"] {
		width: 100%;
		margin-bottom: 10px;
	}
	.search-bar input[type="submit"] {
		width: 100%;
	}
}

@media ( max-width : 480px) {
	.navbar .logo a {
		font-size: 18px;
	}
	.navbar a.nav-link {
		font-size: 16px;
	}
	.edit-form {
		width: 90%;
	}
	.edit-form input[type="submit"], .edit-form input[type="reset"] {
		width: 100%;
		margin-bottom: 10px;
	}
	.action-btn {
		flex-direction: column;
	}
}
</style>
</head>
<body>

	<div class="navbar">
		<div class="logo">
			<a href="home.jsp"> <i class="fa-solid fa-address-book"></i>
				Address Book
			</a>
		</div>
		<div class="nav-links">
			<a href="home.jsp" class="nav-link"><i class="fa-solid fa-house"></i>
				Home</a> <a href="add-contact.jsp" class="nav-link"><i
				class="fa-solid fa-user-plus"></i> Add Contact</a> <a
				href="user-list.jsp" class="nav-link"><i
				class="fa-solid fa-users"></i> View All Contact</a> <a
				href="about-us.jsp" class="nav-link"><i
				class="fa-solid fa-circle-info"></i> About Us</a>
		</div>
	</div>

	<%
	String task = request.getParameter("task");
	List<UserDTO> userDTOContactList = (List<UserDTO>) request.getAttribute("userDTOContactList");
	String searchMessage = (String) request.getAttribute("message");
	%>

	<!-- Search bar hide when editing -->
	<%
	if (!"searchContactByKeyword".equals(task) && request.getAttribute("editUserDTO") == null) {
	%>
	<div class="search-bar">
		<form action="UserServlet" method="get">
			<input type="hidden" name="task" value="searchContactByKeyword">
			<input type="text" name="keyword" placeholder="Search Contact...."
				required> <input type="submit" value="Search">
		</form>
	</div>
	<%
	}
	%>

	<!-- Search Results -->
	<%
	if ("searchContactByKeyword".equals(task)) {
		if (userDTOContactList != null && !userDTOContactList.isEmpty()) {
	%>
	<div class="table-container">
		<table>
			<tr>
				<th>S.NO.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile Number</th>
				<th>Gender</th>
				<th>Address</th>
				<th>City</th>
				<th>Actions</th>
			</tr>
			<%
			for (UserDTO userDTO : userDTOContactList) {
			%>
			<tr>
				<td><%=userDTO.getId()%></td>
				<td><%=userDTO.getFirstName()%></td>
				<td><%=userDTO.getLastName()%></td>
				<td><%=userDTO.getEmail()%></td>
				<td><%=userDTO.getMobileNumber()%></td>
				<td><%=userDTO.getGender()%></td>
				<td><%=userDTO.getAddress()%></td>
				<td><%=userDTO.getCityName()%></td>
				<td class="action-btn">
					<form action="UserServlet" method="get">
						<input type="hidden" name="task" value="findById"> <input
							type="hidden" name="Id" value="<%=userDTO.getId()%>"> <input
							type="submit" value="Edit" class="edit-btn">
					</form>
					<form action="UserServlet" method="get"
						onsubmit="return confirm('Are You Sure?');">
						<input type="hidden" name="task" value="deleteById"> <input
							type="hidden" name="Id" value="<%=userDTO.getId()%>"> <input
							type="submit" value="Delete" class="delete-btn">
					</form>
				</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<%
	} else {
	%>
	<p class="no-result"><%=searchMessage != null ? searchMessage : "No contact found!"%></p>
	<%
	}
	} else if (request.getAttribute("editUserDTO") == null) {
	%>

	<div class="welcome">
		<h1>Welcome To Address Book</h1>
		<p>Use the navigation bar above to manage your contacts easily.</p>
	</div>

	<%
	}
	%>

	<!-- Edit Form -->
	<%
	if (request.getAttribute("editUserDTO") != null) {
		UserDTO editUserDTO = (UserDTO) request.getAttribute("editUserDTO");
	%>
	<h1 class="edit-heading">Edit Contact</h1>
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
					type="submit" value="Update"> <input type="reset"
					value="Reset"></td>
			</tr>
		</table>
	</form>
	<%
	}
	%>

</body>
</html>
