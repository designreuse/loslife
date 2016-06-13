<%@page import="com.asgab.entity.ProgressBar"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@page import="com.asgab.entity.BusinessOpportunity"%>
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
        <spring:message code="opportunity.title.view"/>
    </title>


</head>

<body>
 <style>
 	.box{}
 	.box-noborder{border-top: 0}
 	.btn-100{width: 100px;line-height: 1.1}
 	.btn-70{width: 70px;line-height: 1.1}
 	.products{padding: 0 15px 0 15px;}
 	.content{padding-bottom: 0;min-height:0}
 </style>
 <!-- Content Header -->
       <section class="content-header">
          <h1>
       		<spring:message code="opportunity.title.view"/>
          </h1>
          <ol class="breadcrumb">
            <li><a href="${ctx}/businessOpportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active">
            	<spring:message code="opportunity.title.view"/>
            </li>
          </ol>
        </section>
          <!-- form start -->
          <form action="" method="post" id="primaryForm" class="form-horizontal">
          <input type="hidden" name="id" id="id" value="${opportunity.id}" />
        
        	<%
        		BusinessOpportunity businessOpportunity = (BusinessOpportunity)request.getAttribute("businessOpportunity");
        		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver (request);
				String lang =localeResolver.resolveLocale(request).getLanguage();
        	%>
        
           <!-- Main content -->
          <section class="content">
         
              <div class="box">
              
                <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
           				<div class="form-group">
	                      <label for="number" class="col-md-3"><spring:message code="opportunity.id" /></label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.number}</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <label for="name" class="col-md-3"><spring:message code="business.opportunity.name" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.name}</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <label for="advertiser_id" class="col-md-3"><spring:message code="business.opportunity.advertiser" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.advertiser}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="deliver_date" class="col-md-3"><spring:message code="business.opportunity.deliver.date" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.decodeDeliver_date}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="currency_id" class="col-md-3"><spring:message code="business.opportunity.currency" />*</label>
	                      <div class="col-md-9">
	                      		<label>${businessOpportunity.decodeCurrency}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="budget" class="col-md-3"><spring:message code="business.opportunity.budget" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.budget}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="progress" class="col-md-3"><spring:message code="business.opportunity.progress" /></label>
	                      <div class="col-md-9">
				          	<h4 class="control-sidebar-subheading">
                   			<%=businessOpportunity.getDecodeStatus(lang) %>
                    		<span class="label ${businessOpportunity.progressBar.labelClass} pull-right">${businessOpportunity.progress}%</span>
                  			</h4>
                  			<div class="progress progress-xxs">
                    			<div class="progress-bar ${businessOpportunity.progressBar.barClass}" style="width: ${businessOpportunity.progress}%"></div>
                  			</div>
				          	
				          	
				          </div>
	                    </div>
	                    
                    </div>
                  </div>
                </div><!-- /.box-body -->
                
              </div>
           
          </section>
          
          <!-- part2 -->
          <section class="content">
              <div class="box box-noborder">
                <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
	                    <div class="form-group">
	                    	<label for="exist_msa" class="col-md-3"><spring:message code="business.opportunity.msa" />*</label>
	                      	<div class="col-md-9">
	                      		<label><%=businessOpportunity.getDecodeExist_msa(lang) %></label>
	                     	</div>
	                    </div>
	                    
	                    <div class="form-group">
	                    	<label for="exist_service" class="col-md-3"><spring:message code="business.opportunity.service" />*</label>
	                      	<div class="col-md-9">
	                      		<label><%=businessOpportunity.getDecodeExist_service(lang) %></label>
	                     	</div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="owner_sale" class="col-md-3"><spring:message code="business.opportunity.sale" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.owner_sale_name}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="cooperate_sales" class="col-md-3"><spring:message code="business.opportunity.cooperate" /></label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.decodeCooperate_sale }</label>
	                      </div>
	                    </div>
	                    
                    </div>
                  </div>
                </div><!-- /.box-body -->
              </div>
          </section>
          
          <!-- part3 -->
          <section class="content">
              <div class="box box-noborder">
                <div class="box-body">
                	<div class="row">
           			<div class="col-md-12">
	                    <div class="form-group">
	                      <label  class="col-md-3"><spring:message code="business.opportunity.setting" />&nbsp;<i class="fa fa-w fa-caret-down"></i></label>
	                      <div class="divider-horizontal"></div>
	                    </div>
	                </div>
                    </div>
                    
                    
                    <jsp:include page="productView.jsp" flush="true"></jsp:include>
                    
                    
                   	<div class="row">
	           			<div class="col-md-6">
		                    <div class="form-group">
		                      <label for="remark" class="col-md-3"><spring:message code="business.opportunity.remark" /></label>
		                      <div class="col-md-9">
		                      	<label>${businessOpportunity.remark }</label>
		                      </div>
		                    </div>
		                  
	                    </div>
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <button type="button" class="btn btn-primary btn-sm btn-flat btn-70" onclick="edit();"><spring:message code="btn.edit"/></button> 
                    <button type="button" class="btn btn-primary btn-sm disabled btn-sm btn-flat btn-70" onclick="cancel();"><spring:message code="btn.cancel"/></button>
                </div>
              </div>
          </section>
          
          
         </form>
          
		<script>    
			$(document).ready(function() {
				
				$("#menu_business_opportunity").addClass("active");

				$("#progress").ionRangeSlider({
					min:0,
					max:100,
					keyboard:true,
					values:[0,10,30,50,70,90,100],
					disable: true
				});
			});
			
			function cancel(){
				window.location.href='${ctx}/businessOpportunity';
			};
			
			function edit(){
				window.location.href='${ctx}/businessOpportunity/update/${businessOpportunity.id}';
			};
		</script>
</body>
</html>
 



