<html>
<#include "../common/head.ftl">

<body>
    <!-- body wrapper start -->
    <div id="wrapper">
    

    <#--head-->
    <#include "../common/header.ftl">
	    
	<#--sidebar-->
    <#include "../common/nav.ftl">
    
    <#--main content -->    
    <!-- main content start  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <!--   Kitchen Sink -->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
												<th>Id</th>
					                            <th>Name</th>
					                            <th>Icon</th>
					                            <th>Price</th>
					                            <th>Stock</th>
					                            <th>Description</th>
					                            <th>Category</th>
					                            <th>Create time</th>
					                            <th>Update time</th>
					                            <th colspan="2">Operation</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        	<#assign idx=0 />
                                        	<#list productInfoPage.content as productInfo>
                                        	<#assign idx = idx + 1 />
                                            <tr>
                                                <th scope="row">${idx}</th>
												<td>${productInfo.productId}</td>
					                            <td>${productInfo.productName}</td>
					                            <td><img height="50" width="50" src="${productInfo.productIcon}" alt=""></td>
					                            <td>${productInfo.productPrice}</td>
					                            <td>${productInfo.productStock}</td>
					                            <td>${productInfo.productDesc}</td>
					                            <td>${productInfo.categoryType}</td>
					                            <td>${productInfo.createTime}</td>
					                            <td>${productInfo.updateTime}</td>
					                            <td><a href="/seller/product/index?productId=${productInfo.productId}">Update</a></td>
					                            <td>
					                                <#if productInfo.getProductStatus().getMsg() == "ONLINE">
					                                    <a href="/seller/product/offline_sale?productId=${productInfo.productId}">Offline</a>
					                                <#else>
					                                    <a href="/seller/product/online_sale?productId=${productInfo.productId}">online</a>
					                                </#if>
					                            </td>
                                            </tr>
                                            </#list>
                                        </tbody>
                                    </table>
                                </div>
                                
                                <#--paging-->
			               		<div class="col-md-12 column">
				                    <ul class="pagination pull-right">
					                    <#if currentPage lte 1>
					                        <li class="disabled"><a href="#">Prev</a></li>
					                    <#else>
					                        <li><a href="/seller/product/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
					                    </#if>
					
					                    <#list 1..productInfoPage.getTotalPages() as index>
					                        <#if currentPage == index>
					                            <li class="disabled"><a href="#">${index}</a></li>
					                        <#else>
					                            <li><a href="/seller/product/list?page=${index}&size=${size}">${index}</a></li>
					                        </#if>
					                    </#list>
					
					                    <#if currentPage gte productInfoPage.getTotalPages()>
					                        <li class="disabled"><a href="#">Next</a></li>
					                    <#else>
					                        <li><a href="/seller/product/list?page=${currentPage + 1}&size=${size}">Next</a></li>
					                    </#if>
				                    </ul>
								</div>
                                
                                
                            </div>
                        </div>
                        <!-- End  Kitchen Sink -->
                    </div>

                </div>
            </div>
        </div>
        <!-- main content end  -->
    </div>
    <!-- body wrapper end -->

	<#--footer -->
	<#include "../common/footer.ftl">
</body>
</html>