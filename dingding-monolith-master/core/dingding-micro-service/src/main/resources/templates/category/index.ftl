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
                            
                                <form role="form" method="post" action="/seller/category/save">
                                    <div class="form-group">
                                        <label>Category Name</label>
                            			<input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>type</label>
                            			<input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                                    </div>
                                    
                                    <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
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