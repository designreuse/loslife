<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 <!-- modal  -->
	
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">商户详情</h4>
	      </div>
	      <div class="modal-body">
	       	<div class="row">
	       	<form action="" method="get">
	       			<div class="col-md-6">
	       				<div class="form-group">
	       					<label class="control-label col-md-3">注册时间</label>
	       					<label class="control-label col-md-9">2011-11-11 12:12</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-3">登录设备</label>
	       					<label class="control-label col-md-9">iPad</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-3">会员数</label>
	       					<label class="control-label col-md-9">128</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-3">产品数</label>
	       					<label class="control-label col-md-9">30</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-3">总备份数</label>
	       					<label class="control-label col-md-9">1987</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-3">代理商</label>
	       					<label class="control-label col-md-9">Microsoft</label>
	       				</div>
	       			</div>
	       			
	       			<div class="col-md-6">
	       				<div class="form-group">
	       					<label class="control-label col-md-4">到期时间</label>
	       					<label class="control-label col-md-8">2019-11-11 12:12</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">归属地</label>
	       					<label class="control-label col-md-8">China</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">会员卡类型</label>
	       					<label class="control-label col-md-8">12</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">员工数</label>
	       					<label class="control-label col-md-8">10</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">最近三天备份数</label>
	       					<label class="control-label col-md-8">22</label>
	       				</div>
	       				<div class="form-group">
	       					<div class="form-group">
	       						<label class="control-label col-md-4">解锁</label>
		       					<div class="col-md-8">
		       					<input type="button" class="btn btn-flat btn-primary btn-sm" value="设备解锁">
		       					</div>
	       					</div>
	       				</div>
	       			</div>
	       		</form>
	       	</div>
	       	
	       	
	       	<div class="row" style="margin-top: 10px;">
	      		<div class="box-body  table-responsive no-padding">
	      		 <div class="col-md-12">
        		  <table class="table table-striped table-condensed table-hover">
        		  	<tr>
        		  		<th>充值时间</th>
        		  		<th>版本</th>
        		  		<th>充值金额</th>
        		  		<th>天数</th>
        		  		<th>过期时间</th>
        		  	</tr>
        		  	<tr>
        		  		<td>2011-11-11 12:12</td>
        		  		<td>tril</td>
        		  		<td>0</td>
        		  		<td>30</td>
        		  		<td>2011-12-11 12:12</td>
        		  	</tr>
        		  	<tr>
        		  		<td>2011-11-11 12:12</td>
        		  		<td>profession</td>
        		  		<td>480</td>
        		  		<td>365</td>
        		  		<td>2012-12-11 12:12</td>
        		  	</tr>
        		  </table>
        		 </div>
        		 </div>
	      </div>
	       	
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default btn-sm btn-flat" data-dismiss="modal">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->