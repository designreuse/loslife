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
	    
	    <title>
	    	设置
	    </title>
	</head>
	
	<body>
		
		<section class="content-header">
			<h1>个人设置</h1>
			<ol class="breadcrumb">
				<li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
				<li class="active">个人设置</li>
			</ol>
			
			<c:if test="${message != null}">
				<div class="alert alert-success alert-dismissable" style="margin: 10px 0 0 0">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
	                    <h4>	<i class="icon fa fa-check"></i> <spring:message code="message.alert.success"/>!</h4>
	                    ${message}
	            </div>
      		</c:if>
		</section>

<!-- form -->
<form role="form" action="${ctx}/setting/${action}" method="post" id="primaryForm" class="form-horizontal">
<input type="hidden" name="id" id="id" value="${dataSharing.id}" />	

	
		<section class="content">
			<div class="box box-info">
				<div class="box-body" style="min-height: 300px;">
					<div class="row">
					
						<div class="col-md-6">
						
							<div class="form-group">
								<label for="parent_id" class="col-md-3">数据共享<em> *</em></label>
			                    <div class="col-md-9">
			                    	<select class="form-control select2 parent_id" name="parent_id" id="parent_id">
			                      		<option value></option>
										<c:forEach var="user" items="${users}">
											<option value="${user.id}">${user.name}</option>
										</c:forEach>
			                      	</select>
			                    </div>
							</div>
							
						</div>
					
					</div><!-- /row --> 	
				</div><!-- /box-body -->
				
				<div class="box-footer">
		          	 <button type="submit" class="btn btn-primary btn-sm" onclick="$('#primaryForm').submit();"><spring:message code="btn.save"/></button>
				</div>
				    	
			</div><!-- /box -->
		</section>
		
</form><!-- /form -->	

	<script type="text/javascript"> 
	
		$(document).ready(function() {
			
			$("#primaryForm").validate({
				ignore: "",
				rules:{
					parent_id: "required"
				}
			});
	
			$("#parent_id").select2({
				placeholder: "请输入领导名称",
				allowClear: true
			});
			
			$("#parent_id").find("option[value='${dataSharing.user_id}']").attr("disabled",true);
			$("#parent_id").val("${dataSharing.parent_id}").trigger("change");
			
		});
		
	</script>
	
	</body>

</html>