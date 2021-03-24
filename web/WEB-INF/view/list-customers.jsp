<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="io.kagboton.springdemo.util.SortUtils" %>
<%@ page import="javax.persistence.Id" %>
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

                <!-- construct the first name sort link -->
                <c:url var="firstNameSortLink" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
                </c:url>

                <!-- construct the last name sort link -->
                <c:url var="lastNameSortLink" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
                </c:url>

                <!-- construct the email sort link -->
                <c:url var="emailSortLink" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
                </c:url>

                <tr>
                    <!-- the first name sort link -->
                    <th>
                        <a href="${firstNameSortLink}">First Name</a>
                    </th>

                    <!-- the last name sort link -->
                    <th>
                        <a href="${lastNameSortLink}">Last Name</a>
                    </th>

                    <!-- the email sort link -->
                    <th>
                        <a href="${emailSortLink}">Email</a>
                    </th>

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
