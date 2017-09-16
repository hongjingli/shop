<#include "mod/header.ftl"/>
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>${titleBar}</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="currPage" value="${page.currentPage}">

<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="single-sidebar">
                <h2 class="sidebar-title">商品搜索</h2>
                <input type="text" id="keyWord" placeholder="Search products..." value="">                    
                <input type="submit" value="Search" onclick="search()">
            </div>
            <#list page.goodsList as item>
            <div class="col-md-3 col-sm-6">
                <div class="single-shop-product">
                    <div class="product-upper">
                        <img src="${item.mainImage}" class="stdImage" alt="">
                    </div>
                    <h2><a href="#" onclick="detail(${item.id})">${item.name}</a></h2>
                    <div class="product-carousel-price">
                        <ins>￥${item.price}</ins> <#-- <del>$999.00</del> -->
                    </div>  

                    <div class="product-option-shop">
                        <a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="#" onclick="addCartItem(${item.id}, 1)">加入购物车</a>
                    </div>                       
                </div>
            </div>
            </#list>
        </div>
        <#if (page.goodsList?size > 0)>
        <div class="row">
            <div class="col-md-12">
                <div class="product-pagination text-center">
                    <nav>
                      <ul class="pagination">
                        <li>
                          <a aria-label="Previous" href="${pageUrl}${page.prevPage}">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <#list page.previewList as i>
                    <li><a href="${pageUrl}${i}">${i}</a></li>
                    </#list>
                    <li>
                        <a aria-label="Next" href="${pageUrl}${page.nextPage}">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>                        
        </div>
        <#else>
        <h1>暂无记录</h1>
        </#if>
    </div>
</div>
</div>
</div>
<#include "mod/footer.ftl"/>
