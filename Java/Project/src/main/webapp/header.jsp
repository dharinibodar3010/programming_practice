
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
   <!-- basic -->
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!-- mobile metas -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="viewport" content="initial-scale=1, maximum-scale=1">
   <!-- site metas -->
   <title>webwing</title>
   <!-- bootstrap css -->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <!-- style css -->
   <link rel="stylesheet" href="css/style.css">
   <!-- Responsive-->
   <link rel="stylesheet" href="css/responsive.css">
   <link rel="stylesheet" href="css/owl.carousel.min.css">
   <!-- fevicon -->
   <link rel="icon" href="images/fevicon.png" type="image/gif" />
   <!-- Scrollbar Custom CSS -->
   <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
   <!-- Tweaks for older IEs-->
   <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
	
	<style type="text/css">
        /* Side Drawer Styling */
        .side-drawer {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 2000;
            top: 0;
            left: 0;
            background-color: #032154;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
            box-shadow: 2px 0 10px rgba(0,0,0,0.5);
        }

        .side-drawer a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 25px;
            color: #ffffff;
            display: block;
            transition: 0.3s;
            text-transform: uppercase;
            font-weight: 600;
        }

        .side-drawer a:hover {
            color: #f2db18;
            padding-left: 45px;
        }

        .side-drawer .closebtn {
            position: absolute;
            top: 0;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        .hamburger-btn {
            font-size: 30px;
            cursor: pointer;
            color: white;
            padding: 10px 15px;
            transition: 0.3s;
            display: flex;
            align-items: center;
        }

        .hamburger-btn:hover {
            color: #f2db18;
            transform: scale(1.1);
        }

        .swd-button {
            background: #f2db18;
            border: 1px solid white;
            border-radius: 5px;
            color: black !important;
            display: inline-block;
            font: bold 12px Arial, Helvetica, sans-serif;
            padding: 10px 15px;
            text-decoration: none;
            text-transform: uppercase;
            margin-top: 15px;
            font-weight: 700;
        }
	</style>
</head>

<body class="main-layout">
   <!-- Side Drawer -->
   <div id="mySidenav" class="side-drawer">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a href="index.jsp">Home</a>
      <a href="product.jsp?category=men">Men's Fashion</a>
      <a href="product.jsp?category=women">Women's Fashion</a>
      <a href="product.jsp?category=kids">Kids Collection</a>
      <hr style="border-color: rgba(255,255,255,0.1)">
      <a href="about.jsp">About Us</a>
      <a href="product.jsp">Shop Now</a>
      <a href="contact.jsp">Contact</a>
   </div>

   <!-- header -->
   <header>
      <div class="header">
         <div class="header_to d_none">
            <div class="container">
               <div class="row align-items-center">
                  <ul class="lan">
                     <li>Follow Us</li>
                     <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                     <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                     <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                     <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                  </ul>
               </div>
               <% if(session.getAttribute("project")!=null) { %>
               <div class="col-md-12 text-white text-right" style="font-size:12px;">
                  Welcome: <b><%=session.getAttribute("name") %></b>
               </div>
               <% } %>
            </div>
         </div>
         <div class="header_midil">
            <div class="container">
               <div class="row d_flex">
                  <div class="col-md-4 col-sm-4 d_none">
                     <ul class="conta_icon">
                        <li><a href="#"><i class="fa fa-phone"></i> +01 1234567890</a></li>
                     </ul>
                  </div>
                  <div class="col-md-4 col-sm-4 text-center">
                     <a class="logo" href="index.jsp"><img src="images/logo.png" alt="Logo" /></a>
                  </div>
                  <div class="col-md-4 col-sm-4 d_none">
                     <ul class="conta_icon ">
                        <li><a href="#"><i class="fa fa-envelope"></i> demo@gmail.com</a></li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
         <div class="header_bo" style="background: #032154;">
            <div class="container">
               <div class="row align-items-center">
                  <div class="col-md-9 col-sm-7 flex-row d-flex align-items-center">
                     <!-- Hamburger 3-Line Menu -->
                     <span class="hamburger-btn" onclick="openNav()">
                        <i class="fa fa-bars"></i> <span style="font-size:16px; margin-left:10px; font-weight:600;">MENU</span>
                     </span>

                     <nav class="navigation navbar navbar-expand-md navbar-dark ml-4 d-none d-md-block">
                        <div class="collapse navbar-collapse">
                           <ul class="navbar-nav mr-auto">
                              <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                              <li class="nav-item"><a class="nav-link" href="about.jsp">About</a></li>
                              <li class="nav-item"><a class="nav-link" href="product.jsp">Products</a></li>
                              <% if(session.getAttribute("project")!=null) { %> 
                              <li class="nav-item"><a class="nav-link" href="wishlist.jsp">Wishlist</a></li>
                              <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
                              <% } %>
                              <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
                           </ul>
                        </div>
                     </nav>
                  </div>
                  <div class="col-md-3 col-sm-5 text-right">
                     <% if(session.getAttribute("project")!=null) { %>	 
                        <a class="swd-button" href="logout.jsp">Logout</a>
                     <% } else { %>
                        <a class="swd-button" href="signup.jsp">Sign Up</a>
                        <a class="swd-button" href="signin.jsp">Sign In</a>
                     <% } %>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </header>

   <script>
      function openNav() {
         document.getElementById("mySidenav").style.width = "300px";
      }

      function closeNav() {
         document.getElementById("mySidenav").style.width = "0";
      }
   </script>