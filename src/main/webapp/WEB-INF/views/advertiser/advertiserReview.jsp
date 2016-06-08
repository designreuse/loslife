<%@page import="com.asgab.entity.ClientContact"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<style>
		.input-text{width: 100%;text-align: left;background-color: #eee !important;border:1px solid #d2d6de !important;font-weight: bold;font-size: 12px;}
		.input-text-check{width: 100%;text-align: left;background-color: #fff !important;border:1px solid #d2d6de !important;font-weight: bold;font-size: 12px;}
		.input-div{width: 100%;background-color: #eee !important;border:1px solid #d2d6de !important;font-weight: bold;font-size: 12px;padding: 10px;}
		.input-div-check{width: 100%;background-color: #fff !important;border:1px solid #d2d6de !important;font-weight: bold;font-size: 12px;padding: 10px;}
		
	</style>
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
		<div class="box box-default">

			<div class="box-body no-padding">
				<form action="${ctx}/advertiser/merge" method="post" id="primaryForm">
				<input type="hidden" class="input-ignore" name="checkIds" value="${checkIds }">
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
							<c:forEach items="${map['brand']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="brand_radio"></span> 
											<input type="text" name="brand" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.industry" /></th>
							<c:forEach items="${map['industry_id']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="industry_id_radio"></span> 
											<input type="hidden" name="industry_id" class="form-control input-sm ${status.count}" value="${val}" disabled>
											<span class="input-group-addon input-text ${status.count} industry_id_span"><tags:decodeList list="${industryTypes }" value="${val}" ></tags:decodeList></span>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="client.currency" /></th>
							<c:forEach items="${map['currency_id']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="currency_id_radio"></span> 
											<input type="hidden" name="currency_id" class="form-control input-sm ${status.count}" value="${val}" disabled>
											<span class="input-group-addon input-text ${status.count} currency_id_span"><tags:decodeList list="${currencyTypes }" value="${val}" ></tags:decodeList></span>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.company.address" /></th>
							<c:forEach items="${map['address']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="address_radio"></span> 
											<input type="text" name="address" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.qualification.name" /></th>
							<c:forEach items="${map['company_name']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="company_name_radio"></span> 
											<input type="text" name="company_name" class="form-control input-sm ${status.count}" value="${val}" disabled>
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
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.platform" /></th>
							<c:forEach items="${map['platform']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="platform_radio"></span> 
											<input type="text" name="platform" class="form-control input-sm ${status.count}" value="${val}" disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;"><spring:message code="advertiser.clientcontact" /></th>
							<c:forEach items="${map['contacts']}" var="contacts" varStatus="status">
								<th>
									<div class="input-group col-xs-10">
										<span class="input-group-addon"> 
											<input type="radio" class="${status.count}" name="contact_radio"></span>
											<c:if test="${fn:length(contacts) == 0 }">
												<input type="text" value="" name="contact" class="form-control input-sm ${status.count}" disabled>
											</c:if> 
											<c:if test="${fn:length(contacts) > 0 }">
											<div class="input-div ${status.count} contact_div">
											<c:forEach items="${contacts}" var="val">
												<spring:message code="advertiser.client.name" />${status.count}:${val.contact_person }<br/>
												<spring:message code="advertiser.client.phone" />${status.count}:${val.phone }<br/>
												<spring:message code="advertiser.client.position" />${status.count}:${val.position }<br/>
												<spring:message code="advertiser.client.address" />${status.count}:${val.address }<br/>
												<div class="divider-horizontal"></div>
												<input type="hidden" value="${val.id}" name="contact" class="form-control input-sm ${status.count}" disabled>
											</c:forEach>
											</div>
											</c:if>
									</div>
								</th>
							</c:forEach>
						</tr>
						
					</tbody>
				</table>
				
				<!-- -------modal------- -->
				<div class="modal fade" id="submitModal">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title"><spring:message code="message.please.confirm" /></h4>
				      </div>
				      <div class="modal-body">
					      <div class="row">
					      	<div class="col-md-12 form-horizontal">
			                    <div class="form-group">
			                      <label for="remark" class="col-md-3"><spring:message code="advertiser.refresh.data.range" />*</label>
			                      <div class="col-md-9">
			                      	<select id="refreshDataRange" name="refreshDataRange" class="form-control select2 input-ignore" style="width: 100%;"></select>
			                      </div>
			                    </div>
			                    <div class="form-group" id="mergedDataDIV" style="display: none;">
			                      <div class="col-md-9 col-md-offset-3">
			                      	<input type="text" class="form-control input-ignore" id="mergedData" name="mergedData" placeholder="<spring:message code='advertiser.merge.data' />"/>
			                      </div>
			                    </div>
			                </div>
					      </div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-sm btn-primary btn-flat" onclick="submitForm();"><spring:message code="btn.confirm.submit"/></button>
				        <button type="button" class="btn btn-sm btn-primary btn-flat disabled " data-dismiss="modal"><spring:message code="btn.cancel"/></button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
				
				
				</form>
			</div>
			
			<div class="box-footer">
              <button type="button" class="btn btn-sm btn-primary btn-flat" onclick="$('#submitModal').modal('show');"><spring:message code="btn.submit" /></button>
              <button type="button" class="btn btn-sm btn-primary btn-flat disabled" onclick="cancel();"><spring:message code="btn.cancel" /></button>
            </div>
		</div>
		
	</section>
	
	<div id="shield" style="position: fixed; left: 0px; top: 0px; display: none; z-index: 9998; opacity: 0.8; background: #7D7159; width: 100%; height: 100%;">
		<img src="${ctx}/static/images/loading_s.gif" style="position: absolute; top: 300px; left: 48%;" /></div>
	
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
        	 
        	 // hidden的也要
        	 $('input[type="hidden"]').attr("disabled","disabled");
        	 $('input[type="hidden"].'+this.id).removeAttr("disabled");
        	 $("span[class*='_span']").removeClass("input-text-check").addClass("input-text");
        	 $('span.'+this.id).removeClass("input-text").addClass("input-text-check");
        	 
        	 $("div[class*='contact_div']").removeClass("input-div-check").addClass("input-div");
        	 $('div.'+this.id).removeClass("input-div").addClass("input-div-check");
        	 
        	 $("input[class*='input-ignore']").removeAttr("disabled");
        });
        //单选
        $("input[type='radio'][class!='allcheck']").click(function(){
        	var textname = this.name.replace("_radio","");
        	$("input[type='text'][name='"+textname+"']").attr("disabled","disabled");
        	$(this).parent().parent().children("input[type='text']").removeAttr("disabled");

        	
        	$("input[type='hidden'][name='"+textname+"']").attr("disabled","disabled");
        	$(this).parent().parent().children("input[type='hidden']").removeAttr("disabled");
        	// contact单独处理
        	$(this).parent().parent().find("input[type='hidden'][name='contact']").removeAttr("disabled");
        	
        	$("span[class*='"+textname+"_span']").removeClass("input-text-check").addClass("input-text");
        	$(this).parent().parent().children("span[class*='"+textname+"_span']").removeClass("input-text").addClass("input-text-check");
        	$("div[class*='"+textname+"_div']").removeClass("input-div-check").addClass("input-div");
        	$(this).parent().parent().children("div[class*='"+textname+"_div']").removeClass("input-div").addClass("input-div-check");
        	
        	$("input[class*='input-ignore']").removeAttr("disabled");
        });
        
        $("#primaryForm").validate();
        
        // 刷新数据范围
        $("#refreshDataRange").select2({
        	data:[{id:1,text:"全部"},
        	      {id:2,text:"指定日期之后"},
        	      {id:3,text:"新建单"}
        	     ]
        }).on('change', function (evt) {
        	  if($(this).val()==2){
        		  $("#mergedDataDIV").show();
        		  // 添加验证规则
        		  $("#mergedData").rules('add', {required:true});
        	  }else{
        		  $("#mergedDataDIV").hide();
        		  $("#mergedData").rules('remove', {required:true});
        	  }
        });

        
        $("#mergedData").daterangepicker({singleDatePicker:true,opens:"right",cancelClass:"btn-info",format:'YYYY-MM-DD'});
	 });
	 
	 function cancel(){
		 window.location.href="${ctx}/advertiser";
	 };
	 
	 function submitForm(){
		 if($("#primaryForm").valid()){
			 $('#submitModal').modal('hide');
			 $('#shield').show();
			 $('#primaryForm').submit();
		 }
	 }
	</script>
</body>
</html>
