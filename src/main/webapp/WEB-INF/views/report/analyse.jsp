<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row">
	<div class="col-sm-4" style="padding-bottom: 10px">
		<div class="btn-group">
			<button type="button" class="btn btn-sm btn-primary" ><span class="fa fa-calendar"></span> 今季度(2016/04/01 - 2016/06/30)</button>
			<button type="button" class="btn btn-sm btn-primary dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="#">今季度(2016/04/01 - 2016/06/30)</a></li>
				<li><a href="#">上季度(2016/01/01 - 2016/03/30)</a></li>
			</ul>
		</div>

	</div>
</div>
<div class="row">
	<div class="col-md-4">
		<div class="box box-widget widget-user">
			<!-- Add the bg color to the header using any of the bg-* classes -->
			<div class="widget-user-header bg-gray"
				style="height: 50px; padding: 10px;">
				<h3 class="widget-user-username">收入</h3>
			</div>

			<div class="box-footer" style="padding-top: 1px;">
				<div class="row">
					<div class="col-sm-6 border-right">
						<div class="description-block">
							<h5 class="description-header text-light-blue">￥3,200</h5>
							<span class="description-text">总商机</span>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
					<div class="col-sm-6 ">
						<div class="description-block">
							<h5 class="description-header text-light-blue">￥13,000</h5>
							<span class="description-text">总签约订单</span>
						</div>
						<!-- /.description-block -->
					</div>

				</div>
				<!-- /.row -->
			</div>
		</div>

	</div>

	<div class="col-md-4">
		<div class="box box-widget widget-user">
			<!-- Add the bg color to the header using any of the bg-* classes -->
			<div class="widget-user-header bg-gray"
				style="height: 50px; padding: 10px;">
				<h3 class="widget-user-username">预估毛利</h3>
			</div>

			<div class="box-footer" style="padding-top: 1px;">
				<div class="row">
					<div class="col-sm-6 border-right">
						<div class="description-block">
							<h5 class="description-header text-light-blue">￥3,200</h5>
							<span class="description-text">总商机</span>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
					<div class="col-sm-6 ">
						<div class="description-block">
							<h5 class="description-header text-light-blue">￥13,000</h5>
							<span class="description-text">总签约订单</span>
						</div>
						<!-- /.description-block -->
					</div>

				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>



	<div class="col-md-4">
		<div class="box box-widget widget-user">
			<!-- Add the bg color to the header using any of the bg-* classes -->
			<div class="widget-user-header bg-gray "
				style="height: 50px; padding: 10px;">
				<h3 class="widget-user-username">总计</h3>
			</div>

			<div class="box-footer" style="padding-top: 1px;">
				<div class="row">
					<div class="col-sm-4 border-right">
						<div class="description-block">
							<h5 class="description-header ">33</h5>
							<span class="description-text">广告主</span>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
					<div class="col-sm-4 border-right">
						<div class="description-block">
							<h5 class="description-header ">160</h5>
							<span class="description-text">总签约订单</span>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
					<div class="col-sm-4">
						<div class="description-block">
							<h5 class="description-header ">35</h5>
							<span class="description-text">推广计划</span>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>


</div>

<div class="row">
	<div class="col-sm-12">
		<h5>签约订单</h5>
	</div>
</div>


<div class="row">
	<div class="col-md-6">

		<!-- BAR CHART -->
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">销售团队</h3>
				<div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart">
					<canvas id="barChart" style="height: 230px; width: 467px;"></canvas>
				</div>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

	</div>


	<div class="col-md-6">

		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">收入管理阶段</h3>
				<div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart"></div>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->


	</div>
</div>


<div class="row">
	<div class="col-md-6">

		<!-- BAR CHART -->
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">产品</h3>
				<div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart"></div>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->


	</div>
	<div class="col-md-6">

		<!-- BAR CHART -->
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">设备</h3>
				<div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart"></div>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->


	</div>
</div>

<script type="text/javascript">
	$(function() {

		//-------------
		//- BAR CHART -
		//-------------
		var ctx = document.getElementById("barChart").getContext("2d");
		var myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : [ "华东直客", "华东渠道", "华北直客", "华北渠道", "华北直客", "华南渠道" ],
				datasets : [
						{
							label : '收入',
							data : [ 100, 200, 300, 400, 450, 800 ],
							backgroundColor : [ 'rgba(255, 206, 86, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(255, 206, 86, 0.2)' ],
							borderColor : [ 'rgba(255, 206, 86, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(255, 206, 86, 1)' ],
							borderWidth : 1
						},
						{
							label : '预估毛利',
							data : [ 100, 200, 300, 400, 450, 800 ],
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
									'rgba(255, 99, 132, 0.2)',
									'rgba(255, 99, 132, 0.2)',
									'rgba(255, 99, 132, 0.2)',
									'rgba(255, 99, 132, 0.2)',
									'rgba(255, 99, 132, 0.2)' ],
							borderColor : [ 'rgba(255,99,132,1)',
									'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
									'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
									'rgba(255,99,132,1)' ],
							borderWidth : 1
						} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
	});
</script>
