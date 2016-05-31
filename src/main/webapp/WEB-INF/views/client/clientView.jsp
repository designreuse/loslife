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
        
           <!-- Main content -->
          <section class="content">
         
              <div class="box box-info">
              
                <!-- form start -->
                  <div class="box-body">
                   <form role="form" class="form-horizontal">
                   	 <div class="col-md-8">
	                 		<div class="form-group">
								<label for="name" class="col-md-3">广告主名称<em> *</em></label>
								<div class="col-sm-9">${client.name}</div>
							</div>
							
							<div class="form-group">
								<label for="brand" class="col-md-3">品牌名称<em> *</em></label>
								<div class="col-sm-9">${client.brand}</div>
							</div>
							
							<div class="form-group">
								<label for="whether_channel" class="col-md-3">代理下单</label>
								<div class="col-sm-9">
									<tags:decode items="${agencys}" value="${client.channel}"></tags:decode>
								</div>
							</div>
							
							<div class="form-group">
	                			<label for="industry_id" class="col-md-3">行业</label>
	                			<div class="col-sm-9">
	                				<tags:decode items="${industryTypes}" value="${client.industry_id}"></tags:decode>
	                			</div>
	                		</div>
	                		
	                		<div class="form-group">
	                			<label for="currency_id" class="col-md-3">货币</label>
	                			<div class="col-sm-9">
	                				<tags:decode items="${currencyTypes}" value="${client.currency_id}"></tags:decode>
	                			</div>
	                		</div>
                   	 </div>
                   	 
                   	 <%-- 广告组联系人 --%>
                   	 
                   	 <div class="col-md-8">
                   		<c:forEach var="contact" items="${client.contacts}" varStatus="status">
                   		
							<div class="form-group">
								<label for="" class="col-md-3">
									<c:if test="${status.first == true}">
										广告主联系人
									</c:if>
									<c:if test="${status.first == false}">
										广告主联系人${status.index}
									</c:if>
								</label>
                      			<div class="div-0 col-md-9">
                      				${contact.contact_person}
                      			</div>
							</div> 
							
							<div class="form-group">
								<label for="" class="col-md-3">
									<c:if test="${status.first == true}">
										联络人职位
									</c:if>
									<c:if test="${status.first == false}">
										联络人职位${status.index}
									</c:if>
								</label>
                      			<div class="div-0 col-md-9">
                      				${contact.position}
                      			</div>
							</div>
							
							<div class="form-group">
								<label for="" class="col-md-3">
									<c:if test="${status.first == true}">
										联络人电话
									</c:if>
									<c:if test="${status.first == false}">
										联络人电话${status.index}
									</c:if>
								</label>
                      			<div class="div-0 col-md-9">
                      				${contact.phone}
                      			</div>
							</div> 
							
							<div class="form-group">
								<label for="" class="col-md-3">
									<c:if test="${status.first == true}">
										联络人邮箱
									</c:if>
									<c:if test="${status.first == false}">
										联络人邮箱${status.index}
									</c:if>
								</label>
                      			<div class="div-0 col-md-9">
                      				${contact.email}
                      			</div>
							</div>     
							
							<div class="form-group addresses">
                      			<label for="" class="col-md-3">
                      				<c:if test="${status.first == true}">
										公司地址
									</c:if>
									<c:if test="${status.first == false}">
										公司地址${status.index}
									</c:if>
                      			</label>
                      			<div class="div-0 col-md-9">
                      				${contact.address}
                      			</div>
                      		</div>

                   		</c:forEach>
                   	 
                   	 </div>
                   	 
                     <%-- 销售人员 --%>
		             <div class="col-md-8">
		             
		                	<div class="form-group">
								<label for="name" class="col-md-3">销售人员<em> *</em></label>
			                    <div class="col-md-9">
			                    	<c:forEach var="userId" items="${client.userIds}" varStatus="status">
			                    		<tags:decode items="${users}" value="${userId}" />
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
                   	</form> 
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button  class="btn btn-primary btn-sm" onclick="update(${client.id});"><spring:message code="btn.edit"/></button>
                    <button  class="btn btn-primary btn-sm disabled" onclick="list();"><spring:message code="btn.back"/></button>
                  </div>
              </div>
           
          </section>
          
		<script>    
			$(document).ready(function() {
				$("#menu_advertiser").addClass("active");
				
				$("#primaryForm").validate({
					rules:{
						task:"required"
					}
				});

				$("#progress").ionRangeSlider({
					min:0,
					max:100,
					keyboard:true,
					keyboard_step:10
				});
			});
			
			function list(){
				window.location.href="${ctx}/client";
			};
			function update(id){
				window.location.href="${ctx}/client/update/"+id;
			};
		</script>
</body>
</html>
 



