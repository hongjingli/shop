<div class="col-md-4">
    <div class="single-sidebar">
        <h2 class="sidebar-title">商品搜索</h2>        
            <input type="hidden" value="1" name="page">
            <input type="text" id="keyWord" placeholder="">
            <input type="submit" onclick="search()" value="查找">        
    </div>
    
    <div class="single-sidebar">
        <h2 class="sidebar-title">最新商品</h2>
        <#list latestList as item>
        <div class="thubmnail-recent">
            <img src="${item.mainImage}" class="recent-thumb" alt="">
            <h2><a href="#" onclick="detail(${item.id})">${item.name}</a></h2>
            <div class="product-sidebar-price">
                <ins>￥${item.price}</ins> <!-- <del>$100.00</del> -->
            </div>                             
        </div>
        </#list>
    </div>
</div>