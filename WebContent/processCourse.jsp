
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@page errorPage="error.jsp --%>" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/student/student_style.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<fmt:setBundle basename="messages" />
<title><fmt:message key="course.title" /></title>	
<%@include file="header.jsp"%>
</head>
<body id="body">
<div id="wrapper">
<div class="row">
<div class="col-sm-3">
				<%@include file="menu.jsp"%>
</div>
<div class="col-sm-9">
<%if (request.getAttribute("status")==null) {%>
&nbsp;&nbsp;<span id="title"> <fmt:message key="label.course.title.addCourse" /></span>
<div class="right-content">
<div id="content" class="align">
<br/>
<div id="subtitle">&nbsp;&nbsp;<fmt:message key="label.course.subtitle.addCourse" /></div>
<br/>
<center>
<div class="registration"  style="height:600px" >
<div class="form">

<form id="contactform"action="${pageContext.request.contextPath}/admin_managecourses?action=add" method="post">
<p class="contact">
<label for="CourseName"><fmt:message key="label.course.courseName" /></label>
</p>
<input type="text" name="CourseName" required="required" placeholder="example: JAVA" value="${param['CourseName']}"   size="30" maxlength="20">

<p class="contact">
<label for="Size"><fmt:message key="label.course.size" /></label>
</p>
<input type="number" name="Size" required="required" placeholder="        " value="${param['Size']}" size="30"  maxlength="30"  min=20  max=100><span>${param['sizeError']}</span>

<p class="contact">
<label for="Credits"><fmt:message key="label.course.credit" /></label>
</p>
<input type="number" name="Credits" required="required" placeholder="        " value="${param['Credits']}" size="30"   maxlength="30"  min=1 max=6><span>${param['creditsError']}</span>

<p class="contact">
<label for="LecturerId"><fmt:message key="label.lecturer.lecturerlist.lecturerid" /></label>
</p>
<input type="number" name="LecturerId" required="required" placeholder="example10000" value="${param['LecturerId']}"    size="30" maxlength="20"><span>${param['lecturerError']}</span>

<p class="contact">
<label for="StartDate"><fmt:message key="label.course.startdate" /></label>
</p>
<input type="date" name="StartDate" required="required" placeholder="dd/MM/yyyy" value="${param['StartDate']}"    size="30" maxlength="20"><span>${param['startError']}</span>

<p class="contact">
<label for="EndDate"><fmt:message key="label.course.enddate" /></label>
</p>
<input type="date" name= "EndDate" required="required" placeholder="dd/MM/yyyy" value="${param['EndDate']}" size="30"   maxlength="20"><span>${param['endError']}</span>
<br/><br/><br/>

<div style="float: left; width: 130px">
<input type="submit" class="buttom" id="submit" value="Submit">
</div>

</form>

</div>
</div>
</center>
</div>
</div>
<% } %>
<%if (request.getAttribute("status")=="edit") {%>
&nbsp;&nbsp;<span id="title"> <fmt:message key="label.course.title.editCourse" /></span>
<div class="right-content">
<div id="content" class="align">
<br/>
<div id="subtitle">&nbsp;&nbsp;<fmt:message key="label.course.subtitle.editCourse" /></div>
<br/>
<center>
<div class="registration"  style="height:600px" >
<div class="form">
<form action="${pageContext.request.contextPath}/admin_managecourses?action=modify" method="post">

<p class="contact">
<label for="CourseId"><fmt:message key="label.course.courseId" /></label>
</p>
<input type="text" name="CourseId" value="${CourseId}" readonly="readonly" size=15 maxlength=20>

<p class="contact">
<label for="CourseName"><fmt:message key="label.course.courseName" /></label>
</p>
<input type="text" name="CourseName" required="required" placeholder="example: JAVA" value="${CourseName}" size=30 maxlength=20>
<p class="contact">
<label for="Size"><fmt:message key="label.course.size" /></label>
</p>
<input type="number" name="Size" required="required" placeholder="        " value="${Size}" size=20 maxlength=20  min=20 max=100><span>${param['sizeError']}</span>

<p class="contact">
<label for="Credits"><fmt:message key="label.course.credit" /></label>
</p>
<input type="number" name="Credits" required="required" placeholder="        " value="${Credits}" size=20 maxlength=20  min=1 max=6><span>${param['creditsError']}</span>

<p class="contact">
<label for="LecturerId"><fmt:message key="label.lecturer.lecturerlist.lecturerid" /></label>
</p>
<input type="number" name="LecturerId" required="required" placeholder="example10000"  value="${LecturerId}" size=15 maxlength=20 /><span>${param['lecturerError']}</span>

<p class="contact">
<label for="StartDate"><fmt:message key="label.course.startdate" /></label>
</p>
<input type="date" name="StartDate" required="required" placeholder="dd/MM/yyyy"  value="${StartDate}" size=15 maxlength=20><span>${param['startError']}</span>

<p class="contact">
<label for="EndDate"><fmt:message key="label.course.enddate" /></label>
</p>
<input type="date" name="EndDate" required="required" placeholder="dd/MM/yyyy"  value="${EndDate}" size=15 maxlength=20><span>${param['endError']}</span>
<br/><br/><br/>
<div style="float: left; width: 130px">
<input type="submit" class="buttom" id="submit" value="Modify">
</div>



</form>
</div>
</div>
</center>
</div>
</div>
<% } %>
</div>
</div>
</div>
<div id="below"><jsp:include page="footer.jsp" /></div>
</body>
</html>