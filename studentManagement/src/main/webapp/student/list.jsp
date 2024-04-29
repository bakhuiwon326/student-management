<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: parkheewon
  Date: 4/29/24
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student-list</title>
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
<h1>학생리스트</h1>
<table>
  <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>성별</th>
      <th>나이</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="student" items="${requestScope.studentList}">
      <tr>
        <th>${student.id}</th>
        <th>${student.name}</th>
        <th>${student.gender}</th>
        <th>${student.age}</th>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
