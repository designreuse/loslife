<%@page import="com.asgab.entity.ClientContact"%>
<%@page import="java.util.List"%>
<%@page import="com.asgab.entity.Client"%>
<%@page import="com.asgab.core.pagination.Page"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver (request);
	request.setAttribute("lang",localeResolver.resolveLocale(request).getLanguage());
%>

<html>
<head>
<title><spring:message code="advertiser.title"/></title>
</head>
<body>
	<style type="text/css">
		.btn-sm{line-height: 1.1;}
		.btn-50{width: 50px;}
	</style>
	 <!-- Content Header -->
       <section class="content-header">
          <h1>
            <spring:message code="advertiser.title"/>
          </h1>
          
          <ol class="breadcrumb">
            <li><a href="${ctx}/advertiser/list"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active"><spring:message code="advertiser.title"/></li>
          </ol>
		
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissable" style="margin: 10px 0 0 0">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>	<i class="icon fa fa-check"></i> <spring:message code="message.alert.success"/>!</h4>
                    ${message}
                    <c:if test="${orderMessage != null}"><br/>${orderMessage}</c:if>
            </div>
        </c:if>
		          
        </section>

          <!-- Main content -->
          <section class="content">
          	<div class="nav-tabs-custom">
                <div class="tab-content">
                  <div class="tab-pane active" id="activity">
                  <div class="box">
		            <div class="box-header with-border">
		              <h3 class="box-title">
		              <!-- 没有title -->
                		<button class="btn btn-primary btn-sm btn-flat" onclick="compare();"><i class="fa fa-w fa-columns"></i>&nbsp;<spring:message code="btn.merge"/></button>
                		<button class="btn btn-primary btn-sm btn-flat" onclick="$('#searchForm').submit();"><i class="fa fa-w fa-search"></i>&nbsp;<spring:message code="btn.search"/></button>
		              	<button class="btn btn-warning btn-sm btn-flat" onclick="resetForm();"><i class="fa fa-w fa-undo"></i>&nbsp;<spring:message code="btn.reset"/></button>
		              </h3>
		              <div class="box-tools pull-right">
		                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
		              </div>
		            </div><!-- /.box-header -->
		            <div class="box-body" style="display: block;">
		            	<form action="${ctx}/advertiser" method="get" id="searchForm">
						<div class="row">
							<div class="form-group col-md-4">
								<label><spring:message code="advertiser.clientname" /></label>
								<input type="text" class="form-control" name="or_clientname" id="or_clientname" value="<c:out value="${pages.searchMap['or_clientname']}"/>" placeholder="<spring:message code='advertiser.input.clientname'/>">
							</div>
							
							<div class="form-group col-md-4">
								<label><spring:message code="advertiser.brand" /></label>
								<input type="text" class="form-control" name="or_brand" id="or_brand" value="<c:out value="${pages.searchMap['or_brand']}"/>" placeholder="<spring:message code='advertiser.input.brand'/>">
							</div>
							
							<div class="form-group col-md-4">
								<label><spring:message code="advertiser.companyname" /></label>
								<input type="text" class="form-control" name="or_channel_name" id="or_channel_name" value="<c:out value="${pages.searchMap['or_channel_name']}"/>" placeholder="<spring:message code="client.channel.remark" />">
							</div>
							
							<div class="form-group col-md-4">
								<label><spring:message code="advertiser.sales"/></label>
								<select class="form-control select2 saleIds" name="saleIds" id="saleIds" style="width: 100%;">
									<c:forEach var="user" items="${users}">
	                      				<option value="${user.id}">${user.name}</option>
	                      			</c:forEach>
		                      	</select>
							</div>
							
							<div class="form-group col-md-4">
								<label><spring:message code="advertiser.dateDuring"/></label>
								<div class="input-group">
	                      			<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
	                      			<input type="text" value="<c:out value="${pages.searchMap['dateRange']}"/>" class="form-control pull-right" name="dateRange" id="dateRange" placeholder="<spring:message code='advertiser.input.dateDuring' />">
	                      		</div>
							</div>
							
						</div><!-- /.row -->
						</form>
					</div><!-- /.box-body -->
					</div>
                	
                	<div class="box-body  table-responsive no-padding">
          
	                  <table class="table table-striped table-condensed table-hover">
	                    <thead>
		                    <tr>
		                      <th><input type="checkbox" class="allcheck" id="allcheck" style="width: 10px"> </th>
		                      <th <tags:sort column="id" page="${pages}"/> style="width: 100px"><spring:message code="advertiser.id" /><i class="fa fa-w fa-sort"></i></th>
		                      <th <tags:sort column="clientname" page="${pages}"/>><spring:message code="advertiser.clientname" /><i class="fa fa-w fa-sort"></i></th>
		                      <th><spring:message code="advertiser.brand" /></th>
							  <th><spring:message code="advertiser.companyname" /></th>
		                      <th><spring:message code="advertiser.clientcontact" /></th>
		                      <th><spring:message code="advertiser.clientphone" /></th>
		                      <th><spring:message code="advertiser.clientposition" /></th>
		                      <th><spring:message code="advertiser.client.address" /></th>
		                      <th><spring:message code="advertiser.sales" /></th>
		                    </tr>
	                    </thead>
	                    <tbody>
	                   		<c:forEach var="client" items="${pages.content}" varStatus="status">
	                    	<tr>
	                    		<th><input type="checkbox" class="idBox" name="checkIds" value="${client.id}"></th>
	                    		<td>${client.number}</td>
                     	 		<td>${client.clientname}</td>
	                     		<td>${client.brand}</td>
	                     		<td>${client.channel_name}</td>
	                     		<td>
	                     			<c:forEach var="cantact" items="${client.contacts}">
	                     				<span style='display:block;width=100%;'>${cantact.contact_person}&nbsp;</span>
	                     			</c:forEach>
	                     		</td>
	                     		<td>
	                     			<c:forEach var="cantact" items="${client.contacts}">
	                     				<span style='display:block;width=100%;'>${cantact.phone}&nbsp;</span>
	                     			</c:forEach>
	                     		</td>
	                     		<td>
	                     			<c:forEach var="cantact" items="${client.contacts}">
	                     				<span style='display:block;width=100%;'>${cantact.position}&nbsp;</span>
	                     			</c:forEach>
	                     		</td>
	                     		<td>${client.address}</td>
	                     		<td>${client.saleNames }</td>
	                    	</tr>
	                    	</c:forEach>
                   	 	</tbody>
                  	</table>
                  
				</div><!-- /.box-body-->
                <div class="box-footer clearfix">
                	<c:if test="${pages.total != -1}">
                		<tags:pagination page="${pages}" paginationSize="5" />
                	</c:if>
                </div>
			</div></div></div>
          </section>
          
          
          <script type="text/javascript">
          	$(document).ready(function() {
        		$("menu_advertisor").addClass("active");
        		
        		$("#dateRange").daterangepicker({
        				opens:"left",
        				cancelClass:"btn-info",
        				format:'YYYY-MM-DD'
        		});
        		
        		//Flat red color scheme for iCheck
                $('input[type="checkbox"]').iCheck({
                  	checkboxClass: 'icheckbox_minimal-blue'
                });

        		//全选
                $("input[type='checkbox'].allcheck").on('ifChecked', function(event){
                	 $('input[type="checkbox"].idBox').iCheck('check');
                });
        		
                //单选
                $("input[type='checkbox'][class!='allcheck']").on("ifUnchecked",function(event){
                	$("input[type='checkbox'].allcheck").iCheck("uncheck");
                });
                
             	// 销售人员select2
                $("#saleIds").select2({
                	placeholder: "<spring:message code='business.opportunity.input.sale' />",
				    allowClear: true
				});
             	
                $("#saleIds").val("${pages.searchMap['saleIds']}").trigger("change");
        	});
          	
          	// 重置
          	function resetForm(){
          		$("#searchForm input").val('');
          		$("#searchForm select").val(null);
          		$("#channel").val(null).trigger("change"); 
          		$("#saleIds").val(null).trigger("change"); 
          	};
          	
          	function compare(){
          		var checkIdStr = "";
          		var count = 0;
          		$("input[name='checkIds']").each(function(index,element){
          			if($(this).is(':checked')){
          				if(checkIdStr==''){
          					checkIdStr += $(this).val();
          				}else{
          					checkIdStr += ","+$(this).val();
          				}
	          			count++;
          			}
          		});
          		if(count<2){
          			bootbox.dialog({
              			message: "<spring:message code="message.compare.fail"/>",
              		  	title: "",
              		  	buttons: {
              		  		cancel: {
    	          		      label: "<spring:message code='btn.cancel' />",
    	          		      className: "btn btn-primary btn-sm btn-flat",
    	          		    }
              		  	}
              		});
          		}else{
          			window.location.href="${ctx}/advertiser/merge?checkIds="+checkIdStr;
          		}
          	};
          </script>
</body>
</html>
