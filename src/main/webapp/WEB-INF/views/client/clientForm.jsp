<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>
    	<c:if test="${action eq 'create' }">新增广告主</c:if>
        <c:if test="${action eq 'update' }">编辑广告主</c:if>
    </title>

</head>



<body>

<style>
	.box{}
	.box-noborder{border-top: 0}
	.btn-100{width: 100px;line-height: 1.1}
	.client_contacts{padding: 0 15px 0 15px;}
	.content{padding-bottom: 0;min-height:0}
</style>

<!-- Content Header -->
<section class="content-header">
	<h1>
       	<c:if test="${action eq 'create' }">新增广告主</c:if>
   		<c:if test="${action eq 'update' }">编辑广告主</c:if>
    </h1>	
    <ol class="breadcrumb">
    	<li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
        <li><a href="${ctx}/client">广告主</a></li>
        <li class="active">
        	<c:if test="${action eq 'create' }">新增广告主</c:if>
   			<c:if test="${action eq 'update' }">编辑广告主</c:if>
        </li>
    </ol>
</section> 

<!-- form -->
<form role="form" action="${ctx}/client/${action}" method="post" id="primaryForm" class="form-horizontal">
<input type="hidden" name="id" id="id" value="${client.id}" />

		<!-- Main content -->
		<section class="content">
     		 <div class="box box-info">
      			 <div class="box-body">
      			 	<div class="row">
					 	<div class="col-md-6">
							<div class="form-group">
								<label for="name" class="col-md-3">广告主名称<em> *</em></label>
			                    <div class="col-md-9">
			                      	<input id="name" class="form-control" type="text" name="name" value="${client.name}" />
			                    </div>
							</div>
						
	                		<div class="form-group">
	                			<label for="brand" class="col-md-3">品牌名称<em> *</em></label>
		                      	<div class="col-md-9">
		                      		<input id="brand" class="form-control" type="text" name="brand" value="${client.brand}" />
		                      	</div>
	                		</div>
		                	
		                	 <div class="form-group">
		                	 	<label for="name" class="col-md-3">代理下单</label>
		                	 	<div class="col-md-9">
		                	 		<div class="input-group">
		                	 			<label class="input-group-addon">
		                	 				<c:if test="${client.whether_channel == 1}">
		                	 					<input type="radio" name="whether_channel" checked="checked" class="whether_channel" value="1" />
		                	 				</c:if>
		                	 				<c:if test="${client.whether_channel != 1}">
		                	 					<input type="radio" name="whether_channel"class="whether_channel" value="1" />
		                	 				</c:if>
		                	 				&nbsp;是
		                	 			</label>
		                	 			<tags:select2box name="channel" list="${agencys}" value="${client.channel}" add0="true" style="display : none;" />
		                	 			<label class="input-group-addon">
		                	 				<c:if test="${client.whether_channel == 1}">
		                	 					<input type="radio" name="whether_channel" class="whether_channel" value="0" />
		                	 				</c:if>
		                	 				<c:if test="${client.whether_channel != 1}">
		                	 					<input type="radio" name="whether_channel" checked="checked" class="whether_channel" value="0" />
		                	 				</c:if>
		                	 				&nbsp;否
		                	 			</label>
		                	 		</div>
		                	 	</div>
		                	</div>	
			                	
		                	<div class="form-group">
	                			<label for="industry_id" class="col-md-3">行业</label>
		                      	<div class="col-md-9">
		                      		<tags:selectbox name="industry_id" list="${industryTypes}" value="${client.industry_id}" add0="true" />
		                      	</div>
	                		</div>
	                		
	                		<div class="form-group">
	                			<label for="currency_id" class="col-md-3">货币</label>
		                      	<div class="col-md-9">
		                      		<tags:selectbox name="currency_id" list="${currencyTypes}" value="${client.currency_id}" add0="true" />
		                      	</div>
	                		</div>
	                	  </div>	
		             </div>
      			</div><!-- /.box-body -->
     		 </div><!-- /.box-info -->
		 </section><!--  /Main content -->  
		
		 <!-- part2 -->
		 <section class="content">
		 		<div class="box box-noborder">
		 				<div class="box-body">
		 						<div class="row">
									<div class="col-md-12">
							            <div class="form-group">
						                  	<label class="col-md-3">广告主联系人&nbsp;<i class="fa fa-w fa-caret-down"></i></label>
						                 	<div class="divider-horizontal"></div>
						                </div>
								    </div>
								</div>
		 				
					 			<div class="client_contacts" id="client_contacts">
			                    	<jsp:include page="include/clientContactEdit.jsp"></jsp:include>
			                    </div>
		 						
		 						<div class="row">
				           			<div class="col-md-6">
				           				<div class="form-group">
					                      	<label class="col-md-3"><a href="javascript:void(0);" onclick="add_client_contacts();">
					                      		<i class="fa fa-w fa-plus-square"></i>&nbsp;再添加一个联系人</a>
					                      	</label>
					                    </div>
				                    </div>
				                </div>
		 				</div><!-- /.box-body -->
		 		</div><!-- /.box-noborder -->
		 </section><!-- /part2 -->
		 
		 
		 <!-- part3 -->
		 <section class="content">
		 		<div class="box box-noborder">
		 				<div class="box-body">
		 						<div class="row">
		 						
		 							<div class="col-md-6">
		                
					                	<div class="form-group">
											<label for="name" class="col-md-3">销售人员<em> *</em></label>
						                    <div class="col-md-9">
						                    	<tags:select2box name="userIds" list="${users}" value="${client.userIds}" add0="true" />
						                    </div>
										</div>
										
										<div class="form-group">
											<label for="name" class="col-md-3">是否跨区<br/>（跨区的需要跨区特批）</label>
						                    <div class="col-md-9">
						                    	<c:if test="${client.cross_regional == 1}" >
						                    		<input type="checkbox" name="cross_regional" id="cross_regional" class="cross_regional" checked="checked" value="1"/>
						                    	</c:if>
						                    	<c:if test="${client.cross_regional != 1}" >
						                    		<input type="checkbox" name="cross_regional" id="cross_regional" class="cross_regional" value="0"/>
						                    	</c:if>	
						                    </div>
										</div>
					                
					                </div>
		 						
		 						</div><!-- /.row -->
		 				</div><!-- /.box-body -->
		 				
		      		 	<div class="box-footer">
				          	 <button type="submit" class="btn btn-primary btn-sm" onclick="$('#primaryForm').submit();"><spring:message code="btn.submit"/></button>
				          	 <button class="btn btn-primary btn-sm disabled" onclick="window.location.href='${ctx}/client'"><spring:message code="btn.cancel"/></button>
				    	</div>
		 		</div><!-- /.box-noborder -->
		 </section><!-- /part3 -->
		 

</form><!-- /form -->

          
<script>    
	$(document).ready(function() {
		$("#menu_advertiser").addClass("active");
		
		$("#primaryForm").validate({
			
		});
		
		$(".channel").select2({
			multiple: false
		});
		
		$(".userIds").select2({
			multiple: true
		});
		
		$(".whether_channel").click( function(){
			if($(this).val() == '2'){
				
			}else{
				
			}
		});
		
		<c:if test="${action eq 'create' }">add_client_contacts();</c:if>
		<c:if test="${action eq 'update' }">bind_hover();bind_remove();</c:if>
	});
	
	// 再添加一个联系人
	function add_client_contacts(){
		var index = $("#client_contacts div.contacts").size();
		$.post("${ctx}/ajax/addContact",{index:index},function(html){
			$("#client_contacts").append(html);
			bind_hover();
			bind_remove();
		},"html");
	};
	
	function bind_remove(){
		$("label[id^='rm-contact-']").click( function(){
			if(confirm("确认删除？")){
				var index = $(this).attr('id').split('-')[2];
				var rm_id = $('#client_contact_' + index + ' .contact_id').val();
				if(rm_id != null && dm_id != undefined){
					$("#primaryForm").append('<input type="text" name="deleteContactIds" value="' + rm_id + '" >');
				}
				$('#client_contact_' + index).remove();
			}
		});
	};
	
	function bind_hover(){
		$("label[id^='rm-contact-']").mouseover( function(){
			var index = $(this).attr('id').split('-')[2];
			$("#client_contact_" + index +" input").css('border-color','red');
		});
		
		$("label[id^='rm-contact-']").mouseout( function(){
			var index = $(this).attr('id').split('-')[2];
			$("#client_contact_" + index +" input").css('border-color','#d2d6de');
		});
	};
</script>
</body>
</html>
 



