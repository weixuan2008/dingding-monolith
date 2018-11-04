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
					                            <th>Address</th>
					                            <th>Phone</th>
					                            <th>Country</th>
					                            <th>State</th>
					                            <th>City</th>
					                            <th>Zipcode</th>
					                            <th>Level</th>					                            
					                            <th>Create time</th>
					                            <th>Update time</th>
					                            <th colspan="2">Operation</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        	<#assign idx=0 />
                                        	<#list vendorPage.content as vendor>
                                        	<#assign idx = idx + 1 />
                                            <tr>
                                                <th scope="row">${idx}</th>
												<td>${vendor.vendorId}</td>
					                            <td>${vendor.vendorName}</td>
					                            <td>${vendor.vendorAddress}</td>
					                            <td>${vendor.phoneNumber}</td>
					                            <td>${vendor.vendorCountry}</td>
					                            <td>${vendor.vendorState}</td>					                            
					                            <td>${vendor.vendorCity}</td>
					                            <td>${vendor.vendorZipcode}</td>
					                            <td>${vendor.vendorLevel}</td>					                            
					                            <td>${vendor.createTime}</td>
					                            <td>${vendor.updateTime}</td>
					                            <td><a href="/seller/vendor/index?vendorId=${vendor.vendorId}">Update</a></td>
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
					                        <li><a href="/seller/vendor/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
					                    </#if>
					
					                    <#list 1..vendorPage.getTotalPages() as index>
					                        <#if currentPage == index>
					                            <li class="disabled"><a href="#">${index}</a></li>
					                        <#else>
					                            <li><a href="/seller/vendor/list?page=${index}&size=${size}">${index}</a></li>
					                        </#if>
					                    </#list>
					
					                    <#if currentPage gte vendorPage.getTotalPages()>
					                        <li class="disabled"><a href="#">Next</a></li>
					                    <#else>
					                        <li><a href="/seller/vendor/list?page=${currentPage + 1}&size=${size}">Next</a></li>
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