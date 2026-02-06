<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Product Order Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/resources/css/main.css"
          rel="stylesheet" type="text/css">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center">Orders</h2>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Order ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Shipping Address</th>
            <th>City</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.orderCode}</td>
                <td>${order.customer.firstname} ${order.customer.lastname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/order-details?orderCode=${order.orderCode}">
                            ${order.customer.email}
                    </a>
                </td>
                <td>${order.customer.addressLine1}</td>
                <td>${order.customer.city.cityName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>