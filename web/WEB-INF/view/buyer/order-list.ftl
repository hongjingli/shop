<#include "mod/header.ftl">  
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>我的订单</h2>
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

            <#if orderList?size = 0>
            <h1>暂无记录</h1>
            </#if>
            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <#list orderList as order>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th ></th>                                    
                                    <th class="product-name">商品</th>
                                    <th class="product-price">单价</th>
                                    <th class="product-quantity">数量</th>
                                    <th class="product-subtotal">总价</th>
                                </tr>
                            </thead>
                            <tbody>                                   
                                <#list order.items as item>                                 
                                <tr class="cart_item">                                    
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
                                <td class="actions" colspan="5">
                                    <p class="sidebar-title">总价：${order.totalPrice}</p>
                                    </td>
                                    </tr>
                                <tr>
                                <td class="actions" colspan="5">
                                    <label for="">下单时间：
                                        <span>
                                            ${order.orderTime}                                                 
                                        </span><br>
                                        <label for="">订单状态：
                                            <span>
                                                <#if order.status == 0>买家已下单
                                                <#elseif order.status ==1>交易成功
                                                <#elseif order.status ==2>卖家取消了订单
                                                </#if>                                                 
                                            </span>
                                        </label>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td class="actions" colspan="5">
                                        <h4>卖家信息：</h4>
                                        <label for="">帐号名：<span>${order.seller.username}</span></label><br>
                                        <label for="">手机号：<span>${order.seller.phone}</span></label><br>
                                        <label for="">qq：<span>${order.seller.qq!"（无）"}</span></label><br>
                                        <label for="">联系地址：<span>${order.seller.address}</span></label><br>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            </#list>
                            <div class="cart-collaterals">

                            </div>
                        </div>                        
                    </div>                    
                </div>
            </div>
        </div>
    </div>

    <#include "mod/footer.ftl">  
