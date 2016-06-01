<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row contacts" id="client_contact_${index}">
    <div class="col-md-6">
   			<div class="form-group">
       			<label for="" class="col-md-3">广告主联系人${index+1}</label>
       			<div class="col-md-9">
       				<input id="contact_person" class="form-control" type="text" name="contacts[${index}].contact_person" value="" />
       			</div>
       		</div>
         		
       		<div class="form-group">
       			<label for="" class="col-md-3">联络人职位${index+1}</label>
       			<div class="col-md-9">
       				<input id="position" class="form-control" type="text" name="contacts[${index}].position" value="" />
       			</div>
       		</div>
         		
       		<div class="form-group">
       			<label for="" class="col-md-3">联络人电话${index+1}</label>
       			<div class="col-md-9">
       				<input id="phone" class="form-control" type="text" name="contacts[${index}].phone" value="" />
       			</div>
       		</div>
         		
       		<div class="form-group">
       			<label for="" class="col-md-3">联络人邮箱${index+1}</label>
       			<div class="col-md-9">
       				<input id="email" class="form-control" type="text" name="contacts[${index}].email" value="" />
       			</div>
       		</div>
         		
      		<div class="form-group">
      			<label for="" class="col-md-3">公司地址${index+1}</label>
      			<div class="col-md-9">
      				<input id="address" class="form-control" type="text" name="contacts[${index}].address" value="" />
      			</div>
      		</div>
	</div>
	
	<div class="col-md-6">
		<div class="form-group">
			<label class="col-md-3" id="rm-contact-${index}"><a href="javascript:void(0);" style="color: red;">
				<i class="fa fa-w fa-minus-square"></i>&nbsp;删除联系人${index+1}</a>
			</label>
		</div>
	</div>
	
</div><!-- /.row -->



