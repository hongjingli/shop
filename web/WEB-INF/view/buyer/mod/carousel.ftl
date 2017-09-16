<#list carouselList as item>                            
<div class="single-product" >                            
	<div class="product-f-image">
		<img src="${item.mainImage}" class="stdImage" alt="" />
		<div class="product-hover">
			<a href="#" onclick="addCartItem(${item.id},1)" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> 加入购物车</a>
			<a href="#" onclick="detail(${item.id})" class="view-details-link"><i class="fa fa-link"></i> 商品详情</a>
		</div>
	</div>                           

	<h2><a href="#" onclick="detail(${item.id})">${item.name}</a></h2>

	<div class="product-carousel-price">
		<ins>￥${item.price}</ins><!-- <del>$1355.00</del> -->
	</div>                            
</div>
</#list>
