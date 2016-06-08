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
%>

<html>
<head>
<title><spring:message code="menu.advertiser"/></title>
</head>
<body>
	<style type="text/css">
		.btn-sm{line-height: 1.1}
	</style>
	 <!-- Content Header -->
       <section class="content-header">
          <h1>
            <spring:message code="menu.advertiser"/>
          </h1>
          
          <ol class="breadcrumb">
            <li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active"><spring:message code="menu.advertiser" /></li>
          </ol>
		
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissable" style="margin: 10px 0 0 0">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>	<i class="icon fa fa-check"></i> <spring:message code="message.alert.success"/>!</h4>
                    ${message}
            </div>
        </c:if>
		          
        </section>

          <!-- Main content -->
          <section class="content">
          	<div class="nav-tabs-custom">
                <div class="tab-content">
                  <div class="tab-pane active" id="activity">
                  <div class="box box-info ">
		            <div class="box-header with-border">
		              <h3 class="box-title">
		              <!-- 没有title -->
		             	<button class="btn btn-primary btn-flat btn-sm" onclick="window.location.href='${ctx}/client/create';"><i class="fa fa-w fa-pencil-square-o"></i>&nbsp;<spring:message code="btn.create"/></button>
                		<button class="btn btn-primary btn-flat btn-sm " onclick="$('#searchForm').submit();"><i class="fa fa-w fa-search"></i>&nbsp;<spring:message code="btn.search"/></button>
		              	<button class="btn btn-warning btn-flat btn-sm" onclick="resetForm();"><i class="fa fa-w fa-undo"></i>&nbsp;<spring:message code="btn.reset"/></button>
		              </h3>
		              <div class="box-tools pull-right">
		                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
		              </div>
		            </div><!-- /.box-header -->
		            <div class="box-body" style="display: block;">
		            	<form action="${ctx}/client" method="get" id="searchForm">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="client" /></label>
									<input type="text" class="form-control" name="clientname" id="clientname" value="<c:out value="${pages.searchMap['clientname']}"/>" 
									placeholder="<spring:message code="client.name.remark" />">
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="client.brand" /></label>
									<input type="text" class="form-control" name="brand" id="client_brand" value="<c:out value="${pages.searchMap['brand']}"/>" 
									placeholder="<spring:message code="client.brand.remark" />">
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
		                    	<th style="cursor: pointer;" <tags:sort column="name" page="${pages}"/>><spring:message code="client" />&nbsp;<i class="fa fa-w fa-sort"></i></th>
		                    	<th><spring:message code="public.status"/></th>
		                    	<th><spring:message code="client.brand" /></th>
		                    	<th><spring:message code="client.agency" /> </th>
		                    	<th ><spring:message code="client.industry" /></th>
		                    </tr>
                    
	                    <c:forEach items="${pages.content}" var="client" varStatus="status">
	                    	<tr>
		                     	<td>
		                     		<a href="javascript:void(0);" onclick="view(${client.id});" >${client.clientname}</a>
		                     	</td>
		                     	<td>${client.status}</td>
		                     	<td>${client.brand}</td>
		                     	<td>${client.channel_name}</td>
		                     	<td><tags:decodeList list="${industryTypes}" value="${client.industry_id}"></tags:decodeList></td>
		                    </tr>
	                    </c:forEach>
                    
                  </tbody></table>
                </div><!-- /.box-body -->
                <div class="box-footer clearfix">
                  <tags:pagination page="${pages}" paginationSize="5" />
                </div>
              </div><!-- /.box -->

              
            </div>
            </div>
          </section>
          
          
          <script type="text/javascript">
          	$(document).ready(function() {
        		$("#menu_client").addClass("active");  
        	});
          
          	function view(id){
          		window.location.href="${ctx}/client/view/"+id;
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
	          		    	window.location.href="${ctx}/client/delete/"+id;
	          		      }
	          		    }
          		  	}
          		});
          	};
          	
          	function resetForm(){
          		$("#searchForm input").val('');
          	};
          </script>
</body>
</html>
