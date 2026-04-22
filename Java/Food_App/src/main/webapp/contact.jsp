<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.Dao" %>
<%
    String message = "";
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String msg = request.getParameter("message");

        int status = Dao.addContactMessage(name, email, subject, msg);
        if (status > 0) {
            message = "success";
        } else {
            message = "error";
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .contact-hero {
            padding: 80px 0;
            background: linear-gradient(135deg, rgba(255,107,53,0.9), rgba(46,196,182,0.9)), url('https://images.unsplash.com/photo-1555939594-58d7cb561ad1?q=80&w=2070&auto=format&fit=crop') center/cover;
            text-align: center;
            color: white;
        }

        .contact-hero h1 {
            font-size: 3rem;
            margin-bottom: 15px;
        }

        .contact-container {
            display: grid;
            grid-template-columns: 1fr 1.5fr;
            gap: 50px;
            padding: 80px 0;
            align-items: flex-start;
        }

        .contact-info {
            background: var(--surface-color);
            padding: 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
        }

        .contact-info h3 {
            font-size: 1.5rem;
            color: var(--text-dark);
            margin-bottom: 25px;
        }

        .info-item {
            display: flex;
            align-items: flex-start;
            gap: 15px;
            margin-bottom: 25px;
        }

        .info-item i {
            font-size: 1.5rem;
            color: var(--primary);
            margin-top: 3px;
            width: 30px;
            text-align: center;
        }

        .info-item h4 {
            font-size: 1.1rem;
            color: var(--text-dark);
            margin-bottom: 5px;
        }

        .info-item p {
            color: var(--text-muted);
            font-size: 0.95rem;
            line-height: 1.5;
        }

        .contact-form {
            background: #ffffff;
            padding: 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
        }

        .contact-form h2 {
            font-size: 2rem;
            margin-bottom: 10px;
            color: var(--text-dark);
        }

        .contact-form p {
            color: var(--text-muted);
            margin-bottom: 30px;
        }

        .form-row {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }

        .form-row .form-group {
            flex: 1;
            margin-bottom: 0;
        }

        textarea.form-control {
            resize: vertical;
            min-height: 150px;
        }

        @media(max-width: 900px) {
            .contact-container {
                grid-template-columns: 1fr;
            }
            .form-row {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <!-- Hero Section -->
    <section class="contact-hero">
        <div class="container">
            <h1>Get in Touch</h1>
            <p style="font-size: 1.1rem; opacity: 0.9;">We'd love to hear from you. Drop us a message!</p>
        </div>
    </section>

    <!-- Contact Content -->
    <section class="container contact-container">
        
        <!-- Info Cards -->
        <div class="contact-info">
            <h3>Contact Information</h3>
            
            <div class="info-item">
                <i class="fa-solid fa-location-dot"></i>
                <div>
                    <h4>Our Location</h4>
                    <p>Foodie Headquarters<br>SG Highway, Navrangpura<br>Ahmedabad, Gujarat - 380015</p>
                </div>
            </div>

            <div class="info-item">
                <i class="fa-solid fa-phone"></i>
                <div>
                    <h4>Call Us</h4>
                    <p>Customer Support: +91 98765 43210<br>Sales: +91 98765 43211</p>
                </div>
            </div>

            <div class="info-item">
                <i class="fa-solid fa-envelope"></i>
                <div>
                    <h4>Email Us</h4>
                    <p>support@foodieapp.com<br>info@foodieapp.com</p>
                </div>
            </div>

            <div style="margin-top: 30px; display: flex; gap: 15px;">
                <a href="#" style="width: 40px; height: 40px; border-radius: 50%; background: rgba(255,107,53,0.1); color: var(--primary); display: flex; align-items: center; justify-content: center; font-size: 1.2rem; transition: var(--transition);"><i class="fa-brands fa-facebook-f"></i></a>
                <a href="#" style="width: 40px; height: 40px; border-radius: 50%; background: rgba(255,107,53,0.1); color: var(--primary); display: flex; align-items: center; justify-content: center; font-size: 1.2rem; transition: var(--transition);"><i class="fa-brands fa-twitter"></i></a>
                <a href="#" style="width: 40px; height: 40px; border-radius: 50%; background: rgba(255,107,53,0.1); color: var(--primary); display: flex; align-items: center; justify-content: center; font-size: 1.2rem; transition: var(--transition);"><i class="fa-brands fa-instagram"></i></a>
            </div>
        </div>

        <!-- Contact Form -->
        <div class="contact-form">
            <h2>Send us a Message</h2>
            <p>Have a question or feedback? Fill out the form below and our team will get back to you within 24 hours.</p>
            
            <% if ("success".equals(message)) { %>
                <div style="background: rgba(46, 196, 182, 0.1); color: #2ec4b6; padding: 15px; border-radius: 8px; margin-bottom: 20px; font-weight: 500; border: 1px solid #2ec4b6;">
                    <i class="fa-solid fa-circle-check" style="margin-right: 8px;"></i> Message sent successfully! We'll get back to you soon.
                </div>
            <% } else if ("error".equals(message)) { %>
                <div style="background: rgba(220, 53, 69, 0.1); color: #dc3545; padding: 15px; border-radius: 8px; margin-bottom: 20px; font-weight: 500; border: 1px solid #dc3545;">
                    <i class="fa-solid fa-circle-exclamation" style="margin-right: 8px;"></i> Something went wrong. Please try again later.
                </div>
            <% } %>
            
            <form action="contact.jsp" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder=" " required>
                        <label for="name" class="form-label">Full Name</label>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder=" " required>
                        <label for="email" class="form-label">Email Address</label>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" id="subject" name="subject" placeholder=" " required>
                    <label for="subject" class="form-label">Subject</label>
                </div>

                <div class="form-group">
                    <textarea class="form-control" id="message" name="message" placeholder=" " required></textarea>
                    <label for="message" class="form-label">Your Message</label>
                </div>

                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 14px; font-size: 1.1rem; margin-top: 10px;">Send Message</button>
            </form>
        </div>

    </section>

    <%@ include file="footer.jsp" %>
</body>
</html>
