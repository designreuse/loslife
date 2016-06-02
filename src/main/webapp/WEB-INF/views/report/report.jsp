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
            报表
            <small>Preview</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
            <li class="active">报表</li>
          </ol>
        </section>

          <!-- Main content -->
          <section class="content">
          
          
           <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#activity" data-toggle="tab" aria-expanded="true">销售数据</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">分析</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表1</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表2</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表3</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表4</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表5</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表6</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表7</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表8</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表9</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表10</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表11</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表12</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表13</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表14</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表15</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表16</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表17</a></li>
                  <li class=""><a href="#timeline" data-toggle="tab" aria-expanded="false">报表18</a></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="activity">
                   		
					            <div class="box box-info ">
					            <div class="box-header with-border">
					              <h3 class="box-title">
                   					<button class=" btn   btn-xs ">查询</button>
                   					<button class=" btn   btn-warning btn-xs ">重置</button>
					              </h3>
					              <div class="box-tools pull-right">
					                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					              </div>
					            </div><!-- /.box-header -->
					            <div class="box-body" style="display: block;">
					              <div class="row">
					                <div class="col-md-6">
					                  <div class="form-group">
					                    <label>销售团队</label>
					                    <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
					                      <option selected="selected">Alabama</option>
					                      <option>Alaska</option>
					                      <option>California</option>
					                      <option>Delaware</option>
					                      <option>Tennessee</option>
					                      <option>Texas</option>
					                      <option>Washington</option>
					                    </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-xqlg-container"><span class="select2-selection__rendered" id="select2-xqlg-container" title="Alabama">Alabama</span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
					                  </div><!-- /.form-group -->
					                  <div class="form-group">
					                    <label>收入管理阶段</label>
					                    <select class="form-control select2 select2-hidden-accessible" disabled="" style="width: 100%;" tabindex="-1" aria-hidden="true">
					                      <option selected="selected">Alabama</option>
					                      <option>Alaska</option>
					                      <option>California</option>
					                      <option>Delaware</option>
					                      <option>Tennessee</option>
					                      <option>Texas</option>
					                      <option>Washington</option>
					                    </select><span class="select2 select2-container select2-container--default select2-container--disabled" dir="ltr" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false" tabindex="-1" aria-labelledby="select2-7ats-container"><span class="select2-selection__rendered" id="select2-7ats-container" title="Alabama">Alabama</span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
					                  </div><!-- /.form-group -->
					                </div><!-- /.col -->
					                <div class="col-md-6">
					                  <div class="form-group">
					                    <label>收入</label>
					                    <select class="form-control select2 select2-hidden-accessible" multiple="" data-placeholder="Select a State" style="width: 100%;" tabindex="-1" aria-hidden="true">
					                      <option>Alabama</option>
					                      <option>Alaska</option>
					                      <option>California</option>
					                      <option>Delaware</option>
					                      <option>Tennessee</option>
					                      <option>Texas</option>
					                      <option>Washington</option>
					                    </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--multiple" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false" tabindex="0"><ul class="select2-selection__rendered"><li class="select2-search select2-search--inline"><input class="select2-search__field" type="search" tabindex="-1" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" role="textbox" placeholder="Select a State" style="width: 476px;"></li></ul></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
					                  </div><!-- /.form-group -->
					                </div><!-- /.col -->
					              </div><!-- /.row -->
					            </div><!-- /.box-body -->
					          </div>
					          
                   			
                   		<div class="box-header with-border">
		                  <h3 class="box-title ">总销售报数 <b class="text-green">￥32,342,100</b></h3><a class="pull-right"><i class="fa fa-fw fa-download"></i>下载</a>
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
                   		
                   		
                   		
                  	</div><!-- /.tab-pane -->
                  <div class="tab-pane" id="timeline">
                   
                   
                   
                   <div class="box-body table-responsive no-padding">
                  <table class="table table-striped table-condensed table-hover">
                    <tbody><tr>
                      <th style="width: 10px">#</th>
                      <th>Task</th>
                      <th>Progress</th>
                      <th style="width: 40px">Label</th>
                    </tr>
                    <tr>
                      <td>1.</td>
                      <td>Update software</td>
                      <td>
                        <div class="progress progress-xs">
                          <div class="progress-bar progress-bar-danger" style="width: 55%"></div>
                        </div>
                      </td>
                      <td><span class="badge bg-red">55%</span></td>
                    </tr>
                    <tr>
                      <td>2.</td>
                      <td>Clean database</td>
                      <td>
                        <div class="progress progress-xs">
                          <div class="progress-bar progress-bar-yellow" style="width: 70%"></div>
                        </div>
                      </td>
                      <td><span class="badge bg-yellow">70%</span></td>
                    </tr>
                    <tr>
                      <td>3.</td>
                      <td>Cron job running</td>
                      <td>
                        <div class="progress progress-xs progress-striped active">
                          <div class="progress-bar progress-bar-primary" style="width: 30%"></div>
                        </div>
                      </td>
                      <td><span class="badge bg-light-blue">30%</span></td>
                    </tr>
                    <tr>
                      <td>4.</td>
                      <td>Fix and squish bugs</td>
                      <td>
                        <div class="progress progress-xs progress-striped active">
                          <div class="progress-bar progress-bar-success" style="width: 90%"></div>
                        </div>
                      </td>
                      <td><span class="badge bg-green">90%</span></td>
                    </tr>
                  </tbody></table>
                </div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
          </div>
          </section>
          
          <script type="text/javascript">
            //默认收起查询框
            $(function(){  
        	    $(".btn-box-tool").trigger( "click" );
        	});  
          </script>
</body>
</html>
