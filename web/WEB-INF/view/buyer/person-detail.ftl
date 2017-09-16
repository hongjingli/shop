<#include "mod/header.ftl"/>
    <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>个人信息</h2>
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
                        <div class="woocommerce">
                            <div class="woocommerce-info">修改密码？<a class="showlogin" data-toggle="collapse" href="#login-form-wrap" aria-expanded="false" aria-controls="login-form-wrap">点击修改</a>
                            </div>

                            <div id="login-form-wrap" class="login collapse" >


                                <p class="form-row form-row-first">
                                    <label for="password">请输入：<span class="required">*</span>
                                    </label>
                                    <input type="password" id="password" name="password" class="input-text">
                                </p>
                                <p class="form-row form-row-last">
                                    <label for="password">请重新输入: <span class="required">*</span>
                                    </label>
                                    <input type="password" id="repassword" name="repassword" class="input-text">
                                </p>                                                            
                                <p class="form-row form-row-last">
                                    <input type="submit" value="确认修改" name="apply_coupon" class="button" onclick="reset($('#password').val(), $('#repassword').val())">
                                </p>
                                <div class="clear"></div>
                            </div>                           

                            <div class="checkout"  name="checkout">

                                <div id="customer_details" class="col2-set">
                                    <div class="col-1">
                                        <div class="woocommerce-billing-fields">
                                        <h3>联系方式</h3>
                                            
                                            <form action="" id="edit-form">
                                            <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                <label class="" for="address">联系地址 
                                                </label>
                                                <input type="text" value="${person.address}" placeholder="" id="address" name="address" class="input-text ">
                                            </p>

                                            <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                                <label class="" for="phone">手机号 
                                                </label>
                                                <input type="text" value="${person.phone}" placeholder="" id="phone" name="phone" class="input-text ">
                                            </p>

                                            <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                                <label class="" for="qq">QQ 
                                                </label>
                                                <input type="text" value="${person.qq}" placeholder="" id="qq" name="qq" class="input-text ">
                                            </p>
                                            </form>
                                            <div class="clear"></div>                                            
                                        </div>
                                        <div id="payment">
                                            <div class="form-row place-order">
                                                <input type="submit" data-value="Place order" value="确认修改" id="place_order" name="woocommerce_checkout_place_order" class="button alt" onclick="editInfo()">

                                            </div>

                                            <div class="clear"></div>

                                        </div>
                                        <div class="clear"></div>
                                    </div>

                                </div>

                            </div>                       
                        </div>                    
                    </div>
                </div>
            </div>
        </div>        
<#include "mod/footer.ftl"/>