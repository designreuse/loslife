<%@page import="java.util.ArrayList"%>
<%@page import="com.asgab.util.SelectMapper"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>商户</title>
</head>
<body>
	<style type="text/css">
		.btn-sm{line-height: 1.1;}
		.btn-50{width: 50px;}
	</style>
     <!-- Main content -->
         <div class="box" >
         <div class="box-header with-border">
           <h3 class="box-title">
           <!-- 没有title -->
           		<button class="btn btn-primary btn-sm btn-flat" onclick="$('#shield').show();$('#searchForm').submit();"><i class="fa fa-w fa-search"></i>&nbsp;<spring:message code="btn.search"/></button>
           	<button class="btn btn-warning btn-sm btn-flat" onclick="resetForm();"><i class="fa fa-w fa-undo"></i>&nbsp;<spring:message code="btn.reset"/></button>
           </h3>
           <div class="box-tools pull-right">
             <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
           </div>
         </div><!-- /.box-header -->
         <div class="box-body" style="display: block;">
           	<form action="${ctx}/user" method="get" id="searchForm">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>注册手机</label>
						<input type="text" class="form-control" name="admin_account" id="admin_account" value="<c:out value="${pages.searchMap['admin_account']}"/>" placeholder="注册手机">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>筛选条件</label>
						<%
							List<SelectMapper> list = new ArrayList<SelectMapper>();
							list.add(new SelectMapper("0","全部"));
							list.add(new SelectMapper("1","当月新增付费用户"));
							list.add(new SelectMapper("2","过期3天付费用户"));
							list.add(new SelectMapper("3","还剩5天使用期付费用户"));
							list.add(new SelectMapper("4","还剩5天过期试用用户"));
							list.add(new SelectMapper("5","所有付费用户(在使用的)"));
							list.add(new SelectMapper("6","付费3天未备份用户"));
							list.add(new SelectMapper("7","注册试用3天未备份用户"));
							request.setAttribute("list", list);
						%>
						<tags:selectbox name="searchType" clazz="select2" list="${list}" id="searchType" value="${pages.searchMap['searchType']}"> </tags:selectbox>
					</div>
				</div>
			</div><!-- /.row -->
			</form>
		</div><!-- /.box-body -->
		</div>
             	
       	<div class="box-body  table-responsive no-padding">
 
          <table class="table table-striped table-condensed table-hover">
            <tbody><tr>
              <th style="width: 110px">序号</th>
              <th>注册手机</th>
              <th <tags:sort column="version_type" page="${pages}"/>>版本<i class="fa fa-w fa-sort"></i></th>
              <th <tags:sort column="date_register" page="${pages}"/>>注册时间<i class="fa fa-w fa-sort"></i></th>
              <th <tags:sort column="expires_time" page="${pages}"/>>过期时间<i class="fa fa-w fa-sort"></i></th>
              <th <tags:sort column="lastBackup_date" page="${pages}"/>>最后备份时间<i class="fa fa-w fa-sort"></i></th>
              <th <tags:sort column="backup_count" page="${pages}"/>>备份次数<i class="fa fa-w fa-sort"></i></th>
              <th>操作</th>
            </tr>
           
            <c:forEach items="${pages.content}" var="user" varStatus="status">
            	<tr>
               <td>${status.index + 1}</td>
               <td>${user.admin_account}</td>
               <td>${user.version_type}</td>
               <td>${user.fmtDate_register}</td>
               <td>${user.fmtExpires_time}</td>
               <td>${user.fmtLastBackup_date}</td>
               <td><span class="badge">${user.backup_count}</span></td>
               <td><a href="javascript:void(0);" onclick="view(${user.id});"><i class="fa fa-w fa-search"></i>账户详情</a></td>
             </tr>
            </c:forEach>
           
        	 </tbody></table>
			</div><!-- /.box-body-->
             <div class="box-footer clearfix">
               <tags:pagination page="${pages}" paginationSize="3" />
             </div>
	
		<!-- modal -->
		<div class="modal fade" id="detailModal">
		</div>
             
             <div id="shield" style="position: fixed; left: 0px; top: 0px; display: none; z-index: 9998; opacity: 0.8; background: #7D7159; width: 100%; height: 100%;">
		<img src="${ctx}/static/images/loading_s.gif" style="position: absolute; top: 300px; left: 48%;" /></div>
          
          <script type="text/javascript">
          	$(document).ready(function() {
        		$("#menu_user").addClass("active");
        		$(".select2").select2();
        	});
          
          	function view(id){
          		$.post("${ctx}/ajax/view/"+id,{},function(data){
          			$("#detailModal").html(data);
          			$("#detailModal").modal({backdrop: 'static', keyboard: false}).show();
          		},"text");
          	};
          	
          	function resetForm(){
          		$("#admin_account").val('');
          		$("#searchType").val('0').trigger("change");
          	};
          </script>
</body>
</html>
