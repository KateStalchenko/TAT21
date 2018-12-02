<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="app.Phonebook"%>
<%@ page import="app.Person"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="app.Phone" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Список людей</title>
</head>
<body>

<%
    String userMessage;
    HashMap<String, String> jspParameters = new HashMap<>();
    Phonebook phonebook = (Phonebook) request.getAttribute("phonebook");

    if (request.getAttribute("jspParameters") != null) {
        jspParameters = (HashMap<String, String>) request.getAttribute("jspParameters");
    }
    userMessage = jspParameters.get("currentActionResultLabel");
%>
<table align="center" border="1" width="90%">
    <%
        if ((userMessage != null) && (!userMessage.equals(""))) {
    %>
    <tr>
        <td colspan="6" align="center"><%=userMessage%>
        </td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="6" align="center">
            <a href="<%=request.getContextPath()%>/?action=<%="addPerson"%>">Добавить запись</a>
        </td>
    </tr>
    <tr>
        <td align="center"><b>Фамилия</b></td>
        <td align="center"><b>Имя</b></td>
        <td align="center"><b>Отчество</b></td>
        <td align="center"><b>Телефон(ы)</b></td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
    </tr>
    <%
        for (Person person : phonebook.getContents().values()) {
    %>
    <tr>
        <td><%=person.getSurname()%>
        </td>
        <td><%=person.getName()%>
        </td>
        <td><%=person.getMiddlename()%>
        </td>
        <td>
            <%
                for (Phone phone : person.getPhones().values()) {
            %>
            <%=phone.getPhoneNumber()%><br/>
            <%
                }
            %>
        </td>
        <td>
            <a href="<%=request.getContextPath()%>/?action=<%="editPerson"%>&id=<%=person.getId()%>">Редактировать</a>
        </td>
        <td>
            <a href="<%=request.getContextPath()%>/?action=<%="deletePerson"%>&id=<%=person.getId()%>">Удалить</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>