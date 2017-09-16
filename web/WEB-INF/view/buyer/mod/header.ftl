<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>116商城</title>

    <LINK rel="Bookmark" href="/favicon.ico" >
    <LINK rel="Shortcut Icon" href="/favicon.ico" />
    
    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
        
    <link rel="stylesheet" href="/buyer/css/jNotify.jquery.css" /> 
    <link rel="stylesheet" href="/buyer/css/bootstrap.min.css">
    <link rel="stylesheet" href="/buyer/css/font-awesome.min.css">
    <link rel="stylesheet" href="/buyer/css/owl.carousel.css">
    <link rel="stylesheet" href="/buyer/css/style.css">
    <link rel="stylesheet" href="/buyer/css/responsive.css">    
    <link rel="stylesheet" href="/buyer/css/layer-nav.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
  </head>
  <body>

    <div class="header-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="user-menu">
                        <ul id="top-menu">
                            <li><a href="#" id="account-li" onclick="person()"><i class="fa fa-user"></i> My Account</a></li>
                            <li><a href="#" id="order-li" onclick="orderList()"><i class="fa fa-hospital-o"></i> 我的订单</a></li>              
                            <li><a href="#" id="logout-li" onclick="logout()"><i class="fa fa-reply"></i> 退出登录</a></li>              
                        </ul>
                    </div>
                </div>


            </div> 
        </div>
    </div> 

    <div class="site-branding-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="logo">
                        <h1><a href="/buyer/index"><img src="/buyer/img/logo.png"></a></h1>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="shopping-item">
                        <a href="#" onclick="enterCart()">购物车 - <span class="cart-amunt" id="sumCost">￥0</span> <i class="fa fa-shopping-cart"></i> <span class="product-count" id="sumQuantity">0</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End site branding area -->

    <div class="mainmenu-area">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div> 
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav" id="nav">
                        <li class="nav-li"><a href="/buyer/index">首页</a></li>                        
                        <li class="nav-li"><a href="#" onclick="enterCart()">购物车</a></li>
                        <li class="nav-li"><a href="#" onclick="orderList()">订单</a></li>
                        <li class="nav-li" id="cate-li">
                            <a href="#">分类目录</a>
                            <ul>
                                <#list com.ct.child as pitem>
                                <li>
                                    <a href="#" onclick="categoryPage(${pitem.curr.id})">${pitem.curr.name}</a>
                                    <ul>
                                        <#list pitem.child as citem>
                                        <li><a href="#" onclick="categoryPage(${citem.curr.id})">${citem.curr.name}</a></li>
                                        </#list>                                        
                                    </ul>
                                </li>
                                </#list>
                            </ul>
                        </li>
                        <li class="nav-li"><a href="#" onclick="person()">个人信息</a></li>
                        <li class="nav-li"><a href="#" onclick="saleGoodsList()">出售商品</a></li>
                        <li class="nav-li"><a href="#" onclick="saleOrderList()" >卖家订单</a></li>
                    </ul>
                </div>  
            </div>
        </div>
    </div> <!-- End mainmenu area -->


    
    <!-- 注册窗口 -->
    <div id="register-dialog" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">注册</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" id="register-form">
                        <div class="form-group">
                            <label for="">用户名</label>
                            <input class="form-control" type="text" name="username" placeholder="6-15位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">密码</label>
                            <input class="form-control" type="password" name="password" placeholder="至少6位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">再次输入密码</label>
                            <input class="form-control" type="password" placeholder="至少6位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">手机号</label>
                            <input class="form-control" type="email" name="phone" placeholder="请输入手机号">
                        </div>
                        <div class="form-group">
                            <label for="">联系地址</label>
                            <input class="form-control" type="email" name="address" placeholder="请输入联系地址">
                        </div>
                        <div class="form-group">
                            <label for="">QQ</label>
                            <input class="form-control" type="email" name="qq" placeholder="请输入QQ号">
                        </div>
                    </form>
                    <div class="text-right">
                        <button class="btn btn-primary" onclick="register()">提交</button>
                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>
                    <a href="" data-toggle="modal" data-dismiss="modal" data-target="#login-dialog">已有账号？点我登录</a>                    
                </div>
            </div>
        </div>
    </div>
    <!-- 登录窗口 -->
    <div id="login-dialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">登录</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" id="login-form">
                        <div class="form-group">
                            <label for="">用户名</label>
                            <input class="form-control" type="text" name="username" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="">密码</label>
                            <input class="form-control" type="password" name="password" placeholder="">
                        </div>
                    </form>
                    <div class="text-right">
                        <button class="btn btn-primary" onclick="login()">登录</button>
                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>
                    <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register-dialog">还没有账号？点我注册</a>                    

                </div>
            </div>
        </div>
    </div>
<!-- confirm -->
    <div id="confirm-dialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center" id="confirm-title">登录</h1>
                </div>
                <div class="modal-body">
                    <p id="confirm-msg"></p>
                    <div class="text-right">
                        <button class="btn btn-primary" id="confirm-btn" data-dismiss="modal">确定</button>
                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>                                    

                </div>
            </div>
        </div>
    </div>

