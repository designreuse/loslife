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
    	<c:if test="${action eq 'create' }"><spring:message code="client.title.create" /></c:if>
        <c:if test="${action eq 'update' }"><spring:message code="client.title.edit" /></c:if>
    </title>

</head>



<body>

<style>
	.box{}
	.box-noborder{border-top: 0}
	.btn-100{width: 100px;line-height: 1.1}
	.client_contacts{padding: 0 15px 0 15px;}
	.content{padding-bottom: 0;min-height:0}
	.select2ErrorClass{border: 1px solid #dd4b39;box-shadow: none}
</style>

<!-- Content Header -->
<section class="content-header">
	<h1>
       	<c:if test="${action eq 'create' }"><spring:message code="client.title.create" /></c:if>
   		<c:if test="${action eq 'update' }"><spring:message code="client.title.edit" /></c:if>
    </h1>	
    <ol class="breadcrumb">
    	<li><a href="${ctx}/opportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
        <li><a href="${ctx}/client"><spring:message code="menu.advertiser" /></a></li>
        <li class="active">
        	<c:if test="${action eq 'create' }"><spring:message code="navigat.new" /></c:if>
   			<c:if test="${action eq 'update' }"><spring:message code="navigat.edit" /></c:if>
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
								<label for="clientname" class="col-md-3"><spring:message code="client.name" /><em> *</em></label>
			                    <div class="col-md-9">
			                      	<input id="clientname" class="form-control" type="text" name="clientname" value="${client.clientname}" 
			                      	placeholder="<spring:message code="client.name.remark" />"/>
			                    </div>
							</div>
						
	                		<div class="form-group">
	                			<label for="client_brand" class="col-md-3"><spring:message code="client.brand.name" /><em> *</em></label>
		                      	<div class="col-md-9">
		                      		<input id="client_brand" class="form-control" type="text" name="client_brand" value="${client.client_brand}"
		                      		placeholder="<spring:message code="client.brand.remark" />"/>
		                      	</div>
	                		</div>
		                	
		                	 <div class="form-group">
		                	 	<label for="channel" class="col-md-3"><spring:message code="client.channel" /><em> *</em></label>
		                	 	<div class="col-md-9 ">
		                	 		<div class="input-group channel-parent-append-error">
		                	 			<label class="input-group-addon">
		                	 				<c:if test="${client.whether_channel == 1}">
		                	 					<input type="radio" name="whether_channel" checked="checked" class="whether_channel_yes" value="1" />
		                	 				</c:if>
		                	 				<c:if test="${client.whether_channel != 1}">
		                	 					<input type="radio" name="whether_channel" class="whether_channel_yes" value="1" />
		                	 				</c:if>
		                	 				&nbsp;<spring:message code="client.channel.yes" />
		                	 			</label>
		                	 			<div class="channel-parent-has-error">
			                	 			<select class="form-control select2 channel" name="channel" disabled="disabled" id="channel" style="width: 100%; display: none;">
					                      		<c:if test="${client.channel_name != null}">
					                      			<option value="${client.channel}" selected>${client.channel_name}</option>
					                      		</c:if>
					                      	</select>
				                      	</div>
		                	 			
		                	 			<label class="input-group-addon">
		                	 				<c:if test="${client.whether_channel == 1}">
		                	 					<input type="radio" name="whether_channel" class="whether_channel_no" value="0" />
		                	 				</c:if>
		                	 				<c:if test="${client.whether_channel != 1}">
		                	 					<input type="radio" name="whether_channel" checked="checked" class="whether_channel_no" value="0" />
		                	 				</c:if>
		                	 				&nbsp;<spring:message code="client.channel.no" />
		                	 			</label>
		                	 		</div>
		                	 	</div>
		                	</div>	
			                	
		                	<div class="form-group">
	                			<label for="industry_id" class="col-md-3"><spring:message code="client.industry" /><em> *</em></label>
		                      	<div class="col-md-9">
		                      		<tags:selectbox name="industry_id" list="${industryTypes}" value="${client.industry_id}" addNull="true" />
		                      	</div>
	                		</div>
	                		
	                		<div class="form-group">
	                			<label for="currency_id" class="col-md-3"><spring:message code="client.currency" /><em> *</em></label>
		                      	<div class="col-md-9">
		                      		<tags:selectbox name="currency_id" list="${currencyTypes}" value="${client.currency_id }" addNull="true"  />
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
						                  	<label class="col-md-3"><spring:message code="client.contact.setting" />&nbsp;<i class="fa fa-w fa-caret-down"></i></label>
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
					                      		<i class="fa fa-w fa-plus-square"></i>&nbsp;<spring:message code="client.contact.add" /></a>
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
											<label for="name" class="col-md-3"><spring:message code="client.contact.salesperson" /></label>
						                    <div class="col-md-9">
						                    	<select class="form-control select2 userIds" name="saleIds" id="saleIds" style="width: 100%;" multiple="multiple">
						                    		<c:if test="${client.saleIds != null}">
						                    			<c:set value="${ fn:split(client.saleNames, ',') }" var="str1" />
					                      				<c:forEach var="saleId" items="${client.saleIds}" varStatus="status">
					                      						<option value="${saleId}" selected>${str1[status.index]}</option>
					                      				</c:forEach>
					                      			</c:if>
						                      	</select>
						                    </div>
										</div>
										
										<div class="form-group">
											<label for="name" class="col-md-3"><spring:message code="client.cross.regional" /><br/><spring:message code="client.cross.regional.remark" /></label>
						                    <div class="col-md-9">
						                    		<input type="hidden" name="whether_cross_district" id="whether_cross_district" value="${client.whether_cross_district}">
						                    		<input type="button" class="btn <c:choose><c:when test="${client.whether_cross_district == 1 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose>  btn-flat pull-left btn-sm btn-100" onclick="changeRadio(1,'whether_cross_district',this);" id="btn_whether_cross_district_1" 
						                    		value="<spring:message code="client.channel.yes" />">
	                      							<input type="button" class="btn <c:choose><c:when test="${client.whether_cross_district != 1 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose>  btn-flat pull-left btn-sm btn-100" onclick="changeRadio(0,'whether_cross_district',this);" id="btn_whether_cross_district_0" 
	                      							value="<spring:message code="client.channel.no" />">
						                    </div>
										</div>
					                
					                </div>
		 						
		 						</div><!-- /.row -->
		 				</div><!-- /.box-body -->
		 				
		      		 	<div class="box-footer">
				          	 <button type="submit" class="btn btn-primary btn-sm" onclick="$('#primaryForm').submit();"><spring:message code="btn.submit"/></button>
				          	 <button type="button" class="btn btn-primary btn-sm disabled" onclick="window.location.href='${ctx}/client'"><spring:message code="btn.cancel"/></button>
				    	</div>
		 		</div><!-- /.box-noborder -->
		 </section><!-- /part3 -->
		 

</form><!-- /form -->

          
<script>    
	$(document).ready(function() {
		$("#menu_client").addClass("active");  

		if('${client.whether_channel}' == 1){
			$('#channel').attr('disabled',false);
		}
		
		
		$("#primaryForm").validate({
			ignore: "",
			rules:{
				name: "required",
				brand: "required",
				industry_id: "required",
				currency_id: "required",
				channel: "validate_channel"
			},
			errorPlacement: function(error, element) {
				if( element.hasClass("select2") && element.hasClass("channel")){
					error.insertAfter($('.channel-parent-append-error'));
				}else{
					error.insertAfter(element);
				} 
			} 
		});
		
		// 代理下单，代理
		$("#channel").select2({
			ajax: {
		        url: "${ctx}/ajax/getAgencys",
		        dataType: 'json',
		        delay: 250,
		        data: function (params) {
		            return {q: params.term};
		        },
		        processResults: function (data) {
		            return {results: data};
		        },
		        cache: true
		    },
		    minimumInputLength: 1,
		    placeholder: '<spring:message code="client.channel.remark" />',
		    allowClear: true
		}).on('change',function(evt){
			$("#primaryForm").validate().element(this);
		});
		
		$("#saleIds").select2({
			multiple: true,
			ajax: {
		        url: "${ctx}/ajax/getSales",
		        dataType: 'json',
		        delay: 250,
		        data: function (params) {
		            return {q: params.term};
		        },
		        processResults: function (data) {
		            return {results: data};
		        },
		        cache: true
		    },
		    minimumInputLength: 1,
		    placeholder: "<spring:message code='business.opportunity.input.coopsale' />"
		});
		
		$('#whether_cross_district_cbk').click( function(){
			if($(this).is(':checked')){
				$('#whether_cross_district').val(1);
			}else{
				$('#whether_cross_district').val(0);
			}
		});
		
		// 单选框触发验证
		$('.whether_channel_yes').click( function(){
			$("#channel").attr("disabled",false);
			$("#primaryForm").validate().element($('#channel'));
		});
		
		$('.whether_channel_no').click( function(){
			$("#channel").attr("disabled",true);
			$("#channel").select2("val", "");
			$("#primaryForm").validate().element($('#channel'));
		});
		
		
		// 验证代理下单
		jQuery.validator.addMethod("validate_channel", function(value, element){
			$(".channel-parent-has-error").removeClass("select2ErrorClass");
			if( $('.whether_channel_yes').is(":checked") ){
				if( value == null || value == '' ){
					$(".channel-parent-has-error").addClass("select2ErrorClass");
					return false;
				}
			}
			return true;
		}, "<spring:message code='client.channel.remark' />");
		
		// 手机号码验证
		jQuery.validator.addMethod("isMobileOrPhone", function(value, element) {
		    var length = value.length;
		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
		    var phone = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
		    return this.optional(element) || ((length == 11 && mobile.test(value) || (length != 11 && phone.test(value)) ) );
		}, "<spring:message code='client.contact.phone.error' />");
		
		<c:if test="${action eq 'create' }">add_client_contacts();</c:if>
		<c:if test="${action eq 'update' }">bind_hover();bind_remove();</c:if>
	});
	
	function changeRadio(val,id,name){
		$('#'+id).val(val);
		$(name).parent().children().each(function(){
			$(this).removeClass("btn-primary").addClass("btn-default");
		});
		$(name).removeClass("btn-default").addClass("btn-primary");
	};
	
	// 再添加一个联系人
	function add_client_contacts(){
		var index = $("#client_contacts div.contacts").size();
		$.post("${ctx}/ajax/addContact",{index:index},function(html){
			$("#client_contacts").append(html);
			bind_hover();
			bind_remove();
			bind_client_contact_validate();
		},"html");
	};
	
	// 联系人验证
	function bind_client_contact_validate(){
		$("div[id^='client_contact_']").each(function(i){
			$(this).find("input[name='contacts["+i+"].contact_person']").rules('add',{required:true});
			$(this).find("input[name='contacts["+i+"].position']").rules('add',{required:true});
			$(this).find("input[name='contacts["+i+"].phone']").rules('add',{required:true,isMobileOrPhone:true});
			$(this).find("input[name='contacts["+i+"].email']").rules('add',{required:true,email:true});
			$(this).find("input[name='contacts["+i+"].address']").rules('add',{required:true});
		});
	};
	
	// 删除联系人
	function bind_remove(){
		$("label[id^='rm-contact-']").click( function(){
				var index = $(this).attr('id').split('-')[2];
				var rm_id = $('#client_contact_' + index + ' .contact_id').val();
				if(rm_id != null && rm_id != undefined){
					$("#primaryForm").append('<input type="hidden" name="deleteContactIds" value="' + rm_id + '" >');
				}
				$('#client_contact_' + index).remove();
				bind_client_contact_validate();
		});
	};
	
	// 删除联系人提示
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