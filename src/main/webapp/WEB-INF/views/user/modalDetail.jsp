<%@page import="com.asgab.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.modal-content .control-label{ margin-bottom: 10px!important; }
</style>
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
       						<label class="control-label col-md-4">企业名称</label>
       						<label class="control-label col-md-8">
       							<c:if test="${not empty user.name}">${user.name}</c:if>
       							<c:if test="${empty user.name}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
       						<label class="control-label col-md-4">版本类型</label>
       						<label class="control-label col-md-8">
       							<c:if test="${not empty user.version_type}">${user.version_type}</c:if>
       							<c:if test="${empty user.version_type}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">账户余额</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.accountMoney}">${user.accountMoney}</c:if>
       							<c:if test="${empty user.accountMoney}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">注册时间</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.fmtDate_register2}">${user.fmtDate_register2}</c:if>
       							<c:if test="${empty user.fmtDate_register2}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">登录设备</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.deviceName}">${user.deviceName}</c:if>
       							<c:if test="${empty user.deviceName}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">会员数</label>
	       					<label class="control-label col-md-8">UNKNOW</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">产品数</label>
	       					<label class="control-label col-md-8">${user.serviceCount}</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">总备份数</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.backup_count}">${user.backup_count}</c:if>
       							<c:if test="${empty user.backup_count}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">代理商</label>
	       					<label class="control-label col-md-8">UNKNOW</label>
	       				</div>
	       			</div>
	       			
	       			<div class="col-md-6">
	       				<div class="form-group">
	       					<label class="control-label col-md-4">手机号码</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.contact_phoneMobile}">${user.contact_phoneMobile}</c:if>
       							<c:if test="${empty user.contact_phoneMobile}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">内部ID</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.id}">${user.id}</c:if>
       							<c:if test="${empty user.id}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">短信余额</label>
	       					<label class="control-label col-md-8">UNKNOW</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">到期时间</label>
	       					<label class="control-label col-md-8">
       							<c:if test="${not empty user.fmtExpires_time2}">${user.fmtExpires_time2}</c:if>
       							<c:if test="${empty user.fmtExpires_time2}">&nbsp;</c:if>
       						</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">归属地</label>
	       					<label class="control-label col-md-8">UNKNOW</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">会员卡类型数</label>
	       					<label class="control-label col-md-8">${user.membercardcategoryCount}</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">员工数</label>
	       					<label class="control-label col-md-8">${user.employeeCount}</label>
	       				</div>
	       				<div class="form-group">
	       					<label class="control-label col-md-4">最近三天备份数</label>
	       					<label class="control-label col-md-8" id="backupHistoryCount">&nbsp;</label>
	       				</div>
	       				<div class="form-group">
	       					<div class="form-group">
	       						<label class="control-label col-md-4">解锁</label>
		       					<div class="col-md-8">
		       					<input type="button" onclick="unlockDevice();" class="btn btn-flat btn-primary btn-sm" value="设备解锁">
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
        		  	<c:forEach items="${user.rechargeRecords}" var="rechargeRecord">
        		  		<tr>
        		  		<td>${rechargeRecord.fmtPay_date}</td>
        		  		<td>${rechargeRecord.version_type }</td>
        		  		<td>${rechargeRecord.money}</td>
        		  		<td>${rechargeRecord.day}</td>
        		  		<td>${rechargeRecord.fmtExpires_time }</td>
        		  		</tr>
        		  	</c:forEach>
        		  </table>
        		 </div>
        		 </div>
	      </div>
	      </div>
	      
	      <div class="modal-footer">
	      	<label class="pull-left"><font color="red">值为UNKNOW的,目前还没在数据库找到对应的字段.其他都是正确的</font></label>
	        <button type="button" class="btn btn-default btn-sm btn-flat" data-dismiss="modal">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	  
	   <script type="text/javascript">
          	$(document).ready(function() {
       			//延迟加载 3天备份数. 比较慢
       			$.post("${ctx}/ajax/get3DaysBackupHistory/${user.id}",{},function(data){
       				$("#backupHistoryCount").text(data);
       			},"text");
          	});
          	
          	function unlockDevice(){
          		$.post("${ctx}/ajax/unlockDevice/${user.admin_account}",{},function(data){
          			var html = "";
          			if(data == "true"){
          					html += "<div class='alert alert-success alert-dismissable' style='margin: 10px 0 0 0'>";
                            html += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button>";
                            html += "<h4><i class='icon fa fa-check'></i> 设备已解锁!</h4></div>";
                    		
          			}else{
          				html = "<div class='alert alert-error alert-dismissable' style='margin: 10px 0 0 0'>";
                        html += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button>";
                        html += "<h4><i class='icon fa  fa-times'></i> 解锁失败!</h4>数据异常,";
                        html += "将跳转乐斯生活后台进行解锁.&nbsp;&nbsp;<a href='http://www.yilos.com/admin/' target='_blank' >点我继续</a> </div>";
          			}
          			$(".modal-title").after(html);
          		},"text");
          	}
       </script>