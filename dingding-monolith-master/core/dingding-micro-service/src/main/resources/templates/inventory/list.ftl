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
					                            <th>Category</th>
					                            <th>Vendor</th>
					                            <th>Price</th>
					                            <th>Sku</th>
					                            <th colspan="2">Operation</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        	<#assign idx=0 />
                                        	<#list inventoryPage.content as inventory>
                                        	<#assign idx = idx + 1 />
                                            <tr>
                                                <th scope="row">${idx}</th>
												<td>${inventory.productId}</td>
					                            <td>${inventory.productCategory}</td>
					                            <td>${inventory.vendorId}</td>
					                            <td>${inventory.basePrice}</td>
					                            <td>${inventory.sku}</td>
					                            <td><a href="/seller/inventory/index?productId=${inventory.productId}">Update</a></td>
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
					                        <li><a href="/seller/inventory/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
					                    </#if>
					
					                    <#list 1..inventoryPage.getTotalPages() as index>
					                        <#if currentPage == index>
					                            <li class="disabled"><a href="#">${index}</a></li>
					                        <#else>
					                            <li><a href="/seller/inventory/list?page=${index}&size=${size}">${index}</a></li>
					                        </#if>
					                    </#list>
					
					                    <#if currentPage gte inventoryPage.getTotalPages()>
					                        <li class="disabled"><a href="#">Next</a></li>
					                    <#else>
					                        <li><a href="/seller/inventory/list?page=${currentPage + 1}&size=${size}">Next</a></li>
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