<%@page import="java.util.*" %>
    <%@page import="com.model.CartModel" %>
        <%@page import="com.dao.Dao" %>
            <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

                <% 
                    String specificCartId = request.getParameter("id");
                    String cartIds = (specificCartId != null && !specificCartId.isEmpty()) ? specificCartId : "";
                %>

                    <!DOCTYPE html>
                    <html>

                    <head>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f9fa;
        }
        #paymentForm {
            text-align: center;
            padding: 40px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.1);
        }
        #rzp-button1 {
            background: linear-gradient(135deg, #F37254 0%, #f4511e 100%);
            color: #ffffff;
            font-family: 'Segoe UI', Arial, sans-serif;
            font-size: 18px;
            font-weight: 600;
            padding: 14px 32px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 4px 12px rgba(243, 114, 84, 0.3);
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
            outline: none;
        }
        #rzp-button1:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 16px rgba(243, 114, 84, 0.5);
        }
        #rzp-button1:active {
            transform: translateY(1px);
            box-shadow: 0 2px 8px rgba(243, 114, 84, 0.3);
        }
    </style>
                        <title>Razorpay Payment</title>
                        <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
                    </head>

                    <body>

                        <form id="paymentForm">
                            <input type="hidden" id="amount" value="100">
                            <input type="hidden" id="currency" value="INR">

                            <!-- cartIds hidden -->
                            <input type="hidden" id="cartIds" value="<%=cartIds%>">

                            <button type="button" id="rzp-button1">Pay Now</button>
                        </form>

                        <script>
                            var options = {
                                "key": "rzp_test_SYu5g0dpspSt6h",
                                "amount": document.getElementById("amount").value,
                                "currency": document.getElementById("currency").value,
                                "name": "Test",
                                "description": "Test Transaction",

                                "handler": function (response) {

                                    var form = document.createElement("form");
                                    form.method = "POST";
                                    form.action = "payment";

                                    var paymentId = document.createElement("input");
                                    paymentId.type = "hidden";
                                    paymentId.name = "razorpay_payment_id";
                                    paymentId.value = response.razorpay_payment_id;

                                    var orderId = document.createElement("input");
                                    orderId.type = "hidden";
                                    orderId.name = "razorpay_order_id";
                                    orderId.value = response.razorpay_order_id;

                                    var signature = document.createElement("input");
                                    signature.type = "hidden";
                                    signature.name = "razorpay_signature";
                                    signature.value = response.razorpay_signature;

                                    var cartIds = document.createElement("input");
                                    cartIds.type = "hidden";
                                    cartIds.name = "cartIds";
                                    cartIds.value = document.getElementById("cartIds").value;

                                    form.appendChild(paymentId);
                                    form.appendChild(orderId);
                                    form.appendChild(signature);
                                    form.appendChild(cartIds);

                                    document.body.appendChild(form);
                                    form.submit();
                                },

                                "theme": {
                                    "color": "#F37254"
                                }
                            };

                            document.getElementById('rzp-button1').onclick = function (e) {
                                var rzp1 = new Razorpay(options);
                                rzp1.open();
                                e.preventDefault();
                            }
                        </script>

                    </body>

                    </html>