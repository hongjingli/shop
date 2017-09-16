<#include "mod/header.ftl">
    <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>欢迎</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="latest-product">
                        <h2 class="section-title">最新商品</h2>
                        <div class="product-carousel">
                            <#assign carouselList = latestList/>
                            <#include "mod/carousel.ftl"/>
                </div>
            </div>
        </div>
    </div>
</div>
</div> <!-- End main content area -->


<#include "mod/footer.ftl">