<#include "mod/header.ftl">  
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>购物车</h2>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End Page title area -->


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">                
            <#include "mod/left.ftl"/>

            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <#if cartList?size = 0>
                        <h1>暂无记录</h1>
                        <#else>                        
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th class="product-remove">&nbsp;</th>
                                    <th class="product-thumbnail">&nbsp;</th>
                                    <th class="product-name">商品</th>
                                    <th class="product-price">单价</th>
                                    <th class="product-quantity">数量</th>
                                    <th class="product-subtotal">总价</th>
                                </tr>
                            </thead>
                            <tbody>   
                                <#list cartList as item>                                 
                                <tr class="cart_item" id="cartItem-${item.cartItemId}">
                                    <td class="product-remove">
                                        <a title="Remove this item" class="remove" href="#" onclick="removeItem(${item.cartItemId})">×</a> 
                                    </td>

                                    <td class="product-thumbnail">
                                        <a href="#" onclick="detail(${item.goodsId})"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="${item.mainImage}"></a>
                                    </td>

                                    <td class="product-name">
                                        <a href="#" onclick="detail(${item.goodsId})">${item.name}</a> 
                                    </td>

                                    <td class="product-price">
                                        <span class="amount">￥${item.singlePrice}</span> 
                                    </td>

                                    <td class="product-quantity">
                                        <div class="quantity buttons_added">

                                            <span class="amount">${item.quantity}</span> 

                                        </div>
                                    </td>

                                    <td class="product-subtotal">
                                        <span class="amount">￥${item.totalPrice}</span> 
                                    </td>
                                </tr>
                                </#list>
                                <tr>
                                    <td class="actions" colspan="6">                                                
                                    <label class="sidebar-title" for="">总价：<span id="sumCartCost">0</span></label>
                                    </td>
                                </tr>
                                <tr>

                                    <td class="actions" colspan="6">                                                
                                        <input type="submit" onclick="doOrder()" value="统一下单" name="proceed" class="checkout-button button alt wc-forward">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </#if>
                        <div class="cart-collaterals">

                        </div>
                    </div>                        
                </div>                    
            </div>
        </div>
    </div>
</div>

<#include "mod/footer.ftl">  
