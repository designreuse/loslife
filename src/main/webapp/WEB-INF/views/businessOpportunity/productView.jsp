<%@page import="com.asgab.entity.BusinessOpportunityProduct"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
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
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver (request);
	String lang =localeResolver.resolveLocale(request).getLanguage();
	request.setAttribute("products", products);
%>

<c:forEach items="${products}" var="product" varStatus="status">

<div class="row" id="row_product_${status.index}">
	<div class="col-md-6">
		<div class="form-group">
			<label class="col-md-3" for="product_id"><spring:message code='business.opportunity.product.type'/>${status.index+1}*</label>
			<div class="col-md-9">
				<label>${product.productCategory.value}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="product_id"><spring:message code="business.opportunity.product"/>${status.index+1}*</label>
			<div class="col-md-9">
				<label>${product.decodeProductId}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="sale_mode"><spring:message code="business.opportunity.sale.mode"/>${status.index+1}*</label>
			<div class="col-md-9">
				<label>${product.sale_mode}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3" for="budget"><spring:message code="business.opportunity.product.budget"/>${status.index+1}*</label>
			<div class="col-md-9">
				<label>${product.budget}</label>
			</div>
		</div>
	</div>
</div>
</c:forEach>
