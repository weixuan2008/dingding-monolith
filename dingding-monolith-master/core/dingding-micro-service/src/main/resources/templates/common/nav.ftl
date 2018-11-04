<!-- main side start  -->
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <img src="/images/user.png" class="img-thumbnail" />

                    <div class="inner-text">
                        Name: Eddie Wei
                        <br />
                        Role: Admin
                        <br />                        
                        <small>Last Login: 1 mins ago </small>
                    </div>
                </div>

            </li>	
			
			
			<!-- order section-->		
            <li>
            	<a <#if springMacroRequestContext.requestUri?contains('/seller/order')>class='active-menu'</#if> href="/seller/order/list">
            	<i class="fa fa-fw fa-list-alt"></i>Order</a>
            </li>
			
			<!-- product section-->
            <li>
                <a href="#" <#if springMacroRequestContext.requestUri?contains('/seller/product')>class='active-menu-top'</#if>>
                <i class="fa fa-certificate "></i>Product<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level <#if springMacroRequestContext.requestUri?contains('/seller/product')>collapse in</#if>">
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/product/list')>class='active-menu'</#if> 
                        href="/seller/product/list"><i class="fa fa-list "></i>List</a>
                    </li>
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/product/index')>class='active-menu'</#if> 
                        href="/seller/product/index"><i class="fa fa-flask "></i>Add</a>
                    </li>
                </ul>
            </li>
            
            <!-- product category section-->
            <li>
                <a <#if springMacroRequestContext.requestUri?contains('/seller/category')>class='active-menu-top'</#if>
                href="#"><i class="fa fa-sitemap "></i>Category<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level <#if springMacroRequestContext.requestUri?contains('/seller/category')>collapse in</#if>">
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/category/list')>class='active-menu'</#if>
                        href="/seller/category/list"><i class="fa fa-list "></i>List</a>
                    </li>
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/category/index')>class='active-menu'</#if>
                        href="/seller/category/index"><i class="fa fa-flask "></i>Add</a>
                    </li>
                </ul>
            </li>
            
            <!-- product inventory section-->
            <li>
                <a <#if springMacroRequestContext.requestUri?contains('/seller/inventory')>class='active-menu-top'</#if>
                href="#"><i class="fa fa-database "></i>Inventory<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level <#if springMacroRequestContext.requestUri?contains('/seller/inventory')>collapse in</#if>">
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/inventory/list')>class='active-menu'</#if>
                        href="/seller/inventory/list"><i class="fa fa-list "></i>List</a>
                    </li>
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/inventory/index')>class='active-menu'</#if>
                        href="/seller/inventory/index"><i class="fa fa-flask "></i>Add</a>
                    </li>
                </ul>
            </li>
            
            <!-- vendor section-->
            <li>
                <a <#if springMacroRequestContext.requestUri?contains('/seller/vendor')>class='active-menu-top'</#if>
                href="#"><i class="fa fa-user "></i>Vendor<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level <#if springMacroRequestContext.requestUri?contains('/seller/vendor')>collapse in</#if>">
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/vendor/list')>class='active-menu'</#if>
                        href="/seller/vendor/list"><i class="fa fa-list "></i>List</a>
                    </li>
                    <li>
                        <a <#if springMacroRequestContext.requestUri?contains('/seller/vendor/index')>class='active-menu'</#if>
                        href="/seller/vendor/index"><i class="fa fa-flask "></i>Add</a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</nav>
<!-- main side end  -->
        