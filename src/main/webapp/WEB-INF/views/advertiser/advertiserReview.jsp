<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
%>

<html>
<head>
<title><spring:message code="menu.advertiser.review" /></title>
</head>
<body>
	<!-- Content Header -->
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
				<table class="table table-condensed table-striped ">
					<thead>
						<tr>
							<th style="width: 50px;"></th>
							<c:forEach items="${map['id']}" var="val" varStatus="status">
								<th style="vertical-align: bottom;"><input
									id="${status.count}" type="radio" name="id"
									class="flat-red allcheck" value="${val}"> <label
									for="${val}"
									style="vertical-align: bottom; margin-bottom: 1px;">全选</label>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="vertical-align: middle;">名称</th>
							<c:forEach items="${map['name']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-5">
										<span class="input-group-addon"> <input type="radio"
											class="${status.count}" name="cname_radio"></span> <input
											type="text" name="cname"
											class="form-control input-sm ${status.count}" value="${val}"
											disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;">品牌</th>
							<c:forEach items="${map['brand']}" var="val" varStatus="status">
								<th>
									<div class="input-group col-xs-5">
										<span class="input-group-addon"> <input type="radio"
											class="${status.count}" name="brand_radio"></span> <input
											type="text" name="brand"
											class="form-control input-sm ${status.count}" value="${val}"
											disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
						<tr>
							<th style="vertical-align: middle;">行业</th>
							<c:forEach items="${map['industry']}" var="val"
								varStatus="status">
								<th>
									<div class="input-group col-xs-5">
										<span class="input-group-addon"> <input type="radio"
											class="${status.count}" name="industry_radio"></span> <input
											type="text" name="industry"
											class="form-control input-sm ${status.count}" value="${val}"
											disabled>
									</div>
								</th>
							</c:forEach>
						</tr>
					</tbody>
				</table>

			</div>
			
			<div class="box-footer">
              <button class=" btn   btn-primary  btn-flat">保存</button>
              <button class=" btn   btn-warning  btn-flat">取消</button>
            </div>
		</div>




	</section>
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
        });
        //单选
        $("input[type='radio'][class!='allcheck']").click(function(){
        	var tetxname = this.name.replace("_radio","");
        	$("input[type='text'][name='"+tetxname+"']").attr("disabled","disabled");
        	$(this).parent().parent().children("input[type='text']").removeAttr("disabled");
        });
        
        
	 });
	</script>
</body>
</html>
