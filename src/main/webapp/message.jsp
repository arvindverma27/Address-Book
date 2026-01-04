<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, #cfe2f3, #e0f7fa);
	margin: 0;
	padding: 0;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
}

/* Navbar */
.navbar {
	background-color: #1e3a8a;
	color: #fff;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 25px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
	position: sticky;
	top: 0;
	z-index: 100;
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

/* Container */
.container {
	background: #ffffffdd;
	padding: 40px 50px;
	border-radius: 15px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);
	text-align: center;
	max-width: 500px;
	width: 90%;
	margin: auto;
	margin-top: 100px;
	backdrop-filter: blur(10px);
}

h1 {
	color: #1e3a8a;
	margin-bottom: 15px;
	font-size: 28px;
	text-transform: capitalize;
}

h3 {
	color: #333;
	margin-bottom: 25px;
	font-size: 18px;
}

a.redirect-btn {
	text-decoration: none;
	color: #fff;
	background: #1e3a8a;
	padding: 12px 20px;
	border-radius: 6px;
	transition: 0.3s ease;
	font-weight: bold;
	font-size: 16px;
	display: inline-block;
}

a.redirect-btn:hover {
	background: #0f1f6d;
}

p {
	margin-top: 20px;
	font-size: 14px;
	color: #444;
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
	.container {
		padding: 25px;
	}
}
</style>
</head>
<body>

	<!-- ✅ Navbar -->
	<div class="navbar">
		<div class="logo">
			<a href="home.jsp"> <i class="fa-solid fa-address-book"></i>
				Address Book
			</a>
		</div>
		<div class="nav-links">
			<a href="home.jsp" class="nav-link"> <i class="fa-solid fa-house"></i>
				Home
			</a> <a href="add-contact.jsp" class="nav-link"> <i
				class="fa-solid fa-user-plus"></i> Add Contact
			</a> <a href="user-list.jsp" class="nav-link"> <i
				class="fa-solid fa-users"></i> View All Contact
			</a> <a href="about-us.jsp" class="nav-link"> <i
				class="fa-solid fa-circle-info"></i> About Us
			</a>
		</div>
	</div>

	<%
	String status = (String) request.getAttribute("status");
	String message = (String) request.getAttribute("Message");
	String redirectUrl = (String) request.getAttribute("redirectUrl");

	if (status == null || status.trim().isEmpty()) {
		status = "Success";
	}

	if (message == null || message.trim().isEmpty()) {
		message = "Operation completed successfully!";
	}

	if (redirectUrl == null || redirectUrl.trim().isEmpty()) {
		redirectUrl = "home.jsp";
	}
	%>

	<div class="container">
		<h1><%=status%></h1>
		<h3><%=message%></h3>
		<p>
			Click <a href="<%=redirectUrl%>" class="redirect-btn"> Here </a> to
			Redirect
		</p>
	</div>

</body>
</html>
