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
        广告主详情
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
            广告主详情
          </h1>
          <ol class="breadcrumb">
            <li><a href="${ctx}/client"><i class="fa fa-dashboard"></i> <spring:message code="opportunity.home" /></a></li>
            <li><a href="${ctx}/client">广告主</a></li>
            <li class="active">
            	广告主详情
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
								<label for="name" class="col-md-3">广告主名称<em> *</em></label>
			                    <div class="col-sm-9">${client.name}</div>
							</div>
						
	                		<div class="form-group">
	                			<label for="brand" class="col-md-3">品牌名称<em> *</em></label>
		                      	<div class="col-sm-9">${client.brand}</div>
	                		</div>
		                	
		                	 <div class="form-group">
		                	 	<label for="name" class="col-md-3">代理下单</label>
		                	 	<div class="col-sm-9">
									<tags:decodeList list="${agencys}" value="${client.channel}"></tags:decodeList>
								</div>
		                	</div>	
			                	
		                	<div class="form-group">
	                			<label for="industry_id" class="col-md-3">行业</label>
		                      	<div class="col-sm-9">
	                				<tags:decodeList list="${industryTypes}" value="${client.industry_id}"></tags:decodeList>
	                			</div>
	                		</div>
	                		
	                		<div class="form-group">
	                			<label for="currency_id" class="col-md-3">货币</label>
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
						                  	<label class="col-md-3">广告主联系人&nbsp;<i class="fa fa-w fa-caret-down"></i></label>
						                 	<div class="divider-horizontal"></div>
						                </div>
								    </div>
								</div>
								
								
								<div class="client_contacts col-md-6" id="client_contacts">
									<div class="row">
									<c:forEach var="contact" items="${client.contacts}" varStatus="status">
										<div class="form-group">
											<label for="" class="col-md-3">
												广告主联系人${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.contact_person}
			                      			</div>
										</div> 
										
										<div class="form-group">
											<label for="" class="col-md-3">
												联络人职位${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.position}
			                      			</div>
										</div>
										
										<div class="form-group">
											<label for="" class="col-md-3">
												联络人电话${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.phone}
			                      			</div>
										</div> 
										
										<div class="form-group">
											<label for="" class="col-md-3">
												联络人邮箱${status.index+1}
											</label>
			                      			<div class="div-0 col-md-9">
			                      				${contact.email}
			                      			</div>
										</div>     
										
										<div class="form-group addresses">
			                      			<label for="" class="col-md-3">
			                      				公司地址${status.index+1}
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
											<label for="name" class="col-md-3">销售人员<em> *</em></label>
						                    <div class="col-md-9">
						                    	<c:forEach var="userId" items="${client.userIds}" varStatus="status">
						                    		<tags:decodeList list="${users}" value="${userId}" />
						                    		<c:if test="${status.last!= true}">,</c:if>
						                    	</c:forEach>
						                    </div>
										</div>
										
										<div class="form-group">
											<label for="name" class="col-md-3">是否跨区<br/>（跨区的需要跨区特批）</label>
						                    <div class="col-md-9">
						                    	<c:if test="${client.cross_regional == 1}" >
						                    		<input type="checkbox" disabled="disabled" checked="checked"/>
						                    	</c:if>
						                    	<c:if test="${client.cross_regional != 1}" >
						                    		<input type="checkbox" disabled="disabled" />
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
</body>
</html>

<script type="text/javascript">    
	$(document).ready(function() {
		$("#menu_advertiser").addClass("active");
		
		$("#primaryForm").validate({
			rules:{
				task:"required"
			}
		});
		
	});
</script>



