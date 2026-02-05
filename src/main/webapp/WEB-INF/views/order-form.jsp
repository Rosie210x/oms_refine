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
<div class="container text-center">
    <h1>Product Order Form</h1>
    <h2><c:out value="${message}"/></h2>
    <h3>User list</h3>

    <form action="${pageContext.request.contextPath}/add-order" method="post" class="mt-4">

        Full Name
        <div class="form-group row justify-content-center">
            <div class="col-md-4">
                <label for="txtFirstName"></label>
                <input type="text" class="form-control" name="firstName" id="txtFirstName" placeholder="First">
            </div>
            <div class="col-md-4">
                <label for="txtLastName"></label>
                <input type="text" class="form-control" name="lastName" id="txtLastName" placeholder="Last">
            </div>
        </div>
        Email
        <div class="form-group row justify-content-center">
            <div class="col-md-8">
                <label for="txtEmail"></label>
                <input type="text" class="form-control" id="txtEmail" name="email" placeholder="lulila@company.com">
            </div>
        </div>
        Phone Number
        <div class="form-group row justify-content-center">
            <div class="col-md-8">
                <label for="txtPhone"></label>
                <input type="text" class="form-control" id="txtPhone" name="phoneNumber" placeholder="+84 999 988 886">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <div class="col-md-8">
                <label for="txtAddressLine1"></label>
                <input type="text" class="form-control" id="txtAddressLine1" name="addressLine1" placeholder="Address Line 1">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <div class="col-md-8">
                <label for="txtAddressLine2"></label>
                <input type="text" class="form-control" id="txtAddressLine2" name="addressLine2" placeholder="Address Line 2">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <div class="col-md-4">
                <label for="txtCity"></label>
                <input type="text" class="form-control" id="txtCity" name="city" placeholder="City">
            </div>
            <div class="col-md-4">
                <label for="txtRegion"></label>
                <input type="text" class="form-control" id="txtRegion" name="region" placeholder="Region">
            </div>
        </div>

        <div class="form-group row justify-content-center">
            <div class="col-md-4">
                <label for="txtPostalCode"></label>
                <input type="text" class="form-control" id="txtPostalCode" name="postalCode" placeholder="Postal/Zip Code">
            </div>
            <div class="col-md-4">
                <label for="txtCountry"></label>
                <label for="txtCountry"></label>
                <select class="custom-select" id="txtCountry" name="country">
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.countryId}">
                            <c:out value="${country.countryName}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Buttons -->
        <div class="form-group row justify-content-center">
            <div class="col-md-8 text-center">
                <button type="submit" class="btn btn-primary mr-2" name="add">Add</button>
                <button type="submit" class="btn btn-secondary" name="cancel">Cancel</button>
            </div>
        </div>

    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>