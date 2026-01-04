<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>About</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, #cfe2f3, #e0f7fa);
	margin: 0;
	padding: 0;
	overflow-x: hidden;
	font-size: 16px;
}

/* === NAVBAR === */
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

/* White divider between nav links */
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

/* === CONTAINER === */
.container {
	width: 90%;
	max-width: 1000px;
	margin: 60px auto;
	background: #ffffffdd;
	border-radius: 20px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
	padding: 40px;
	backdrop-filter: blur(10px);
}

.container h1 {
	text-align: center;
	color: #1e3a8a;
	margin-bottom: 30px;
	font-size: 36px;
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 10px;
}

/* === SECTION BOXES === */
.section-box {
	background: #f8fafc;
	border-radius: 15px;
	padding: 25px 30px;
	margin-bottom: 25px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s ease;
}

.section-box:hover {
	transform: translateY(-5px);
}

.section-box h2 {
	color: #0072ff;
	margin-top: 0;
	margin-bottom: 15px;
	display: flex;
	align-items: center;
	gap: 8px;
	font-size: 22px;
}

.section-box p, .section-box ul {
	font-size: 16px;
	line-height: 1.7;
	margin-bottom: 10px;
}

/* Lists inside boxes */
.section-box ul {
	list-style: none;
	padding-left: 0;
	display: flex;
	flex-wrap: wrap;
	gap: 12px;
}

.section-box ul li {
	background: #1e3a8a;
	color: white;
	padding: 6px 10px;
	border-radius: 8px;
	font-weight: 600;
	font-size: 14px;
	display: flex;
	align-items: center;
	gap: 6px;
	transition: transform 0.3s ease, background 0.3s ease;
}

.section-box ul li:hover {
	transform: translateY(-3px);
	background: #0072ff;
}

/* === CONTACT INFO === */
.contact-info p {
	margin: 5px 0;
}

.contact-info a {
	color: #1e3a8a;
	text-decoration: none;
	font-weight: bold;
}

.contact-info a:hover {
	text-decoration: underline;
}

/* === FOOTER === */
footer {
	text-align: center;
	padding: 30px;
	margin-top: 100px;
	background: #1e3a8a;
	color: white;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
}

footer a {
	color: #ffd700;
	text-decoration: none;
	margin: 0 8px;
}

footer a:hover {
	text-decoration: underline;
}

/* === RESPONSIVE === */
@media ( max-width : 768px) {
	.container {
		padding: 25px;
	}
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
	.container h1 {
		font-size: 28px;
	}
	.section-box ul {
		flex-direction: column;
	}
}
</style>
</head>

<body>

	<!-- === NAVBAR === -->
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

	<!-- === MAIN CONTAINER === -->
	<div class="container">
		<h1>
			<i class="fa-solid fa-circle-info"></i> About Address Book
		</h1>

		<p style="font-weight: bold;">Welcome to the Address Book web
			application. Manage your contacts efficiently and effortlessly. Add,
			view, search, edit, and delete contacts with ease. Built with JSP,
			Servlets, and modern CSS for a clean interface.</p>

		<!-- Features Box -->
		<div class="section-box">
			<h2>
				<i class="fa-solid fa-star"></i> Features
			</h2>
			<ul>
				<li><i class="fa-solid fa-user-plus"></i> Add contacts</li>
				<li><i class="fa-solid fa-table-list"></i> View contacts in
					table</li>
				<li><i class="fa-solid fa-pen-to-square"></i> Edit/Delete
					contacts</li>
				<li><i class="fa-solid fa-magnifying-glass"></i> Search
					contacts</li>
				<li><i class="fa-solid fa-file-export"></i> Export to PDF/Excel</li>
			</ul>
		</div>

		<!-- Technologies Used Box -->
		<div class="section-box">
			<h2>
				<i class="fa-solid fa-gears"></i> Technologies Used
			</h2>
			<ul>
				<li><i class="fa-brands fa-java"></i> JSP & Servlets</li>
				<li><i class="fa-brands fa-html5"></i> HTML5</li>
				<li><i class="fa-brands fa-css3-alt"></i> CSS3</li>
				<li><i class="fa-brands fa-js"></i> JavaScript</li>
				<li><i class="fa-brands fa-font-awesome"></i> FontAwesome</li>
			</ul>
		</div>

		<!-- Developer Box -->
		<div class="section-box">
			<h2>
				<i class="fa-solid fa-user"></i> Developer
			</h2>
			<p>
				<strong>Arvind Ahirwar</strong> – Full Stack Developer
			</p>
			<div class="contact-info">
				<p>
					<i class="fa-solid fa-envelope"></i> Email: <a
						href="mailto:arvindahirwar9285@gmail.com">arvindahirwar9285@gmail.com</a>
				</p>
				<p>
					<i class="fa-solid fa-phone"></i> Phone: <a
						href="tel:+918461890212">+91 84618 90212</a>
				</p>
				<p>
					<i class="fa-brands fa-whatsapp"></i> WhatsApp: <a
						href="https://wa.me/918461890212" target="_blank">+91
						8461890212</a>
				</p>
				<p>
					<i class="fa-brands fa-linkedin"></i> LinkedIn: <a
						href="https://www.linkedin.com/in/arvind-ahirwar-80325b233/"
						target="_blank"> linkedin.com/in/arvind-ahirwar-80325b233/</a>
				</p>
				<p>
					<i class="fa-solid fa-location-dot"></i> Address: Manorama House,
					Bholaram Ustad Marg, Indore 452001, Madhya Pradesh, India
				</p>
			</div>
		</div>

		<!-- FAQ Box -->
		<div class="section-box">
			<h2>
				<i class="fa-solid fa-question-circle"></i> FAQ
			</h2>
			<p>
				<strong>Q:</strong> How do I add a new contact?<br> <strong>A:</strong>
				Click on "Add Contact" in the navbar and fill out the form.
			</p>

			<p>
				<strong>Q:</strong> Can I export my contacts?<br> <strong>A:</strong>
				Yes, export to PDF or Excel using the buttons on the contact list
				page.
			</p>

			<p>
				<strong>Q:</strong> Is this app mobile-friendly?<br> <strong>A:</strong>
				Yes, it is fully responsive for phones and tablets.
			</p>
		</div>
	</div>

	<!-- === FOOTER === -->
	<footer>
		&copy; 2025 Address Book. All rights reserved. | <a href="home.jsp">Home</a>
		| <a href="add-contact.jsp">Add Contact</a> | <a href="user-list.jsp">View
			All Contact</a> | <a href="about-us.jsp">About Us</a>
	</footer>

</body>
</html>
