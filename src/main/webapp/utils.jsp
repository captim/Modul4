<%@ page import="com.dumanskiy.dao.DAO" %>
<%@ page import="com.dumanskiy.dao.OracleDAO" %>
<%@ page import="com.dumanskiy.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 21.04.2020
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String redirect = request.getParameter("redirect");
        response.sendRedirect(redirect + ".jsp");
        String name = request.getParameter("name");
        DAO dao = OracleDAO.getInstance();
        User user = new User();
    %>
</body>
</html>
