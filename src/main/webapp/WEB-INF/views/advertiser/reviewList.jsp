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
<title><spring:message code="menu.business.opportunity"/></title>
</head>
<body>
	<style type="text/css">
		.btn-sm{line-height: 1.1;}
		.btn-50{width: 50px;}
	</style>
	 <!-- Content Header -->
       <section class="content-header">
          <h1>
            <spring:message code="menu.business.opportunity"/>
          </h1>
          
          <ol class="breadcrumb">
            <li><a href="${ctx}/businessOpportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active"><spring:message code="opportunity.header"/></li>
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
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="business.opportunity.advertiser"/></label>
									<input type="text" class="form-control" name="clientname" id="clientname" value="<c:out value="${pages.searchMap['clientname']}"/>" placeholder="<spring:message code='business.opportunity.input.advertiser'/>">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="business.opportunity.advertiser"/></label>
									<input type="text" class="form-control" name="client_brand" id="client_brand" value="<c:out value="${pages.searchMap['clientname']}"/>" placeholder="<spring:message code='business.opportunity.input.advertiser'/>">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="business.opportunity.advertiser"/></label>
									<input type="text" class="form-control" name="industry_id" id="industry_id" value="<c:out value="${pages.searchMap['clientname']}"/>" placeholder="<spring:message code='business.opportunity.input.advertiser'/>">
								</div>
							</div>
						</div><!-- /.row -->
						</form>
					</div><!-- /.box-body -->
					</div>
                	
                	<div class="box-body  table-responsive no-padding">
          
	                  <table class="table table-striped table-condensed table-hover">
	                    <tbody>
	                    <tr>
	                      <th <tags:sort column="id" page="${pages}"/> style="width: 110px"><spring:message code="opportunity.id" /><i class="fa fa-w fa-sort"></i></th>
	                      <th <tags:sort column="clientname" page="${pages}"/>>广告主名称<i class="fa fa-w fa-sort"></i></th>
	                      <th style="width: 80px">广告主品牌</th>
	                      <th >行业</th>
						  <th >是否渠道</th>
	                      <th >公司地址</th>
	                      <th >网站名称</th>
	                    </tr>
                    
	                    <c:forEach items="${pages.content}" var="review" varStatus="status">
	                    	<tr>
		                      <td>${review.id}</td>
		                      <td>${review.clientname}</td>
		                      <td>${review.client_brand}</td>
		                      <td>${review.industry_id}</td>
		                      <td>${review.whether_channe}</td>
		                      <td>${review.company_adress}</td>
		                      <td>${review.website_name}</td>
		                      
		                    </tr>
	                    </c:forEach>
                    
                  </tbody></table>
				</div><!-- /.box-body-->
                <div class="box-footer clearfix">
                  <tags:pagination page="${pages}" paginationSize="3" />
                </div>
			</div></div></div>
          </section>
          
          
          <script type="text/javascript">
          	$(document).ready(function() {
        		$("menu_advertisor").addClass("active");  
        	});
          
          	function view(id){
          		window.location.href="${ctx}/businessOpportunity/view/"+id;
          	};
          	
          	function del(id){
          		bootbox.dialog({
          			message: "<spring:message code="message.confirm.del"/>?",
          		  	title: "",
          		  	buttons: {
          		  		cancel: {
	          		      label: "<spring:message code='btn.cancel' />",
	          		      className: "",
	          		      callback: function() {
	          		    	
	          		      }
	          		    },
	          			success: {
	          		      label: "<spring:message code='btn.delete' />",
	          		      className: "btn-danger",
	          		      callback: function() {
	          		    	window.location.href="${ctx}/businessOpportunity/delete/"+id;
	          		      }
	          		    }
          		  	}
          		});
          	};
          	
          	function resetForm(){
          		$("#advertiser").val('');
          	};
          </script>
</body>
</html>
