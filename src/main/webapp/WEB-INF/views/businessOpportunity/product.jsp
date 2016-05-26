<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row" id="row_product_${index}">
	<div class="col-md-6">
		<div class="form-group">
			<label class="col-md-3" for="product_id">产品${index}*</label>
			<div class="col-md-9">
				<select class="form-control select2" name="product_id_${index}" id="product_id_${index}" style="width: 100%;"></select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="sale_mode">销售模式${index}*</label>
			<div class="col-md-9">
				<tags:selectbox name="sale_mode_${index}" id="sale_mode_${index}" list="${saleModes}" addNull="true"></tags:selectbox>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="budget">产品预算${index}*</label>
			<div class="col-md-9">
				<div class="input-group">
				<input type="text" class="form-control text-right" name="product_budget_${index}" id="product_budget_${index}"  placeholder="<spring:message code='business.opportunity.input.product.budget' />">
				<div class="input-group-addon"><i class="fa fa-cny"></i></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="form-group">
			<label class="col-md-3"><a href="javascript:void(0);" onclick="$('#row_product_${index}').remove();" style="color: red;"><i class="fa fa-w fa-minus-square"></i>&nbsp;删除产品</a></label>
		</div>
	</div>
</div>

<script>    
	$(document).ready(function() {
		$("#row_product_${index}").find("select[name^='product_id']").rules('add', {required:true});
		$("#row_product_${index}").find("select[name^='sale_mode']").rules('add', {required:true});
		$("#row_product_${index}").find("input[name^='product_budget']").rules('add', {required:true,number:true});
		
		$("#product_id_${index}").select2({
			ajax: {
		        url: "${ctx}/ajax/getProducts",
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
		    placeholder: "<spring:message code='business.opportunity.input.product'/>",
		    allowClear: true
		});
	});
</script>