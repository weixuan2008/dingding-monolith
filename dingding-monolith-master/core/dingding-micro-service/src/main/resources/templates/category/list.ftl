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
					                            <th>Name</th>
					                            <th>Type</th>
					                            <th>Create Time</th>
					                            <th>Update Time</th>
					                            <th>Operation</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        	<#list productCategoryPage.content as productCategory>
                                            <tr>
												<td>${productCategory.categoryId}</td>
					                            <td>${productCategory.categoryName}</td>
					                            <td>${productCategory.categoryType}</td>
					                            <td>${productCategory.createTime}</td>
					                            <td>${productCategory.updateTime}</td>
					                            <td><a href="/seller/category/index?categoryId=${productCategory.categoryId}">Update</a></td>
                                            </tr>
                                            </#list>
                                        </tbody>
                                    </table>
                                </div>
                                	                  
                            </div>
                            
                            <#--paging-->
			               		<div class="col-md-12 column">
				                    <ul class="pagination pull-right">
					                    <#if currentPage lte 1>
					                        <li class="disabled"><a href="#">Prev</a></li>
					                    <#else>
					                        <li><a href="/seller/category/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
					                    </#if>
					
					                    <#list 1..productCategoryPage.getTotalPages() as index>
					                        <#if currentPage == index>
					                            <li class="disabled"><a href="#">${index}</a></li>
					                        <#else>
					                            <li><a href="/seller/category/list?page=${index}&size=${size}">${index}</a></li>
					                        </#if>
					                    </#list>
					
					                    <#if currentPage gte productCategoryPage.getTotalPages()>
					                        <li class="disabled"><a href="#">Next</a></li>
					                    <#else>
					                        <li><a href="/seller/category/list?page=${currentPage + 1}&size=${size}">Next</a></li>
					                    </#if>
				                    </ul>
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