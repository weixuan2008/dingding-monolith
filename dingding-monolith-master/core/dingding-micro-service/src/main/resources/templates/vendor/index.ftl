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
                            
                                <form role="form" method="post" action="/seller/vendor/save">
                                    <div class="form-group">
                                        <label>Vendor Name</label>
                                        <input name="vendorName" type="text" class="form-control" value="${(vendor.vendorName)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Vendor Address</label>
                            			<input name="vendorAddress" type="text" class="form-control" value="${(vendor.vendorAddress)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Phone Number</label>
                            			<input name="phoneNumber" type="text" class="form-control" value="${(vendor.phoneNumber)!''}"/>
                                    </div>

									<div class="form-group">
                                        <label>Country</label>
                            			<input name="vendorCountry" type="text" class="form-control" value="${(vendor.vendorCountry)!''}"/>
                                    </div>
                                    
      								<div class="form-group">
                                        <label>State</label>
                            			<input name="vendorState" type="text" class="form-control" value="${(vendor.vendorState)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>City</label>
                            			<input name="vendorCity" type="text" class="form-control" value="${(vendor.vendorCity)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Zipcode</label>
                            			<input name="vendorZipcode" type="text" class="form-control" value="${(vendor.vendorZipcode)!''}"/>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Level</label>
                            			<input name="vendorLevel" type="number" class="form-control" value="${(vendor.vendorLevel)!''}"/>
                                    </div>                         
                                                      
                                                                                                                                                              
									<input hidden type="text" name="vendorId" value="${(vendor.vendorId)!''}">
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