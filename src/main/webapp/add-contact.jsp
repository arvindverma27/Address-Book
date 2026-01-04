<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Contact</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
	background: linear-gradient(135deg, #cfe2f3, #e0f7fa);
	min-height: 100vh;
}

/* === NAVBAR === */
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
	gap: 10px;
}

.navbar .logo a:hover {
	color: #00bfff;
}

.navbar .nav-links {
	display: flex;
	gap: 0;
	flex-wrap: wrap;
}

/* White divider between items */
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

/* === FORM === */
.content {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	padding: 40px 20px;
	width: 100%;
}

.container {
	background: #fff;
	width: 100%;
	max-width: 400px;
	padding: 25px 20px;
	border-radius: 14px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.container h1 {
	text-align: center;
	font-size: 26px;
	font-weight: 600;
	margin-bottom: 25px;
	color: #333;
}

.input-group {
	display: flex;
	align-items: center;
	background: #f9f9f9;
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 10px;
	margin-bottom: 15px;
	gap: 10px;
}

.input-group i {
	color: #777;
	font-size: 18px;
}

.input-group input, .input-group select {
	border: none;
	outline: none;
	flex: 1;
	font-size: 16px;
	background: transparent;
}

input[type=submit] {
	width: 100%;
	background: #1e88e5;
	color: white;
	padding: 12px;
	border: none;
	border-radius: 8px;
	font-size: 18px;
	font-weight: 600;
	cursor: pointer;
	transition: background 0.3s ease;
}

input[type=submit]:hover {
	background: #1565c0;
}

/* === RESPONSIVE === */
@media ( max-width : 800px) {
	.navbar {
		flex-direction: column;
		gap: 10px;
		text-align: center;
	}
	.navbar .nav-links {
		flex-wrap: wrap;
		justify-content: center;
		gap: 15px;
	}
	.navbar a.nav-link {
		border-right: none;
		margin: 0;
		padding: 0;
	}
}

@media ( max-width : 450px) {
	.container {
		padding: 20px 15px;
	}
	.input-group input {
		font-size: 15px;
	}
}
</style>
</head>
<body>

	<!-- NAVBAR -->
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

	<!-- === FORM === -->
	<div class="content">
		<div class="container">
			<h1>Add Contact</h1>
			<form action="UserServlet" method="post"
				onsubmit="return validateForm()">
				<input type="hidden" name="task" value="addcontact">

				<div class="input-group">
					<i class="fa-solid fa-user"></i> <input type="text"
						name="firstName" placeholder="First Name" required>
				</div>

				<div class="input-group">
					<i class="fa-regular fa-user"></i> <input type="text"
						name="lastName" placeholder="Last Name" required>
				</div>

				<div class="input-group">
					<i class="fa-solid fa-envelope"></i> <input type="email"
						name="email" placeholder="Email Address" required>
				</div>

				<div class="input-group">
					<i class="fa-solid fa-phone"></i> <input type="text"
						name="mobileNumber" placeholder="Mobile Number" required
						pattern="[0-9]{10}">
				</div>

				<div class="input-group">
					<i class="fa-solid fa-venus-mars"></i> <select name="gender"
						required>
						<option value="">Select Gender</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Other">Other</option>
					</select>
				</div>

				<div class="input-group">
					<i class="fa-solid fa-location-dot"></i> <input type="text"
						name="address" placeholder="Address" required>
				</div>

				<div class="input-group">
					<i class="fa-solid fa-city"></i> <input type="text" name="cityName"
						placeholder="City" required>
				</div>

				<input type="submit" value="Save Contact">
			</form>
		</div>
	</div>

	<script>
		function validateForm() {
			const mobile = document.querySelector("input[name='mobileNumber']").value;
			const email = document.querySelector("input[name='email']").value;

			if (!/^\d{10}$/.test(mobile)) {
				alert("Mobile number must be 10 digits.");
				return false;
			}

			const emailRegex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/i;
			if (!emailRegex.test(email)) {
				alert("Enter a valid email address.");
				return false;
			}

			return true;
		}
	</script>
</body>
</html>
