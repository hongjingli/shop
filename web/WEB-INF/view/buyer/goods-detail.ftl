<#include "mod/header.ftl"/>
<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>商品详情</h2>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="single-product-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<#include "mod/left.ftl"/>

			<div class="col-md-8">
				<div class="product-content-right">
					<div class="product-breadcroumb">
						<#list goods.cl as clItem>
						<a href="/buyer/goods/category?categoryId=${clItem.id}&page=1">${clItem.name}</a>             
						</#list>               
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="product-images">
								<div class="product-main-img">
									<a href="${goods.mainImage}"><img class="stdImage" src="${goods.mainImage}" alt=""></a>
								</div>

								<div class="product-gallery">                                
									<#list goods.moreImage as img>
									<#if img.imageUrl != ''>
									<a href="${img.imageUrl}">
										<img src="${img.imageUrl}" alt="">
									</a>
									</#if>
									</#list>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="product-inner">
								<h2 class="product-name">${goods.name}</h2>
								<div class="product-inner-price">
									<ins>￥${goods.price}</ins> <!-- <del>$100.00</del> -->
								</div>    

								<form action="" class="cart">
									<div class="quantity">
										<input type="number" id="quantity" size="4" class="input-text qty text" title="数量" value="1" name="quantity" min="1" step="1" max="${goods.quantity}">
									</div>
									<div class="quantity">（库存${goods.quantity}件）</div>
									
								</form>   

								<button class="add_to_cart_button" onclick="addCartItem(${goods.id},$('#quantity').val())">加入购物车</button>
								<br>
								<br>
								<div role="tabpanel">
									<ul class="product-tab" role="tablist">
										<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">详情</a></li>
										<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">联系信息</a></li>
									</ul>
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane fade in active" id="home">
											<h2>商品描述</h2>  
											<p>${goods.description!"<暂无描述>"}</p>
											
										</div>
										<div role="tabpanel" class="tab-pane fade" id="profile">
											<h2>卖家信息</h2>
											<div class="submit-review">
												<p><label for="name">用户名</label> ${goods.seller.username}</p>
												<p><label for="phone">手机号</label> ${goods.seller.phone}</p>
												<p><label for="qq">QQ</label>
													${goods.seller.qq!"（无）"}</p>
													<p><label for="address">联系地址</label>${goods.seller.address}</p>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>


						<#-- <div class="related-products-wrapper">
							<h2 class="related-products-title">同类商品</h2>
							<div class="product-carousel">
							<#assign carouselList = sameCategoryGoods>
							<#include "mod/carousel.ftl">
							</div>
						</div> -->
					</div>                    
				</div>
			</div>
		</div>
	</div>
	<#include "mod/footer.ftl"/>
