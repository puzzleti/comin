<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Web Application - Welcome</title>
<s:head />
</head>
<body>
	<h1>welcome To the init-project</h1>
	
	<hr>
	<p>
	this project is a init-project with embedded jetty server, h2 database<br>
	and a person-dao example.<br>
	frontend is made of struts2 for simplicity.<br> 
	feel free and encouraged to develop further...
	</p>
	<hr>
	
	<ul>
	<li>under <em>src/main/resources</em> there are the initial sql scripts</li>
	<li>in the <em>listener</em> an example db-item is created</li>
	<li><a href="http://localhost:8082/login.jsp">H2 console</a></li>
	<li>on every (re)start of the jetty server creates a new initial h2 database</li>
	</ul>
	
	<br>
	<hr>
	<br>
	Login:<br>
	<s:form action="login">
		<s:textfield name="email" label="Email" />
		<s:password name="password" label="Password" />
		<s:submit />
	</s:form>
	
	<br>
	<hr>
	<br>
	<p><a href="register.jsp">Please register</a> for a happy life</p>
	

</body>
</html>