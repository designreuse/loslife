<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>


</head>

<body>
	
 <!-- Content Header -->
       <section class="content-header">
          <h1>
            Form Elements
            <small>Preview</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Forms</a></li>
            <li class="active">General Elements</li>
          </ol>
        </section>
        
           <!-- Main content -->
          <section class="content">
         
              <div class="box box-info">
              
                <!-- form start -->
                <form role="form">
                  <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
	                    <div class="form-group">
	                      <label for="Brand">Brand</label>
	                      <input type="text" class="form-control" id="Brand" placeholder="Enter Brand">
	                    </div>
	                    <div class="form-group">
	                      <label for="CustomerType">Customer Type</label>
	                      <select class="form-control" id="CustomerType">
	                        <option>option 1</option>
	                        <option>option 2</option>
	                        <option>option 3</option>
	                        <option>option 4</option>
	                        <option>option 5</option>
	                      </select>
	                    </div>
	                    <div class="form-group">
	                      <label for="Applicant">Applicant</label>
	                      <input type="text" class="form-control" id="Applicant" placeholder="Applicant">
	                    </div>
	                    <div class="form-group">
	                      <label for="ApplicantTeam">Applicant</label>
	                      <input type="text" class="form-control" id="ApplicantTeam" placeholder="Applicant Team">
	                    </div>
	                    <div class="form-group">
	                      <label for="ApplicantBU">Applicant BU</label>
	                      <input type="text" class="form-control" id="ApplicantBU" placeholder="Applicant BU">
	                    </div>
                    </div>
                    
                    <div class="col-md-6">
	                    <div class="form-group">
	                      <label for="CustomerContact">Customer Contact</label>
	                      <input type="text" class="form-control" id="CustomerContact" placeholder="Customer Contact">
	                    </div>
	                    <div class="form-group">
	                      <label for="CustomerTel">Customer Tel</label>
	                      <input type="text" class="form-control" id="CustomerTel" placeholder="Customer Tel:">
	                    </div>
	                   <div class="form-group">
	                      <label for="CustomerEmail">Customer Email</label>
	                      <input type="email" class="form-control" id="CustomerEmail" placeholder="Enter email">
	                    </div>
	                     <div class="form-group">
	                      <label for="ContactTitle">Customer Title</label>
	                      <input type="text" class="form-control" id="ContactTitle" placeholder="Customer Title">
	                    </div>
	                   
                    </div>
                    
                   </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                  </div>
                </form>
              </div>
           
          </section>
</body>
</html>
