<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver (request);
	String lang =localeResolver.resolveLocale(request).getLanguage();
	request.setAttribute("lang", lang);
%>

<html>
<head>
<title><spring:message code="menu.advertiser.review" /></title>
</head>
<body>
	<!-- Content Header -->
	<section class="content-header">
		<h1>
			<spring:message code="menu.advertiser.review" />
		</h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i>
					<spring:message code="opportunity.home" /></a></li>
			<li class="active"><spring:message code="menu.advertiser.review" /></li>
		</ol>
	</section>

	<!-- Content -->
	<section class="content">
		<form action="${ctx}/advertiser/merge" method="post" id="primaryForm">
		<input type="hidden" name="checkIds" value="${checkIds }">
		<div class="box box-default">

			<div class="box-body no-padding">
				<table class="table table-condensed table-striped ">
					<thead>
						<tr>
							<th style="width: 80px;"></th>
							<c:forEach items="${map['id']}" var="val" varStatus="status">
								<th style="vertical-align: bottom;">
									<input id="${status.count}" type="radio" name="id" class="flat-red allcheck" value="${val}"> 
									<label for="${status.count}" style="vertical-align: bottom; margin-bottom: 1px;"><spring:message code="btn.selectall" /></label>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.clientname" /></th>
							<c:forEach items="${map['clientname']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="clientname_radio"></span> 
											<input type="text" name="clientname" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.brand" /></th>
							<c:forEach items="${map['client_brand']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="client_brand_radio"></span> 
											<input type="text" name="client_brand" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.industry" /></th>
							<c:forEach items="${map['industry']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="industry_radio"></span> 
											<input type="text" name="industry" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.company.address" /></th>
							<c:forEach items="${map['company_address']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="company_address_radio"></span> 
											<input type="text" name="company_address" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.qualification.name" /></th>
							<c:forEach items="${map['qualification_name']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="qualification_name_radio"></span> 
											<input type="text" name="qualification_name" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.website.name"/></th>
							<c:forEach items="${map['website_name']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="website_name_radio"></span> 
											<input type="text" name="website_name" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.website.address"/></th>
							<c:forEach items="${map['website_address']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="website_address_radio"></span> 
											<input type="text" name="website_address" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.organization.code"/></th>
							<c:forEach items="${map['organization_code']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="organization_code_radio"></span> 
											<input type="text" name="organization_code" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.icp" /></th>
							<c:forEach items="${map['icp']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="icp_radio"></span> 
											<input type="text" name="icp" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.business.licence" /></th>
							<c:forEach items="${map['business_licence']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="business_licence_radio"></span> 
											<input type="text" name="business_licence" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						
					</tbody>
				</table>

			</div>
			
			<div class="box-footer">
              <button class=" btn btn-sm  btn-primary  btn-flat" onclick="$('#primaryForm').submit();"><spring:message code="btn.submit" /></button>
              <button class=" btn btn-sm btn-warning  btn-flat"><spring:message code="btn.cancel" /></button>
            </div>
		</div>
		</form>
	</section>
	<script type="text/javascript">
	
	 $(function () {
        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
          checkboxClass: 'icheckbox_flat-green',
          radioClass: 'iradio_flat-green'
        });
        //全选
        $("input[type='radio'].allcheck").on('ifChecked', function(event){
        	 $('input[type="radio"].'+this.id).iCheck('check');
        	 $('input[type="text"]').attr("disabled","disabled");
        	 $('input[type="text"].'+this.id).removeAttr("disabled");
        });
        //单选
        $("input[type='radio'][class!='allcheck']").click(function(){
        	var tetxname = this.name.replace("_radio","");
        	$("input[type='text'][name='"+tetxname+"']").attr("disabled","disabled");
        	$(this).parent().parent().children("input[type='text']").removeAttr("disabled");
        });
        
        
	 });
	</script>
</body>
</html>
