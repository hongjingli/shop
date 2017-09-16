<#include "../buyer/mod/header.ftl">  
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>出售商品</h2>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End Page title area -->


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">                
            <#include "../buyer/mod/left.ftl"/>

            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th class="product-remove">&nbsp;</th>
                                    <th class="product-thumbnail">&nbsp;</th>
                                    <th class="product-name">商品</th>
                                    <th class="product-price">单价</th>
                                    <th class="product-quantity">库存数量</th>
                                    <th class="product-quantity">状态</th>
                                    <th class="product-subtotal">操作</th>
                                </tr>
                            </thead>
                            <tbody>   
                                <#list goodsList as item>                                 
                                <tr class="cart_item" id="goods-${item.id}">
                                    <td class="product-remove">
                                        <a title="Remove this item" class="remove" href="#" onclick="deleteGoods(${item.id})">×</a> 
                                    </td>

                                    <td class="product-thumbnail">
                                        <a href="#" onclick="editGoods(${item.id})"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="${item.mainImage}"></a>
                                    </td>

                                    <td class="product-name">
                                        <a href="#" onclick="editGoods(${item.id})">${item.name}</a> 
                                    </td>

                                    <td class="product-price">
                                        <span class="amount">￥${item.price}</span> 
                                    </td>

                                    <td class="product-quantity">
                                        <div class="quantity buttons_added">

                                            <span class="amount">${item.quantity}</span> 

                                        </div>
                                    </td>
                                    <td class="product-quantity">
                                        <div class="quantity buttons_added">

                                            <span class="amount">
                                                <#if item.status = 1>下架                                  
                                                <#elseif item.status = 2>
                                                上架
                                                </#if>                                                
                                            </span> 

                                        </div>
                                    </td>

                                    <td class="product-subtotal">
                                       <span><a href="#" onclick="editGoods(${item.id})">编辑</a></span>
                                    </td>
                                </tr>
                                </#list>                                
                                <tr>

                                    <td class="actions" colspan="7">                                                
                                        <input type="submit" onclick="location='/seller/goods/add'" value="新增商品" name="proceed" class="checkout-button button alt wc-forward">
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="cart-collaterals">

                        </div>
                    </div>                        
                </div>                    
            </div>
        </div>
    </div>
</div>

<#include "../buyer/mod/footer.ftl">  
