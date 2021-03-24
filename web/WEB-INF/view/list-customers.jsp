<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Customers List</title>

    <!-- reference our style sheet-->
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">
            <!-- add customer button -->
            <input type="submit" value="Add Customer" onclick="window.location.href='showAddCustomerForm'; return false;" class="add-button">

            <!-- add search form -->
            <form:form action="searchCustomer" method="get">
                <label>Search Customer:</label>
                <input type="text" name="searchName">
                <input type="submit" value="Search" class="add-button">
            </form:form>

            <!-- customers list table -->
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>

                <!-- loop over and print customers -->
                <C:forEach var="customer" items="${customers}">

                    <!-- construct an update link with customer id -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${customer.id}" />
                    </c:url>

                    <!-- construct the delete link with customer id -->
                    <c:url var="deleteLink" value="/customer/deleteCustomer">
                        <c:param name="customerId" value="${customer.id}" />
                    </c:url>
                    <tr>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>

                        <td>
                            <!-- the update link -->
                            <a href="${updateLink}">Update</a>
                            |
                            <!-- the update link -->
                            <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this customer ?'))) return false;">Delete</a>
                        </td>
                    </tr>
                </C:forEach>
            </table>

        </div>
</div>
</body>
</html>
