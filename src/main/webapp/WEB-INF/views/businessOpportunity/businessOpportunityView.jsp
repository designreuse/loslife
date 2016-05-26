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
       		<c:if test="${action eq 'update' }"><spring:message code="opportunity.title.view"/></c:if>
          </h1>
          <ol class="breadcrumb">
            <li><a href="${ctx}/businessOpportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active">
            	<c:if test="${action eq 'update' }"><spring:message code="opportunity.title.view"/></c:if>
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
         
              <div class="box box-info">
              
                <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
	                    <div class="form-group">
	                      <label for="advertiser_id" class="col-md-3"><spring:message code="business.opportunity.advertiser" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.advertiser}</label>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="deliver_date" class="col-md-3"><spring:message code="business.opportunity.deliver.date" />*</label>
	                      <div class="col-md-9">
	                      	<label>${businessOpportunity.deliver_start_date} - ${businessOpportunity.deliver_start_date}</label>
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
				          	<input id="progress" class="form-control" data-slider-id="blue" type="text" name="progress" value="${businessOpportunity.progress}">
				          	<label><%=businessOpportunity.getDecodeStatus(lang) %></label>
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
	                     	<input type="hidden" name="exist_msa" id="exist_msa" value="${businessOpportunity.exist_service}">
	                      	<div class="col-md-9">
	                      		<input type="button" class="btn btn-primary btn-flat pull-left btn-sm btn-100" onclick="changeRadio(1,'exist_msa',this);" value="<spring:message code="business.opportunity.yes" />">
	                      		<input type="button" class="btn btn-default btn-flat pull-left btn-sm btn-100" onclick="changeRadio(0,'exist_msa',this);" value="<spring:message code="business.opportunity.no" />">
	                     	</div>
	                    </div>
	                    
	                    <div class="form-group">
	                    	<label for="exist_service" class="col-md-3"><spring:message code="business.opportunity.service" />*</label>
	                     	<input type="hidden" name="exist_service" id="exist_service" value="${businessOpportunity.exist_service}">
	                      	<div class="col-md-9">
	                      		<input type="button" class="btn btn-primary btn-flat pull-left btn-sm btn-100" onclick="changeRadio(1,'exist_service',this);" value="<spring:message code="business.opportunity.service" />">
	                      		<input type="button" class="btn btn-default btn-flat pull-left btn-sm btn-100" onclick="changeRadio(0,'exist_service',this);" value="<spring:message code="business.opportunity.exec" />">
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
                    
                    <!--  -->
                    <div class="products" id="products">
                    
                    </div>
                    
                   	<div class="row">
           			<div class="col-md-6">
           				
	                    <div class="form-group">
	                      <label for="remark" class="col-md-3"><spring:message code="business.opportunity.remark" /></label>
	                      <div class="col-md-9">
	                      	${businessOpportunity.remark }
	                      </div>
	                    </div>
	                  
	                    <input type="hidden" id="hiddenProductArray" name="hiddenProductArray">
                    </div>
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm btn-flat btn-70" onclick="edit();"><spring:message code="btn.edit"/></button> 
                    <button type="button" class="btn btn-primary btn-sm disabled btn-sm btn-flat btn-70" onclick="cancel();"><spring:message code="btn.cancel"/></button>
                </div>
              </div>
          </section>
          
          
         </form>
          
		<script>    
			$(document).ready(function() {
				
				$("#menu_business_opportunity").addClass("active");
				
				$("#deliver_date").daterangepicker({opens:"right",cancelClass:"btn-info",format:'YYYY-MM-DD'});
				
				// 广告主
				$("#advertiser_id").select2({
					ajax: {
				        url: "${ctx}/ajax/getAdvertisers",
				        dataType: 'json',
				        delay: 250,
				        data: function (params) {
				            return {q: params.term};
				        },
				        processResults: function (data) {
				            return {results: data};
				        },
				        cache: true
				    },
				    minimumInputLength: 1,
				    placeholder: "<spring:message code='business.opportunity.input.advertiser' />",
				    allowClear: true
				});
				
				// 销售
				$("#owner_sale").select2({
					ajax: {
				        url: "${ctx}/ajax/getSales",
				        dataType: 'json',
				        delay: 250,
				        data: function (params) {
				            return {q: params.term};
				        },
				        processResults: function (data) {
				            return {results: data};
				        },
				        cache: true
				    },
				    minimumInputLength: 1,
				    placeholder: "<spring:message code='business.opportunity.input.sale' />",
				    allowClear: true
				});
				
				// 合作销售
				$("#cooperate_sales").select2({
					ajax: {
				        url: "${ctx}/ajax/getSales",
				        dataType: 'json',
				        delay: 250,
				        data: function (params) {
				            return {q: params.term};
				        },
				        processResults: function (data) {
				            return {results: data};
				        },
				        cache: true
				    },
				    minimumInputLength: 1,
				    placeholder: "<spring:message code='business.opportunity.input.coopsale' />"
				});
				
				$("#primaryForm").validate({
					rules:{
						advertiser_id:"required",
						deliver_date:"required",
						budget:"required",
						currency_id:"required",
						owner_sale:"required"
					}
				});

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
			
			function changeRadio(val,id,name){
				$('#'+id).val(val);
				$(name).parent().children().each(function(){
					$(this).removeClass("btn-primary").addClass("btn-default");
				});
				$(name).removeClass("btn-default").addClass("btn-primary");
			};
			
			function addProduct(){
				var productIndex = $(".products").children(".row").length+1
				$.post("${ctx}/ajax/addProduct",{index:productIndex},function(html){
					$(".products").append(html);
				},"html");
			};
			
			function submitForm(){
				var array = new Array();
				$("#products").children(".row").each(function(){
					var tmpArray = new Array();
					tmpArray.push($(this).find("select[name^='product_id']").val());
					tmpArray.push($(this).find("select[name^='sale_mode']").val());
					tmpArray.push($(this).find("input[name^='product_budget']").val());
					array.push(tmpArray);
				});
				$("#hiddenProductArray").val(JSON.stringify(array));
				if($("#primaryForm").validate()){
					$("#primaryForm").submit();
				}
			};
		</script>
</body>
</html>
 



