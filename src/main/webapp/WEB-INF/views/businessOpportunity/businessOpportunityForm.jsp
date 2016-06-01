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
    	<c:if test="${action eq 'create' }"><spring:message code="opportunity.title.create"/></c:if>
        <c:if test="${action eq 'update' }"><spring:message code="opportunity.title.update"/></c:if>
    </title>


</head>

<body>
 <style>
 	.box{}
 	.box-noborder{border-top: 0}
 	.btn-100{width: 100px;line-height: 1.1}
 	.products{padding: 0 15px 0 15px;}
 	.content{padding-bottom: 0;min-height:0}
 	.select2ErrorClass{border: 1px solid red;}
 </style>
 <!-- Content Header -->
       <section class="content-header">
          <h1>
           	<c:if test="${action eq 'create' }"><spring:message code="opportunity.title.create"/></c:if>
       		<c:if test="${action eq 'update' }"><spring:message code="opportunity.title.update"/></c:if>
          </h1>
          <ol class="breadcrumb">
            <li><a href="${ctx}/businessOpportunity"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li class="active">
            	<c:if test="${action eq 'create' }"><spring:message code="opportunity.title.create"/></c:if>
            	<c:if test="${action eq 'update' }"><spring:message code="opportunity.title.update"/></c:if>
            </li>
          </ol>
        </section>
          <!-- form start -->
          <form action="${ctx}/businessOpportunity/${action}" method="post" id="primaryForm" class="form-horizontal">
          <input type="hidden" name="id" id="id" value="${businessOpportunity.id}" />
        
           <!-- Main content -->
          <section class="content">
         
              <div class="box box-info">
              
                <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
	                    <div class="form-group">
	                      <label for="advertiser_id" class="col-md-3"><spring:message code="business.opportunity.advertiser" />*</label>
	                      <div class="col-md-9">
	                      	<select class="form-control select2" name="advertiser_id" id="advertiser_id" style="width: 100%;">
	                      		<c:if test="${advertiser !=null}">
	                      			<option value="${advertiser.id}" selected>${advertiser.name}</option>
	                      		</c:if>
	                      	</select>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="deliver_date" class="col-md-3"><spring:message code="business.opportunity.deliver.date" />*</label>
	                      <div class="col-md-9">
	                      	<div class="input-group">
	                      		<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
	                      		<input type="text" value="${businessOpportunity.decodeDeliver_date }" class="form-control pull-right" name="deliver_date" id="deliver_date" placeholder="<spring:message code='business.opportunity.input.deliverdate' />">
	                      	</div>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="currency_id" class="col-md-3"><spring:message code="business.opportunity.currency" />*</label>
	                      <div class="col-md-9">
	                      		<tags:selectbox name="currency_id" list="${currencys}" addNull="true" value="${businessOpportunity.currency_id }"></tags:selectbox>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="budget" class="col-md-3"><spring:message code="business.opportunity.budget" />*</label>
	                      <div class="col-md-9">
	                      	<div class="input-group">
	                      		<input type="text" value="${businessOpportunity.budget }" class="form-control text-right" name="budget" id="budget" placeholder="<spring:message code='business.opportunity.input.budget' />">
	                      		<div class="input-group-addon"><i class="fa fa-cny"></i></div>
	                      	</div>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="progress" class="col-md-3"><spring:message code="business.opportunity.progress" /></label>
	                      <div class="col-md-9">
				          	<input id="progress" class="form-control" data-slider-id="blue" type="text" name="progress" value="${businessOpportunity.progress}">
				          </div>
	                    </div>
	                    
                    </div>
                    
                    <div class="col-lg-6">
                    	<div class="form-group">
	                      <label for="progress" class="col-md-3"><a href="#"><spring:message code="business.opportunity.add.advertiser" /></a></label>
	                     </div>
                    </div>
                  </div>
                </div><!-- /.box-body -->
                
              </div>
           
          </section>
          
          <!-- part2 -->
          <section class="content">
              <div class="box box-noborder">
                <div class="box-body">
                   <div class="row">
           			<div class="col-md-6">
	                    <div class="form-group">
	                    	<label for="exist_msa" class="col-md-3"><spring:message code="business.opportunity.msa" />*</label>
	                     	<input type="hidden" name="exist_msa" id="exist_msa" value="${businessOpportunity.exist_msa}">
	                      	<div class="col-md-9">
	                      		<input type="button" class="btn <c:choose><c:when test="${businessOpportunity.exist_msa ==1 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose>  btn-flat pull-left btn-sm btn-100" onclick="changeRadio(1,'exist_msa',this);" id="btn_exist_msa_1" value="<spring:message code="business.opportunity.yes" />">
	                      		<input type="button" class="btn <c:choose><c:when test="${businessOpportunity.exist_msa ==0 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose>  btn-flat pull-left btn-sm btn-100" onclick="changeRadio(0,'exist_msa',this);" id="btn_exist_msa_0" value="<spring:message code="business.opportunity.no" />">
	                     	</div>
	                    </div>
	                    
	                    <div class="form-group">
	                    	<label for="exist_service" class="col-md-3"><spring:message code="business.opportunity.service" />*</label>
	                     	<input type="hidden" name="exist_service" id="exist_service" value="${businessOpportunity.exist_service}">
	                      	<div class="col-md-9">
	                      		<input type="button" class="btn <c:choose><c:when test="${businessOpportunity.exist_service ==1 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose> btn-flat pull-left btn-sm btn-100" onclick="changeRadio(1,'exist_service',this);" value="<spring:message code="business.opportunity.service" />">
	                      		<input type="button" class="btn <c:choose><c:when test="${businessOpportunity.exist_service ==0 }">btn-primary</c:when><c:otherwise>btn-default</c:otherwise></c:choose> btn-flat pull-left btn-sm btn-100" onclick="changeRadio(0,'exist_service',this);" value="<spring:message code="business.opportunity.exec" />">
	                     	</div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="owner_sale" class="col-md-3"><spring:message code="business.opportunity.sale" />*</label>
	                      <div class="col-md-9">
	                      	<select class="form-control select2" name="owner_sale" id="owner_sale" style="width: 100%;">
	                      		<c:if test="${ownerSale !=null}">
	                      			<option value="${ownerSale.id}" selected>${ownerSale.name}</option>
	                      		</c:if>
	                      	</select>
	                      </div>
	                    </div>
	                    
	                    <div class="form-group">
	                      <label for="cooperate_sales" class="col-md-3"><spring:message code="business.opportunity.cooperate" /></label>
	                      <div class="col-md-9">
	                      	<select class="form-control select2" name="cooperate_sales" id="cooperate_sales" style="width: 100%;" multiple="multiple">
	                      		<c:if test="${businessOpportunity.cooperate_sale_list !=null}">
	                      			<c:forEach items="${businessOpportunity.cooperate_sale_list}" var="sale">
	                      				<option value="${sale.id}" selected>${sale.name}</option>
	                      			</c:forEach>
	                      		</c:if>
	                      	</select>
	                      </div>
	                    </div>
	                    
                    </div>
                  </div>
                </div><!-- /.box-body -->
              </div>
          </section>
          
          <!-- part3 -->
          <section class="content">
              <div class="box box-noborder">
                <div class="box-body">
                	<div class="row">
           			<div class="col-md-12">
	                    <div class="form-group">
	                      <label  class="col-md-3"><spring:message code="business.opportunity.setting" />&nbsp;<i class="fa fa-w fa-caret-down"></i></label>
	                      <div class="divider-horizontal"></div>
	                    </div>
	                </div>
                    </div>
                    
                    <!--  -->
                    <div class="products" id="products">
                    	<jsp:include page="productEdit.jsp"></jsp:include>
                    </div>
                    
                   	<div class="row">
           			<div class="col-md-6">
           				<div class="form-group">
	                      <label  class="col-md-3"><a href="javascript:void(0);" onclick="addProduct();"><i class="fa fa-w fa-plus-square"></i>&nbsp;<spring:message code="business.opportunity.addproduct" /></a></label>
	                    </div>
           			
	                    <div class="form-group">
	                      <label for="remark" class="col-md-3"><spring:message code="business.opportunity.remark" /></label>
	                      <div class="col-md-9">
	                      	<textarea rows="3" class="form-control" name="remark" id="remark" placeholder="<spring:message code="business.opportunity.input.remark" />">${businessOpportunity.remark}</textarea>
	                      </div>
	                    </div>
	                  
                    </div>
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                	<div class="row">
	           			<div class="col-md-6" id="budgetSumError" style="display: none;">
		                	<div class="alert alert-error alert-dismissable" style="margin: 10px 0 0 0">
		                    <button type="button" class="close" onclick="$('#budgetSumError').hide();" aria-hidden="true">×</button>
			                   <spring:message code="business.opportunity.budget.sum.error" />
		            		</div>
	            		</div>
            		</div>
            		<br/>
                    <button type="button" class="btn btn-primary btn-sm btn-flat" onclick="submitForm();"><spring:message code="btn.submit"/></button> 
                    <button type="button" class="btn btn-primary btn-sm disabled btn-sm btn-flat" onclick="cancel();"><spring:message code="btn.cancel"/></button>
                </div>
              </div>
          </section>
          
          
         </form>
         
         <div id="shield" style="position: fixed; left: 0px; top: 0px; display: none; z-index: 9998; opacity: 0.8; background: #7D7159; width: 100%; height: 100%;">
		<img src="${ctx}/static/images/loading_s.gif" style="position: absolute; top: 300px; left: 48%;" /></div>
          
		<script>    
			$(document).ready(function() {
				
				$("#menu_business_opportunity").addClass("active");
				
				$("#deliver_date").daterangepicker({opens:"right",cancelClass:"btn-info",format:'YYYY-MM-DD'});
				
				$("#btn_exist_service_${businessOpportunity.exist_service}").trigger('click');
				$("#btn_exist_msa_service_${businessOpportunity.exist_msa}").trigger('click');
				
				// 广告主
				$("#advertiser_id").select2({
					ajax: {
				        url: "${ctx}/ajax/getAdvertisers",
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
				    placeholder: "<spring:message code='business.opportunity.input.advertiser' />",
				    allowClear: true
				}).on('change',function(evt){
					validateSelect2(this);
				});
				
				// 销售
				$("#owner_sale").select2({
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
				    placeholder: "<spring:message code='business.opportunity.input.sale' />",
				    allowClear: true
				}).on('change',function(evt){
					validateSelect2(this);
				});
				
				// 合作销售
				$("#cooperate_sales").select2({
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
				}).on('change',function(evt){
					validateSelect2(this);
				});
				
				$("#primaryForm").validate({
					ignore: "",
					rules:{
						advertiser_id:"required",
						deliver_date:"required",
						budget:{required:true,number:true},
						currency_id:"required",
						owner_sale:"required"
					},
					errorPlacement: function(error, element) {
						if (element.attr("class").indexOf("select2")!=-1) {
							  error.insertAfter( element.next() );
							  element.next().addClass("select2ErrorClass");
						} 
						else if (element.parent('.input-group').length || element.prop('type') === 'checkbox' || element.prop('type') === 'radio') {
				            error.insertAfter(element.parent());
				        } else {
				            error.insertAfter(element);
				        }
				    }
				});
				
				$("div[id^='row_product']").each(function(i){
					$(this).find("select[name='businessOpportunityProducts["+i+"].product_id']").rules('add', {required:true});
					$(this).find("select[name='businessOpportunityProducts["+i+"].sale_mode']").rules('add', {required:true});
					$(this).find("input[name='businessOpportunityProducts["+i+"].budget']").rules('add', {required:true,number:true});
				});

				$("#progress").ionRangeSlider({
					min:0,
					max:100,
					keyboard:true
					<c:if test="${action eq 'create' }">,values:[10]</c:if>
					<c:if test="${action eq 'update' }">,values:[0,10,30,50,70,90,100]</c:if>
				});
			});
			
			function cancel(){
				window.location.href='${ctx}/opportunity';
			};
			
			function changeRadio(val,id,name){
				$('#'+id).val(val);
				$(name).parent().children().each(function(){
					$(this).removeClass("btn-primary").addClass("btn-default");
				});
				$(name).removeClass("btn-default").addClass("btn-primary");
			};
			
			function addProduct(){
				var productIndex = $(".products").children(".row").length;
				$.post("${ctx}/ajax/addProduct",{index:productIndex},function(html){
					$(".products").append(html);
				},"html");
			};
			
			function submitForm(){
				var budget = +$("#budget").val();
				var subBudget = 0;
				$("input[name*='.budget']").each(function(i){
					subBudget += +$(this).val();
				});
				var errSum = 0;
				if(budget!=0 && subBudget!=budget){
					$("#budgetSumError").show();
					errSum++;
				}
				if(!$("#primaryForm").valid()){
					errSum++;
				}
				if(errSum==0){
					$("#budgetSumError").hide();
					$("#shield").show();
					$("#primaryForm").submit();
				}
			};
			
			function validateSelect2(name){
				if($("#primaryForm").validate().element( name )){
					$(name).next().removeClass('select2ErrorClass');
				}else{
					$(name).next().addClass('select2ErrorClass');
				}
			};
		</script>
</body>
</html>
 



