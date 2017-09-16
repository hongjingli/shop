</body>
<script>  
//设置cookie  
function setCookie(cname, cvalue) {
	var exdays = 1;
	var d = new Date();  
	d.setTime(d.getTime() + (exdays*24*60*60*1000));  
	var expires = "expires="+d.toUTCString();  
	document.cookie = cname + "=" + cvalue + "; " + expires;  
}  
//获取cookie  
function getCookie(cname) {  
	var name = cname + "=";  
	var ca = document.cookie.split(';');  
	for(var i=0; i<ca.length; i++) {  
		var c = ca[i];  
		while (c.charAt(0)==' ') c = c.substring(1);  
		if (c.indexOf(name) != -1) return c.substring(name.length, c.length);  
	}  
	return "";  
}  
//清除cookie    
function clearCookie(name) {    
	setCookie(name, "", -1);    
}    
function checkCookie(cname) {  
	return getCookie(cname) == "";
}  

</script>  

<script type="text/javascript">
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
</script>

<!-- Latest jQuery form server -->
<script src="https://code.jquery.com/jquery.min.js"></script>

<!-- Bootstrap JS form CDN -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- jQuery sticky menu -->
<script src="/buyer/js/owl.carousel.min.js"></script>
<script src="/buyer/js/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="/buyer/js/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="/buyer/js/main.js"></script>

<!-- Slider -->
<script type="text/javascript" src="/buyer/js/bxslider.min.js"></script>
<script type="text/javascript" src="/buyer/js/script.slider.js"></script>

<script type="text/javascript" src="/buyer/js/jNotify.jquery.js"></script> 
<script>    	
	function rejectOrder(id) {
		confirm("拒绝订单", "确定要拒绝此订单吗？", function () {
			$.ajax({
				url: '/seller/order/cancel',
				type: 'GET',		
				data: "orderId=" + id,
				success: function(res) {
					location.reload(true);
				}
			});			
		});
	}
	function confirmOrder(id) {
		confirm("确认已收款", "交易成功请确认。", function () {
			$.ajax({
				url: '/seller/order/confirm',
				type: 'GET',		
				data: "orderId=" + id,
				success: function(res) {
					location.reload(true);
				}
			});			
		});
	}
	function doOrder() {
		confirm("确认下单", "下单后买家需要当面支付，且无法取消订单，继续吗？", function () {
			$.ajax({
				url: "/buyer/order/do-order",
				type: 'GET',
				success: function (res) {
					orderList();
				}
			});
		});
	}
	function confirm(title, msg, func) {
		$('#confirm-title').html(title);
		$('#confirm-msg').html(msg);
		$('#confirm-btn').on('click', func);
		$('#confirm-dialog').modal('toggle');
	}
	function editInfo() {
		$.ajax({
			url: '/buyer/person/detail/do-edit',
			type: 'POST',
			data: $('#edit-form').serialize(),
			success: function(res) {
				if (res.success)
					jSuccess("修改成功");
				else {
					jError(res.info);
				}
			}
		});
	}

	function deleteGoods(id) {
		confirm("删除商品", "确定要删除此商品吗?", function () {
			$.ajax({
				url: '/seller/goods/do-remove',
				type: 'GET',
				data: "goodsId=" + id,
				success : function (res) {
					if (res.success) 
						jSuccess("删除成功");
					$('#goods-' + id).hide();
				}
			});
		});
	}

	function reset(psw, repsw) {    	
		if (psw != repsw) {
			jError("两次输入密码不一样");
			return ;
		} 
		$.ajax({
			url: '/buyer/person/do-reset',
			type: 'POST',
			data: "password=" + psw,
			success: function (res) {
				if (res.success) {
					jSuccess("修改成功，请重新登录");
					logout();				
				}

				else {
					jError(res.info);
				}
			}
		});
	}
	function editGoods(id) {
		location = "/seller/goods/edit?goodsId=" + id;
	}
	function saleOrderList() {
		if (checkLogin()) {
			location = "/seller/order/list";
		} else {
			showLogin();
		}
	} 	
	function saleGoodsList() {
		if (checkLogin()) {
			location = "/seller/goods/list";
		} else {
			showLogin();
		}
	} 	
	function orderList() {
		if (checkLogin()) {
			location = "/buyer/order/list";
		} else {
			showLogin();
		}
	} 	
	function searchPage(page) {
		location = "/buyer/goods/search?page=" + page + "&keyWord=" + $('#keyWord').val();
	}
	function search() {
		searchPage(1);
	}
	function enterCart() {
		if (checkLogin()) {
			location = "/buyer/person/cart";
		} else {
			showLogin();
		}
	}

	function showLogin() {
		$('#login-dialog').modal('toggle');
	}
	function login() {
		$.ajax({
			url:"/buyer/person/do-login",
			type: "POST",
			data: $('#login-form').serialize(),
			success: function(res) {
				if (!res.success) {
					jError(res.info);
				} else {
					location.reload(true);
				}
			}
		});
	}
	function register() {
		$.ajax({
			url:"/buyer/person/register",
			type: "POST",
			data: $('#register-form').serialize(),
			success: function(res) {
				if (!res.success) {
					jError(res.info);
				} else {
					jSuccess("注册成功");
					location.reload(true);
				}
			}
		});
	}
	function checkLogin() {
		return getCookie("username") != "";
	}
	function addCartItem(goodsId, quantity) {
		if (checkLogin()) {
			$.ajax({
				url: '/buyer/person/cart/do-add?goodsId=' + goodsId + "&quantity=" + quantity,
				type: 'GET',
				success:function (res) {
					if (res.success) {
						jSuccess("添加成功!");
						updateCartLogo();
					} else {
						jError(res.info);
					}               
				}});
		}         
		else {
			showLogin();
		}
	} 
	
	function categoryPage(id) {
		location = "/buyer/goods/category?page=1&categoryId=" + id;
	}

	function detail(goodsId) {
		location = "/buyer/goods/detail?goodsId=" + goodsId;
	}
	function updateCartLogo() {
		if (checkLogin())
			$.ajax({
				url: '/buyer/person/cart/outline',
				type: 'GET',
				success: function (res) {
					if (res == null) 
						return;
					$('#sumCost').html("￥" + res.sumCost);
					$('#sumQuantity').html(res.itemNum);                    
					if ($('#sumCartCost') != null) {
						$('#sumCartCost').html(res.sumCost);
					}
				}
			});             
	}
	function logout() {
		location = "/buyer/person/do-logout";
	}
	function person() {
		if (checkLogin()) 
			location = "/buyer/person/detail";
		else
			showLogin();
	}
	function removeCartItem(id) {
		$.ajax({
			url: '/buyer/person/cart/remove',
			type: 'GET',
			data: 'cartItemId=' + id,
			success: function (res) {
				if (res.success) {
					jSuccess("删除成功");
					$('#cartItem-' + id).hide(); 
					updateCartLogo();                   
				} else {
					jError(res.info);
				}
			}
		});            
	}
	$(function (){      
		$('.nav-li').eq(${com.navActive}).attr('class', 'active');         		
		if (checkLogin()) {
			$('#account-li').html("<i class='fa fa-user'></i>" + getCookie('username')); 
			updateCartLogo();
		} else {
			$('#account-li').html("<i class='fa fa-user'></i>" + "请登录"); 
			$('#logout-li').hide();
			$('#order-li').hide();
		}   
		var keyWord = getQueryString('keyWord');    
		if (keyWord!=null)
			$('#keyWord').val(keyWord); 		
	});

	
</script>
</html>

