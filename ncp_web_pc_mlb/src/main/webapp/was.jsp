<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%=System.getProperty("weblogic.Name")%> (<%=System.getProperty("spring.profiles.active")%>)
<br>
HTTPS: <%= request.isSecure()%>
<br>
IP: <%= InetAddress.getLocalHost().getHostAddress() %>