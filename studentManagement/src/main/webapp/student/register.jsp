<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.heewon.studentmanagement.Gender" %>
<%@ page import="com.heewon.studentmanagement.bean.Student" %><%--
  Created by IntelliJ IDEA.
  User: parkheewon
  Date: 4/29/24
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student-register</title>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border:1px #ccc solid;
        }
        table tr>td,th{
            padding:5px;
            border:1px #ccc solid;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${empty student}">
        <c:set var="action" value="/student/register.do" />
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/student/update.do" />
    </c:otherwise>
</c:choose>
<form method="post" action="${action}">
<table>
    <tr>
        <th>ID</th>
        <td><input name="id" type="text" value="${student.id}" required/></td>
    </tr>
    <tr>
        <th>이름</th>
        <td><input name="name" type="text" value="${student.name}" required/></td>
    </tr>
    <tr>
        <th>성별</th>
        <td>
            <label><input type="radio" name="gender" value="M" required ${Objects.isNull(student) || !student.gender.equals(Gender.M) ? " " : "checked"}> 남 </label>
            <label><input type="radio" name="gender" value="F" ${Objects.isNull(student) || !student.gender.equals(Gender.F) ? " " : "checked"}> 여 </label>

        </td>
    </tr>
    <tr>
        <th>나이</th>
        <td><input name="age" type="text" value="${student.age}" required/></td>
    </tr>
</table>
    <p><button type="submit">
        <c:choose>
            <c:when test="${empty student}">등록</c:when>
            <c:otherwise>수정</c:otherwise>
        </c:choose>
        </button>
    </p>
</form>
</body>
</html>
