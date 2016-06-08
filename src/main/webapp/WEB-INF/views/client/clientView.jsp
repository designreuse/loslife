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
        <spring:message code="client.title.view" />
    </title>


</head>

<body>

<style>
	.box{}
	.box-noborder{border-top: 0}
	.btn-100{width: 100px;line-height: 1.1}
	.products{padding: 0 15px 0 15px;}
	.content{padding-bottom: 0;min-height:0}
</style>

	<!-- Content Header -->
      	 <section class="content-header">
          <h1>
            <spring:message code="client.title.view" />
          </h1>
          <ol class="breadcrumb">
            <li><a href="${ctx}/client"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li><a href="${ctx}/client"><spring:message code="menu.advertiser" /></a></li>
            <li class="active">
            	<spring:message code="navigat.view" />
            </li>
          </ol>
        </section>

<div class="form-horizontal">
	
	<!-- Main content -->
		<section class="content">
     		 <div class="box box-info">
      			 <div class="box-body">
      			 	<div class="row">
					 	<div class="col-md-6">
							<div class="form-group">
								<label for="name" class="col-md-3"><spring:message code="client.name" /></label>
			                    <div class="col-sm-9">${client.clientname}</div>
							</div>
						
	                		<div class="form-group">
	                			<label for="brand" class="col-md-3"><spring:message code="client.brand.name" /></label>
		                      	<div class="col-sm-9">${client.brand}</div>
	                		</div>
		                	
		                	 <div class="form-group">
		                	 	<label for="name" class="col-md-3"><spring:message code="client.channel" /></label>
		                	 	<div class="col-sm-9">
									${client.channel_name}
								</div>
		                	</div>	
			                	
		                	<div class="form-group">
	                			<label for="industry_id" class="col-md-3"><spring:message code="client.industry" /></label>
		                      	<div class="col-sm-9">
	                				<tags:decodeList list="${industryTypes}" value="${client.industry_id}"></tags:decodeList>
	                			</div>
	                		</div>
	                		
	                		<div class="form-group">
	                			<label for="currency_id" class="col-md-3"><spring:message code="client.currency" /></label>
		                      	<div class="col-sm-9">
	                				<tags:decodeList list="${currencyTypes}" value="${client.currency_id}"></tags:decodeList>
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
								
								
								<div class="client_contacts col-md-6" id="client_contacts">
									<div class="row">
									<c:forEach var="contact" items="${client.contacts}" varStatus="status">
										<div class="form-group">
											<label for="" class="col-md-3">
												<spring:message code="client.contact.person" /> ${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.contact_person}
			                      			</div>
										</div> 
										
										<div class="form-group">
											<label for="" class="col-md-3">
												<spring:message code="client.contact.position" /> ${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.position}
			                      			</div>
										</div>
										
										<div class="form-group">
											<label for="" class="col-md-3">
												<spring:message code="client.contact.phone" /> ${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.phone}
			                      			</div>
										</div> 
										
										<div class="form-group">
											<label for="" class="col-md-3">
												<spring:message code="client.contact.email" /> ${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.email}
			                      			</div>
										</div>     
										
										<div class="form-group addresses">
			                      			<label for="" class="col-md-3">
			                      				<spring:message code="client.contact.address" /> ${status.index+1}
			                      			</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.address}
			                      			</div>
			                      		</div>
			                   		</c:forEach>
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
						                    	<c:if test="${client.saleNames != null}">
					                      				<c:forEach var="saleName" items="${client.saleNames}" varStatus="status">
					                      					${saleName}<c:if test="${status.last!= true}">,</c:if>
					                      				</c:forEach>
					                      		</c:if>
						                    </div>
										</div>
										
										<div class="form-group">
											<label for="name" class="col-md-3"><spring:message code="client.cross.regional" /><br/><spring:message code="client.cross.regional.remark" /></label>
						                    <div class="col-md-9">
						                    	<c:if test="${client.whether_cross_district == 1}" >
						                    		<spring:message code="client.channel.yes" />
						                    	</c:if>
						                    	<c:if test="${client.whether_cross_district != 1}" >
						                    		<spring:message code="client.channel.no" />
						                    	</c:if>	
						                    </div>
										</div>
										
					                </div>
		 					</div><!-- /.row -->
		 				</div><!-- /.box-body -->
		 				
		      		 	<div class="box-footer">
		                    <button type="button" class="btn btn-primary btn-sm btn-70" onclick="window.location.href='${ctx}/client/update/${client.id}'"><spring:message code="btn.edit"/></button>
		                    <button class="btn btn-primary btn-sm disabled btn-70" onclick="window.location.href='${ctx}/client'"><spring:message code="btn.back"/></button>
		                </div>
		 		</div><!-- /.box-noborder -->
		 </section><!-- /part3 -->
	</div>

	<script type="text/javascript">
		$(function() {
			$("#menu_client").addClass("active");
		});
	</script>
</body>
</html>



