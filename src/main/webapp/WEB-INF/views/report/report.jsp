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
		<h1><spring:message code="report.report" /></h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="report.home" /></a></li>
			<li class="active"><spring:message code="report.report" /></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#activity" data-toggle="tab"
					aria-expanded="true"><spring:message code="report.sale.data"/></a></li>
				<li class=""><a href="#timeline" data-toggle="tab"
					aria-expanded="false"><spring:message code="report.analysis"/></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="activity">

					<div class="box  box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">
								<button class=" btn btn-flat btn-primary btn-xs "><spring:message code="btn.apply" /></button>
								<button class=" btn btn-flat btn-warning btn-xs "><spring:message code="btn.reset" /></button>
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
										<label><spring:message code="report.data.right" /></label> 
										<select class="form-control select2" name="dataRight" style="width: 100%;">
											<option value="1"><spring:message code="report.product" /></option>
											<option value="2" selected="selected"><spring:message code="report.sale.team" /></option>
											<option value="3"><spring:message code="report.sale.representative" /></option>
											<option value="4"><spring:message code="report.channel.company" /></option>
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
										
										<div id="reportDateDiv" class="pull-left" style="background: #fff; cursor: pointer; padding: 7px 8px; border: 1px solid #ccc; width: 100%">
                                       			<i class="pull-left glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
                                        		<span id="reportDate" ></span> <b style="margin-top: 6px;" class="caret pull-right"></b>
                                        		<input type="hidden" name="daterange" id="daterange">
                                   		 </div>
										
									</div>
									<!-- /.form-group -->

									<div class="form-group">
										<label><spring:message code="report.progress"/></label> 
										<select class="form-control select2" name="progress" style="width: 100%;">
											<option selected="selected"><spring:message code="report.all"/></option>
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
										<label>销售团队</label> 
										<select class="form-control select2" name="saleTeam" style="width: 100%;">
											<option selected="selected">全部</option>
											<option>华东</option>
											<option>华北</option>
										</select>
									</div>
									<!-- /.form-group -->

									<div class="form-group">
										<label>销售代表</label> 
										<select class="form-control select2" name="saleRepresentative" style="width: 100%;">
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
										<label>预算</label> 
										<select class="form-control select2" name="budget" style="width: 100%;">
											<option selected="selected">全部</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
											<option><100000</option>
										</select>
									</div>


									<div class="form-group">
										<label>下单类型</label> 
										<select class="form-control select2" name="billType" style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>
								</div>

								<div class="col-md-6">

									<div class="form-group">
										<label>预估GP%</label>
										<select class="form-control select2" name="gp" style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>

									<div class="form-group">
										<label>销售收入类型</label> 
										<select class="form-control select2" name="incomeType" style="width: 100%;">
											<option selected="selected">全部</option>
										</select>
									</div>
								</div>

							</div>
							<!-- /.row -->


							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>货币</label>
										
										<tags:selectbox name="currency" list="${currencys }" clazz="select2" style="width: 100%;"></tags:selectbox>
										
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
			
			//default value
			$('#reportDate').html(
					moment().startOf('quarter').format('YYYY/MM/DD') + ' - '+ moment().endOf('quarter').format('YYYY/MM/DD'));
			$('#daterange').val(
					moment().startOf('quarter').format('YYYY/MM/DD') + ' - '+ moment().endOf('quarter').format('YYYY/MM/DD'));

			$('#reportDateDiv').daterangepicker(
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
							'This Quarter' : [moment().subtract(0, 'quarter').startOf('quarter'),
											  moment().subtract(0, 'quarter').endOf('quarter') ],
							'Last Quarter' : [moment().subtract(1, 'quarter').startOf('quarter'),
											  moment().subtract(1, 'quarter').endOf('quarter') ]
									
						},
						format : 'YYYY/MM/DD',
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
						startDate : moment().startOf('quarter'),
						endDate : moment().endOf('quarter')
					},
					function(start, end) {
						$('#reportDate').html(
								start.format('YYYY/MM/DD') + ' - '+ end.format('YYYY/MM/DD'));
						
						$('#daterange').val(start.format('YYYY/MM/DD') + ' - '+ end.format('YYYY/MM/DD'));
					});
		})
	</script>
</body>
</html>
