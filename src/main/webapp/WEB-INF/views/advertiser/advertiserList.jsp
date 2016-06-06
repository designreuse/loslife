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
            <li><a href="${ctx}/advertiser"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
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
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="advertiser.clientname"/></label>
									<input type="text" class="form-control" name="name" id="name" value="<c:out value="${pages.searchMap['name']}"/>" placeholder="<spring:message code='advertiser.input.clientname'/>">
								</div>
								<div class="form-group">
									<label><spring:message code="advertiser.sales"/></label>
									<input type="text" class="form-control" name="user_id" id="user_id" value="<c:out value="${pages.searchMap['user_id']}"/>" placeholder="<spring:message code='advertiser.input.sales'/>">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><spring:message code="advertiser.brand"/></label>
									<input type="text" class="form-control" name="brand" id="brand" value="<c:out value="${pages.searchMap['brand']}"/>" placeholder="<spring:message code='advertiser.input.brand'/>">
								</div>
								<div class="form-group">
									<label><spring:message code="advertiser.platform"/></label>
									<input type="text" class="form-control" name="platform" id="platform" value="<c:out value="${pages.searchMap['platform']}"/>" placeholder="<spring:message code='advertiser.input.platform'/>">
								</div>
							</div>
							<div class="col-md-4">
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
	                    <tbody>
	                    <tr>
	                      <th><input type="checkbox" class="allcheck" id="allcheck" style="width: 10px"> </th>
	                      <th <tags:sort column="id" page="${pages}"/> style="width: 100px"><spring:message code="opportunity.id" /><i class="fa fa-w fa-sort"></i></th>
	                      <th <tags:sort column="clientname" page="${pages}"/>><spring:message code="advertiser.clientname" /><i class="fa fa-w fa-sort"></i></th>
	                      <th ><spring:message code="advertiser.brand" /></th>
						  <th ><spring:message code="advertiser.companyname" /></th>
	                      <th ><spring:message code="advertiser.clientcontact" /></th>
	                      <th ><spring:message code="advertiser.clientphone" /></th>
	                      <th ><spring:message code="advertiser.clientposition" /></th>
	                      <th ><spring:message code="advertiser.clientaddress" /></th>
	                      <th ><spring:message code="advertiser.sales" /></th>
	                    </tr>
                    
	                    <c:forEach items="${pages.content}" var="client" varStatus="status">
	                    	<tr>
	                    	  <th><input type="checkbox" class="idBox" name="checkIds" value="${client.id}"> </th>
		                      <td>${client.id}</td>
		                      <td>${client.name}</td>
		                      <td>${client.brand}</td>
		                      <td>${client.name}</td>
		                      <td>${client.linkman_name}</td>
		                      <td>${client.linkman_tel}</td>
		                      <td>${client.linkman_position}</td>
		                      <td>${client.address }</td>
		                      <td>${client.user_id }</td>
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
        		
        		$("#dateRange").daterangepicker({opens:"left",cancelClass:"btn-info",format:'YYYY-MM-DD'});
        		
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
        		
        	});
          	
          	function resetForm(){
          		$("#name").val('');
          		$("#brand").val('');
          		$("#user_id").val('');
          		$("#platform").val('');
          		$("#dateRange").val('');
          	};
          	
          	function compare(){
          		var checkIdStr = "";
          		var count = 0;
          		$("input[name='checkIds']").each(function(index,element){
          			if($(this).is(':checked')){
	          			checkIdStr += $(this).val()+",";
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
