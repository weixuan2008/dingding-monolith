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
                            
                                <form role="form" method="post" action="/seller/inventory/save">
                                    <div class="form-group">
                                        <label>Product Category</label>
			                            <select name="productCategory" class="form-control">
			                                <#list categoryList as category>
			                                    <option value="${category.categoryType}"
			                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
			                                                selected
			                                            </#if>
			                                        >${category.categoryName}
			                                    </option>
			                                </#list>
			                            </select>
                         				<p class="help-block">product gategory.</p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Vendor Id</label>
                            			<input name="vendorId" type="text" class="form-control" value="${(inventory.vendorId)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Base Price</label>
                            			<input name="basePrice" type="number" class="form-control" value="${(inventory.basePrice)!''}"/>
                                    </div>

									<div class="form-group">
                                        <label>Sku</label>
                            			<input name="sku" type="number" class="form-control" value="${(inventory.sku)!''}"/>
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