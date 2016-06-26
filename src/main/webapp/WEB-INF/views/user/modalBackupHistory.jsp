<%@page import="com.asgab.entity.BackupHistory"%>
<%@page import="java.util.List"%>
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
	        <h4 class="modal-title">备份历史记录</h4>
	      </div>
	      <div class="modal-body">
	      		<%
	      			List<List<BackupHistory>>  backupHistories = (List<List<BackupHistory>>)request.getAttribute("backupHistories");
	      			if(backupHistories.size()>0){
	      			for(int i = 0 ; i < backupHistories.size();i++){
	      				List<BackupHistory> tmpHistories = backupHistories.get(i);
						%>
							<ul class="timeline">
								<li class="time-label">
						        <span class="bg-red">
						            <%=tmpHistories.get(0).getFmtBackup_time()%>
						        </span>
						    	</li>
						<%
	      				for(int j = 0 ;j < tmpHistories.size();j++){
	      					BackupHistory tmpHistory = tmpHistories.get(j);
	      					%>
	      						<li>
							        <i class="fa fa-cloud-upload bg-blue"></i>
							        <div class="timeline-item">
							            <h3 class="timeline-header"><%=tmpHistory.getFmtBackup_time2()%></h3>
							        </div>
							    </li>
	      					<%
	      					if(i == backupHistories.size()-1 && j == tmpHistories.size()-1){
								%>
									<li>
								        <i class="fa fa-clock-o bg-gray"></i>
								        <div class="timeline-item">
								            <h3 class="timeline-header">...</h3>
								        </div>
								    </li>
								<%
							}
	      				}
						
							%>
							</ul>
							<%
	      			}
	      			}else{
	      				%>
	      					<div align="center">
	      						<label>暂无备份记录</label>
	      					</div>
	      				<%
	      			}
	      		%>
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default btn-sm btn-flat" data-dismiss="modal">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	  
	   <script type="text/javascript">
          	$(document).ready(function() {
          	});
       </script>