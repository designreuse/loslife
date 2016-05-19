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
	 <!-- Content Header -->
       <section class="content-header">
          <h1>
            <spring:message code="opportunity.header"/>
            <small><spring:message code="opportunity.header.sub"/></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Forms</a></li>
            <li class="active">General Elements</li>
          </ol>
        </section>

          <!-- Main content -->
          <section class="content">
          <div class="row">
            <div class="col-md-12">
              <div class="box">
                <div class="box-header ">
                  <h3 class="box-title">Order Table</h3>
                  <div class="btn-group pull-right">
                          <button type="button" class="btn btn-sm btn-info">search</button>
                          <a href="${ctx}/opportunity/create" class="btn btn-sm btn-primary">create</a>
                          
                        </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table ">
                    <tbody><tr>
                      <th style="width: 10px">#</th>
                      <th <tags:sort column="task" page="${pages}"/>>Task<i class="fa fa-w fa-sort"></i></th>
                      <th>Progress</th>
                      <th style="width: 40px">Label</th>
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
</body>
</html>
