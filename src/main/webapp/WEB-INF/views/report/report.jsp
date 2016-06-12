<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
<title><spring:message code="menu.report" /></title>
</head>
<body>

	<!-- Content Header -->
	<section class="content-header">
		<h1>报表</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
			<li class="active">报表</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#activity" data-toggle="tab"
					aria-expanded="true">销售数据</a></li>
				<li class=""><a href="#timeline" data-toggle="tab"
					aria-expanded="false">分析</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="activity">

					<div class="box  box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">
								<button class=" btn btn-flat btn-primary btn-xs ">应用</button>
								<button class=" btn btn-flat btn-warning btn-xs ">重置</button>
							</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->

						<div class="box-body" style="display: block;">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>可见维度</label> <select class="form-control select2"
											style="width: 100%;">
											<option>产品</option>
											<option selected="selected">销售团队</option>
											<option>销售代表</option>
											<option>代理公司</option>
										</select>
									</div>
									<!-- /.form-group -->

								</div>
								<!-- /.col -->

								<div class="col-md-6"></div>
								<!-- /.col -->

							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->

						<div class="box-footer">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>时间</label>

										<div class="input-group">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" class="form-control pull-left"
												id="reservation">
										</div>
									</div>
									<!-- /.form-group -->

									<div class="form-group">
										<label>进度</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
											<option>10%</option>
											<option>30%</option>
											<option>50%</option>
											<option>70%</option>
											<option>90%</option>
											<option>100%</option>
										</select>
									</div>
									<!-- /.form-group -->

								</div>
								<!-- /.col -->

								<div class="col-md-6">
									<div class="form-group">
										<label>销售团队</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
											<option>华东</option>
											<option>华北</option>
										</select>
									</div>
									<!-- /.form-group -->

									<div class="form-group">
										<label>销售代表</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>


								</div>
								<!-- /.col -->

							</div>
							<!-- /.row -->


							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>预算</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
										</select>
									</div>


									<div class="form-group">
										<label>下单类型</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>
								</div>

								<div class="col-md-6">

									<div class="form-group">
										<label>预估GP%</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>

									<div class="form-group">
										<label>销售收入类型</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>
								</div>

							</div>
							<!-- /.row -->


							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>货币</label> <select class="form-control select2"
											style="width: 100%;">
											<option selected="selected">全部</option>
											<option value="RMB">RMB</option>
											<option value="USD">USD</option>
											<option value="SGD">SGD</option>
											<option value="TWD">TWD</option>
											<option value="KRW">KRW</option>
											<option value="JPY">JPY</option>
											<option value="MYR">MYR</option>
											<option value="GBP">GBP</option>
											<option value="EUR">EUR</option>
											<option value="AUD">AUD</option>
											<option value="THB">THB</option>
											<option value="RUB">RUB</option>
											<option value="IDR">IDR</option>
										</select>
									</div>

								</div>

								<div class="col-md-6"></div>

							</div>
							<!-- /.row -->

						</div>
						<!-- /.box-footer -->

					</div>


					<div class="box-header with-border">
						<h3 class="box-title ">
							总销售报数 <b class="text-green">￥32,342,100</b>
						</h3>
						<a class="pull-right"><i class="fa fa-fw fa-download"></i>下载</a>
					</div>

					<div class="box-body  table-responsive no-padding">
						<table class="table table-striped table-condensed table-hover ">
							<tbody>
								<tr>
									<th>销售团队</th>
									<th>商机</th>
									<th>签约订单</th>
									<th>销售报数</th>

								</tr>
								<tr>
									<td>华东直客</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
								</tr>
								<tr>
									<td>华东直客</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
								</tr>
								<tr>
									<td>华东直客</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
								</tr>
								<tr>
									<td>华东直客</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
								</tr>
								<tr>
									<td>华东直客</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
									<td>￥123,232</td>
								</tr>


							</tbody>
						</table>
					</div>



				</div>
				<!-- /.tab-pane -->
				<div class="tab-pane" id="timeline">
					<jsp:include page="analyse.jsp"></jsp:include>
				</div>
				<!-- /.tab-pane -->
			</div>
			<!-- /.tab-content -->
		</div>
	</section>
	<script type="text/javascript">
		$(function() {
			$(".select2").select2();

			$('#reservation').daterangepicker(
					{
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),moment() ],
							'This Month' : [ moment().startOf('month'),moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf('month'),
									moment().subtract(1, 'month').endOf('month') ],
							'Last Three Month' : [
									moment().subtract(2, 'month').startOf('month'),
									moment().subtract(0, 'month').endOf('month') ]
						},
					    locale :{
			                applyLabel: 'Apply',
			                cancelLabel: 'Cancel',
			                fromLabel: 'From',
			                toLabel: 'To',
			                weekLabel: 'W',
			                customRangeLabel: 'Custom Range',
			                daysOfWeek: moment.weekdaysMin(),
			                monthNames: moment.monthsShort(),
			                firstDay: moment.localeData()._week.dow
			            },
						startDate : moment().subtract(29, 'days'),
						endDate : moment()
					},
					function(start, end) {
						$('#reservation').val(
								start.format('YYYY/MM/DD') + ' - '+ end.format('YYYY/MM/DD'));
					});
		})
	</script>
</body>
</html>
