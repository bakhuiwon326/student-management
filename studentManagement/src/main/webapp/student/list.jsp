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
<p>
  <a href="/student/register.do">등록하러가기</a>
</p>
<table>
  <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>성별</th>
      <th>나이</th>
      <th>cmd</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="student" items="${requestScope.studentList}">
      <tr>
        <th>${student.id}</th>
        <th>${student.name}</th>
        <th>${student.gender}</th>
        <th>${student.age}</th>
        <td>
          <!-- todo view.do 변경 -->
          <c:url var="view_link" value="/student/view.do" scope="request">
            <c:param name="id" value="${student.id}" />
          </c:url>
          <a href="${view_link}">조회</a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
