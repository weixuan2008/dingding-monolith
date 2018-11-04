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
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-info">
                            <div class="panel-body">
                            
                                <form role="form" method="post" action="/seller/product/save">
                                    <div class="form-group">
                                        <label>Product Name</label>
                                        <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Product Price</label>
                            			<input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Stock Amount</label>
                            			<input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                                    </div>

									<div class="form-group">
                                        <label>Description</label>
                            			<input name="productDesc" type="text" class="form-control" value="${(productInfo.productDesc)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                         <label>Product Icon</label>  
                                        <#if productInfo??> 
			                            	<img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
			                            </#if>
			                            
			                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>
                                    </div>
                                    
                                    
                                    <div class="form-group">
                                        <label>Product Category</label>
			                            <select name="categoryType" class="form-control">
			                                <#list categoryList as category>
			                                    <option value="${category.categoryType}"
			                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
			                                                selected
			                                            </#if>
			                                        >${category.categoryName}
			                                    </option>
			                                </#list>
			                            </select>
                                    </div>
                                   
                                                                        
									<input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                                    <button type="submit" class="btn btn-info">Submit</button>
                                </form>
                                
                            </div>
                        </div>
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