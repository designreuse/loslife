<%@page import="com.asgab.entity.BusinessOpportunityProduct"%>
<%@page import="java.util.List"%>
<%@page import="com.asgab.entity.BusinessOpportunity"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	BusinessOpportunity businessOpportunity = (BusinessOpportunity)request.getAttribute("businessOpportunity");
	List<BusinessOpportunityProduct> products = businessOpportunity.getBusinessOpportunityProducts();
	for(int index = 0 ; index <  products.size();index++){
	  request.setAttribute("index", index);
	  request.setAttribute("product", products.get(index));
	  %>
	 

<div class="row" id="row_product_${index}">
	<div class="col-md-6">
		<input type="hidden" name="businessOpportunityProducts[${index}].id" value="${product.id}">
		<div class="form-group">
			<label class="col-md-3" for="product_id"><spring:message code="business.opportunity.product"/>${index+1}*</label>
			<div class="col-md-9">
				<select class="form-control select2" name="businessOpportunityProducts[${index}].product_id" style="width: 100%;">
					<option value="${product.product.id}" selected>${product.product.name}</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="sale_mode"><spring:message code="business.opportunity.sale.mode"/>${index+1}*</label>
			<div class="col-md-9">
				<tags:selectbox name="businessOpportunityProducts[${index}].sale_mode" value="${product.sale_mode}" list="${saleModes}" addNull="true"></tags:selectbox>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="budget"><spring:message code="business.opportunity.product.budget"/>${index+1}*</label>
			<div class="col-md-9">
				<div class="input-group">
				<input type="text" class="form-control text-right" name="businessOpportunityProducts[${index}].budget" value="${product.budget}" placeholder="<spring:message code='business.opportunity.input.product.budget' />">
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
		$("select[name='businessOpportunityProducts[${index}].product_id']").select2({
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
 <%
	}
%>