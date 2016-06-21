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
	    <title><spring:message code="setting.personal" /></title>
	</head>
	
	<body>
		
		<section class="content-header">
			<h1><spring:message code="setting.personal" /></h1>
			<ol class="breadcrumb">
				<li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
				<li class="active"><spring:message code="setting.personal" /></li>
			</ol>
			
			<c:if test="${message != null}">
				<div class="alert alert-success alert-dismissable" style="margin: 10px 0 0 0">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
	                    <h4>	<i class="icon fa fa-check"></i> <spring:message code="message.alert.success"/>!</h4>
	                    ${message}
	            </div>
      		</c:if>
		</section>


		<section class="content">
		
			<div class="row">
	            <div class="col-md-6">
	              <div class="box box-solid">
	                <div class="box-header with-border">
	                  <!-- <i class="fa fa-text-width"></i> -->
	                  <h3 class="box-title"><spring:message code="setting.data.sharing.title" /></h3>
	                </div><!-- /.box-header -->
	                <!-- form -->
					<form role="form" action="${ctx}/setting/${action}" method="post" id="primaryForm" class="form-horizontal">
					<input type="hidden" name="id" id="id" value="${dataSharing.id}" />	
	                <div class="box-body">
	                  	<div class="form-group">
							 <label for="parent_id" class="col-md-3 control-label"><spring:message code="setting.data.sharing" /><em> *</em></label>
		                     <div class="col-md-9">
		                    	<select class="form-control select2 parent_id" name="parent_id" id="parent_id">
		                      		<option value></option>
									<c:forEach var="user" items="${users}">
										<option value="${user.id}">${user.name}</option>
									</c:forEach>
		                      	</select>
		                    </div>
						</div>
	                </div><!-- /.box-body -->
	                </form><!-- /.form -->
	                <div class="box-footer">
			          	 <button type="submit" class="btn btn-primary btn-sm" onclick="$('#primaryForm').submit();"><spring:message code="btn.save"/></button>
					</div>
	              </div><!-- /.box -->
	            </div><!-- ./col -->
	            
	            <div class="col-md-6">
	              <div class="box box-solid">
	                <div class="box-header with-border">
	                 <!--  <i class="fa fa-text-width"></i> -->
	                  <h3 class="box-title"><spring:message code="setting.my.view.data.sharing.title" /></h3>
	                </div><!-- /.box-header -->
	                <div class="box-body">
	                  <ul class="list-unstyled">
	                    <c:forEach var="subUser" items="${subUsers}">
	                    	<LI>${subUser.name}</LI>
	                    </c:forEach>
	                  </dl>
	                </div><!-- /.box-body -->
	              </div><!-- /.box -->
	            </div><!-- ./col -->
	          </div><!-- /.row -->
          <!-- END TYPOGRAPHY -->
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
				placeholder: "<spring:message code='setting.data.sharing.remark' />",
				allowClear: true
			});
			
			$("#parent_id").find("option[value='${dataSharing.user_id}']").attr("disabled",true);
			$("#parent_id").val("${dataSharing.parent_id}").trigger("change");
			
		});
		
	</script>
	
	</body>

</html>