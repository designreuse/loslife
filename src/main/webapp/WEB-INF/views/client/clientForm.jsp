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

<!-- Main content -->
<section class="content">
      <div class="box box-info">
      		<div class="box-body">
      			 <form role="form" action="${ctx}/client/${action}" method="post" id="primaryForm" class="form-horizontal">
						<input type="hidden" name="id" id="id" value="${client.id}" />
						<div class="col-md-8">
						
								<div class="form-group">
									<label for="name" class="col-md-3">广告主名称<em> *</em></label>
				                    <div class="col-md-9">
				                      	<input id="name" class="form-control" type="text" name="name" value="" />
				                    </div>
								</div>
							
		                		<div class="form-group">
		                			<label for="brand" class="col-md-3">品牌名称<em> *</em></label>
			                      	<div class="col-md-9">
			                      		<input id="brand" class="form-control" type="text" name="brand" value="" />
			                      	</div>
		                		</div>
		                	
			                	 <div class="form-group">
			                	 	<label for="name" class="col-md-3">代理下单</label>
			                	 	<div class="col-md-9">
			                	 		<div class="input-group">
			                	 			<label class="input-group-addon"><input type="radio" name="whether_channel" class="whether_channel" value="1"/ >&nbsp;是</label>
			                	 			<tags:selectbox name="channel" map="${agencys}" value="${client.channel}" add0="true" style="display : none;" />
											<label class="input-group-addon"><input type="radio" name="whether_channel" class="whether_channel" value="0" />&nbsp;否</label>
			                	 		</div>
			                	 	</div>
			                	</div>	
			                	
			                	<div class="form-group">
		                			<label for="industry_id" class="col-md-3">行业</label>
			                      	<div class="col-md-9">
			                      		<tags:selectbox name="industry_id" map="${industryTypes}" value="${client.industry_id}" add0="true" />
			                      	</div>
		                		</div>
		                		
		                		<div class="form-group">
		                			<label for="currency_id" class="col-md-3">货币</label>
			                      	<div class="col-md-9">
			                      		<tags:selectbox name="currency_id" map="${currencyTypes}" value="${client.currency_id}" add0="true" />
			                      	</div>
		                		</div>
		                		
		                		
		                </div>
		                
		                <%-- list 广告主联系人 --%>	  
		                <div class="col-md-12">
			                <div class="form-group contact-list">
			                	<label for="" class="col-md-2"></label>
			                	<div class="col-md-3">
			                		<span class="glyphicon glyphicon-remove-circle" id="rm-0">&nbsp;</span>
			                	</div>
			                </div>	
		                </div>
		                
		                
		                <%-- add 广告主联系人 --%>	  
		                <div class="col-md-12 client-contacts">
		                	<div class="form-group contact_persons">
                      			<label for="" class="col-md-2">广告主联系人</label>
                      			<div class="div-0 col-md-6">
                      				<input id="contact[]" class="form-control" type="text" name="contacts[0].contact_person" value="" />
                      			</div>
                      		</div>
                      		
                      		<div class="form-group positions">
                      			<label for="" class="col-md-2">联络人职位</label>
                      			<div class="div-0 col-md-6">
                      				<input id="brand" class="form-control" type="text" name="contacts[0].position" value="" />
                      			</div>
                      		</div>
                      		
                      		<div class="form-group phones">
                      			<label for="" class="col-md-2">联络人电话</label>
                      			<div class="div-0 col-md-6">
                      				<input id="brand" class="form-control" type="text" name="contacts[0].phone" value="" />
                      			</div>
                      		</div>
                      		
                      		<div class="form-group emails">
                      			<label for="" class="col-md-2">联络人邮箱</label>
                      			<div class="div-0 col-md-6">
                      				<input id="brand" class="form-control" type="text" name="contacts[0].email" value="" />
                      			</div>
                      		</div>
                      		
                      		<div class="form-group addresses">
                      			<label for="" class="col-md-2">公司地址</label>
                      			<div class="div-0 col-md-6">
                      				<input id="brand" class="form-control" type="text" name="contacts[0].address" value="" />
                      			</div>
                      		</div>
		                </div>
		                
		                <div class="col-md-12">
		                <div class="form-group">
		                	<span class="glyphicon glyphicon-plus" onclick="add_client_contacts();">再添加一个联系人</span>
		                	</div>
		                </div>
		                
		                <%-- 销售人员 --%>
		                <div class="col-md-8">
		                
		                	<div class="form-group">
								<label for="name" class="col-md-3">销售人员<em> *</em></label>
			                    <div class="col-md-9">
			                    	<tags:selectbox name="userIds" map="${users}" value="${client.currency_id}" add0="true" />
			                    </div>
							</div>
							
							<div class="form-group">
								<label for="name" class="col-md-3">是否跨区<br/>（跨区的需要跨区特批）</label>
			                    <div class="col-md-9">
			                    	<input type="checkbox" name="cross_regional" id="cross_regional" class="cross_regional" value="1" />
			                    </div>
							</div>
		                
		                </div>
		                
		                
				 </form><!-- /form -->
      		</div><!-- /.box-body -->
      		
      		 <div class="box-footer">
		           <button type="submit" class="btn btn-primary btn-sm" onclick="$('#primaryForm').submit();"><spring:message code="btn.submit"/></button>
		           <button class="btn btn-primary btn-sm disabled" onclick="cancel();"><spring:message code="btn.cancel"/></button>
		     </div>
      </div><!-- /.box-info -->
</section>        
          
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
		
		bind_remove();
		
		
	});
	
	function add_client_contacts(){
		var contact_size = $(".client-contacts .contact_persons div").size();
		var index = contact_size;
		
		$(".client-contacts .div-0").removeClass("col-md-6");
		$(".client-contacts .div-0").addClass("col-md-3");
		
		$(".client-contacts .contact_persons").append('<div class="col-md-3 div-'+ index +'"><input id="brand" class="form-control" type="text" name="contacts['+index+'].contact_person" value="" /></div>');
		$(".client-contacts .positions").append('<div class="col-md-3 div-'+ index +'"><input id="brand" class="form-control" type="text" name="contacts['+index+'].position" value="" /></div>');
		$(".client-contacts .phones").append('<div class="col-md-3 div-'+ index +'"><input id="brand" class="form-control" type="text" name="contacts['+index+'].phone" value="" /></div>');
		$(".client-contacts .emails").append('<div class="col-md-3 div-'+ index +'"><input id="brand" class="form-control" type="text" name="contacts['+index+']email." value="" /></div>');
		$(".client-contacts .addresses").append('<div class="col-md-3 div-'+ index +'"><input id="brand" class="form-control" type="text" name="contacts['+index+'].address" value="" /></div>');
		
		$(".contact-list").append('<div class="col-md-3"><span class="glyphicon glyphicon-remove-circle" id="rm-'+ index +'">&nbsp;</span></div>')
		bind_remove();
	};
	
	function cancel(){
		window.location.href='${ctx}/opportunity';
	};
	
	function bind_remove(){
		$(".contact-list span").mouseover( function(){
			var index = $(this).attr('id').split('-')[1];
			$(".div-" +index + " input").css('border-color','red');
		});
		
		$(".contact-list span").mouseout( function(){
			var index = $(this).attr('id').split('-')[1];
			$(".div-" +index + " input").css('border-color','#d2d6de');
		});
	}
</script>
</body>
</html>
 



