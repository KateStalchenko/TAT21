<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="app.Person"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="app.Phone" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Управление данными о человеке</title>
</head>
<body>

<%
    HashMap<String, String> jspParameters = new HashMap<>();
    Person person = new Person();
    String errorMessage;

    if (request.getAttribute("jspParameters") != null) {
        jspParameters = (HashMap<String, String>) request.getAttribute("jspParameters");
    }
    if (request.getAttribute("person") != null) {
        person = (Person) request.getAttribute("person");
    }

    errorMessage = jspParameters.get("errorMessage");
%>
<form action="<%=request.getContextPath()%>/" method="post">
    <input type="hidden" name="id" value="<%=person.getId()%>"/>

    <table align="center" border="1" width="70%">
        <%
            if ((errorMessage != null) && (!errorMessage.equals(""))) {
        %>
        <tr>
            <td colspan="2" align="center"><span style="color:red"><%=errorMessage%></span></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="2" align="center">Информация о человеке</td>
        </tr>
        <tr>
            <td>Фамилия:</td>
            <td><input type="text" name="surname" value="<%=person.getSurname()%>"/></td>
        </tr>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name" value="<%=person.getName()%>"/></td>
        </tr>
        <tr>
            <td>Отчество:</td>
            <td><input type="text" name="middlename" value="<%=person.getMiddlename()%>"/></td>
        </tr>
        <%
            if (jspParameters.get("currentAction") != null &&
                    !jspParameters.get("currentAction").equals("addPerson")) {
        %>
        <tr>
            <td>Телефоны:</td>
            <td>
                <%
                    for (Phone phone : person.getPhones().values()) {
                        if (phone != null) {
                            out.write(phone.getPhoneNumber());
                %>
                <a href="<%=request.getContextPath()%>/phone/?action=<%="editPhone"%>&id=<%=phone.getId()%>&ownerId=<%=person.getId()%>">Редактировать</a>
                <a href="<%=request.getContextPath()%>/phone/?action=<%="deletePhone"%>&id=<%=phone.getId()%>&ownerId=<%=person.getId()%>">Удалить</a>
                <br>
                <%
                        }
                    }
                %>
                <br><a
                    href="<%=request.getContextPath()%>/phone/?action=<%="addPhone"%>&ownerId=<%=person.getId()%>">Добавить</a>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" name="<%=jspParameters.get("nextAction")%>"
                       value="<%=jspParameters.get("nextActionLabel")%>"/>
                <br><a href="<%=request.getContextPath()%>/">Вернуться к списку</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>