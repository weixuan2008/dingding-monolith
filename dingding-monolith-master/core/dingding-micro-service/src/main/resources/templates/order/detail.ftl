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
					                            <th>Order Id</th>
					                            <th>Total amount</th>
					                        </tr>
					                    </thead>
				                        <tbody>
					                        <tr>
					                            <td>${orderDTO.orderId}</td>
					                            <td>${orderDTO.orderAmount}</td>
					                        </tr>
				                        </tbody>   
                                    </table>
                                </div>
                                
                                
                                <#--order detail-->
				                <div class="table-responsive">
				                    <table class="table table-bordered">
				                        <thead>
				                        <tr>
				                            <th>productId</th>
				                            <#--th>Name</th-->
				                            <th>Price</th>
				                            <th>Quantity</th>
				                            <th>Amount</th>
				                        </tr>
				                        </thead>
				                        <tbody>
				                        <#list orderDTO.orderDetailList as orderDetail>
				                        <tr>
				                            <td>${orderDetail.productId}</td>
				                            <#--td>${orderDetail.productName}</td-->
				                            <td>${orderDetail.productPrice}</td>
				                            <td>${orderDetail.productQuantity}</td>
				                            <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
				                        </tr>
				                        </#list>
				                        </tbody>
				                    </table>
				                </div>
                
                				<#--operation-->
				                <div class="col-md-12 column">
				                	<#if orderDTO.getOrderStatus().msg == "New">
					                    <a href="/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">Complete</a>
					                    <a href="/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">Cancel</a>
				                	</#if>
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



	<#-- popup window -->
	<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h4 class="modal-title" id="myModalLabel">
	                    提醒
	                </h4>
	            </div>
	            <div class="modal-body">
	                你有新的订单
	            </div>
	            <div class="modal-footer">
	                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
	            </div>
	        </div>
	    </div>
	</div>

	<#--play music -->
	<audio id="notice" loop="loop">
	    <source src="/mp3/song.mp3" type="audio/mpeg" />
	</audio>

	<script>
	    var websocket = null;
	    if('WebSocket' in window) {
	        websocket = new WebSocket('ws://localhost:9600/webSocket');
	    }else {
	        alert('该浏览器不支持websocket!');
	    }
	
	    websocket.onopen = function (event) {
	        console.log('建立连接');
	    }
	
	    websocket.onclose = function (event) {
	        console.log('连接关闭');
	    }
	
	    websocket.onmessage = function (event) {
	        console.log('收到消息:' + event.data)
	        //弹窗提醒, 播放音乐
	        $('#myModal').modal('show');
	
	        document.getElementById('notice').play();
	    }
	
	    websocket.onerror = function () {
	        alert('websocket通信发生错误！');
	    }
	
	    window.onbeforeunload = function () {
	        websocket.close();
	    }
	
	</script>

</body>
</html>