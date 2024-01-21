<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.sql.*,java.net.*" %>

<%
	String name = request.getParameter("some_string");
	
	out.println("received word : " + name + "<br>");
%>