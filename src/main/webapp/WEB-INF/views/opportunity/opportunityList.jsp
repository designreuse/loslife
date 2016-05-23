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
<title><spring:message code="menu.business.opportunity"/></title>
</head>
<body>
	<style type="text/css">
		.btn-sm{line-height: 1.1}
	</style>
	 <!-- Content Header -->
       <section class="content-header">
          <h1>
            <spring:message code="opportunity.header"/>
            <small><spring:message code="opportunity.header.list"/></small>
          </h1>
          
          <ol class="breadcrumb">
            <li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active"><spring:message code="opportunity.header"/></li>
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
          <div class="row">
            <div class="col-md-12">
              <div class="box">
                <div class="box-header ">
                  <h3 class="box-title"><spring:message code="opportunity.table"/></h3>
                  <div class="btn-group pull-right">
                          <button type="button" onclick="btnSearch();" class="btn btn-sm btn-info"><spring:message code="btn.search"/></button>
                          <a href="${ctx}/opportunity/create" class="btn btn-sm btn-primary"><spring:message code="btn.create"/></a>
                          
                        </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table ">
                    <tbody><tr>
                      <th style="width: 10px">#</th>
                      <th <tags:sort column="task" page="${pages}"/>><spring:message code="opportunity.task"/><i class="fa fa-w fa-sort"></i></th>
                      <th><spring:message code="opportunity.progress"/></th>
                      <th style="width: 40px"><spring:message code="opportunity.label"/></th>
                      <th style="width: 152px"><spring:message code="opportunity.operate"/></th>
                    </tr>
                    
                    <c:forEach items="${pages.content}" var="opportunity" varStatus="status">
                    	<tr>
	                      <td>${status.index + 1}.</td>
	                      <td>${opportunity.task}</td>
	                      <td>
	                        <div class="progress progress-xs progress-striped active">
	                          <div class="progress-bar ${opportunity.progressBar.barClass}" style="width: ${opportunity.progressBar.value}%"></div>
	                        </div>
	                      </td>
	                      <td><span class="badge ${opportunity.progressBar.bgClass}">${opportunity.progressBar.value}%</span></td>
	                      <td>
	                      		<a href="#" onclick="update(${opportunity.id});" class="btn btn-sm btn-warning"><i class="fa fa-w fa-pencil-square-o"></i>&nbsp;<spring:message code="btn.edit"/></a>
	                      		<a href="#" onclick="del(${opportunity.id});" class="btn btn-sm btn-danger"><i class="fa fa-w fa-trash-o"></i>&nbsp;<spring:message code="btn.delete"/></a>
	                      </td>
	                    </tr>
                    </c:forEach>
                    
                  </tbody></table>
                </div><!-- /.box-body -->
                <div class="box-footer clearfix">
                  <tags:pagination page="${pages}" paginationSize="3" />
                </div>
              </div><!-- /.box -->

              
            </div>
            </div>
          </section>
          
          
          <script type="text/javascript">
          	function update(id){
          		window.location.href="${ctx}/opportunity/update/"+id;
          	};
          	
          	function del(id){
          		if(confirm('<spring:message code="message.confirm.del"/>?')){
          			window.location.href="${ctx}/opportunity/delete/"+id;
          		}
          	};
          	
          	function btnSearch(){
          		
          	};
          </script>
</body>
</html>
