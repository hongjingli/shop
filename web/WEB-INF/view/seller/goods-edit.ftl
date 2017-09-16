<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="/favicon.ico" >
		<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/admin/lib/html5.js"></script>
<script type="text/javascript" src="/admin/lib/respond.min.js"></script>
<script type="text/javascript" src="/admin/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="/seller/css/default.css" rel="stylesheet" type="text/css" >
<link href="/seller/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />	
<link rel="stylesheet" type="text/css" href="/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/admin/static/h-ui/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/admin/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/admin/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="/admin/static/h-ui/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/admin/static/h-ui/css/style.css" />
<link rel="stylesheet" type="text/css" href="/buyer/css/jNotify.jquery.css">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

</head>
<body>	
	<div class="page-container">
		<div class="form form-horizontal" >
			<form action="" id="goods-add-form" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${goods.id}">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${goods.name}" placeholder="" id="name" name="name">
					</div>
				</div>		
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否上架：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<#if goods.status = 1>
						<label for="status-1"><input id="status-1" type="radio" checked="checked" name="status" value="1">下架&nbsp;&nbsp;&nbsp;</label>
						<label for="status-2"><input id="status-2" type="radio" name="status" value="2">上架</label>
						<#elseif goods.status = 2>
						<label for="status-1"><input id="status-1" type="radio"  name="status" value="1">下架&nbsp;&nbsp;&nbsp;</label>
						<label for="status-2"><input id="status-2" type="radio" checked="checked" name="status" value="2">上架</label>
						</#if>

					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>分类栏目：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select name="categoryId" class="select">
							<#list ct.child as pitem>							
								<#if pitem.curr.id = goods.categoryId>
								<option value="${pitem.curr.id}" selected="selected">${pitem.curr.name}</option>
								<#else>
								<option value="${pitem.curr.id}">${pitem.curr.name}</option>
								</#if>
								<#list pitem.child as citem>
								<#if citem.curr.id = goods.categoryId>
								<option value="${citem.curr.id}" selected="selected">${citem.curr.name}</option>
								<#else>
								<option value="${citem.curr.id}">├${citem.curr.name}</option>
								</#if>								
								</#list>                                       			
							</#list>												
					</select>
				</span> </div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>价格：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" name="price" id="" placeholder="" value="${goods.price}" class="input-text" style="width:90%">
					元</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>货存数量：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="number" min=1 value="1" name="quantity" id="" placeholder="" value="${goods.quantity}" class="input-text" style="width:90%">
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">商品描述：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<textarea name="description" cols="" rows="" class="textarea"  placeholder="说点什么..." dragonfly="true" onKeyUp="">${goods.description!''}</textarea>						
					</div>
				</div>	
			</form>	
			<form action="" id="mainImage-form" enctype="multipart/form-data">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品图片：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<div class="col-sm-9 big-photo">
							<div id="preview">
								<img id="imghead" border="0" src="${goods.mainImage}" width="90" height="90" onclick="$('#previewImg').click();">
							</div>         
							<input type="file" name="mainImage" onchange="previewImage(this, 'preview')" style="display: none;" id="previewImg">
						</div>
					</div>
				</div>		
			</form>
			<form action="" id="moreImage-form" enctype="multipart/form-data">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">其它图片：</label>		              
					<div class="col-sm-9 big-photo">

						<#list goods.moreImage as i>
						<input type="hidden" name="moreImage-id[]" value="${i.id}">			
						<#if i.imageUrl != ''>	
						<div id="preview${i_index + 2}">
							<img id="imghead${i_index + 2}" border="0" src="${i.imageUrl}" width="90" height="90" onclick="$('#preview${i_index + 2}Img').click();">
						</div>         
						
						<#else>
						<div id="preview${i_index + 2}">
							<img id="imghead${i_index + 2}" border="0" src="/buyer/img/photo_icon.png" width="90" height="90" onclick="$('#preview${i_index + 2}Img').click();">
						</div>    
						</#if>
						<input type="file" name="moreImage[]" onchange="previewImage(this, 'preview${i_index + 2}')" style="display: none;" id="preview${i_index + 2}Img">

						</#list>

					</div>        			
				</div>	
			</form>		
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="goodsEditSubmit()" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i>确认提交</button>
					<button onClick="history.go(-1);" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>		
		</div>		
	</div>

	
</body>
<!--_footer 作为公共模版分离出去-->

	<script type="text/javascript" src="/seller/js/jquery.js"></script> 
	<script type="text/javascript" src="/buyer/js/jNotify.jquery.js"></script>
	<script src="/seller/js/fileinput.js" type="text/javascript"></script>
	<script src="/seller/js/fileinput_locale_zh.js" type="text/javascript"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript">		
		
		function goodsEditSubmit() {

			var data = new FormData($('#moreImage-form')[0]);
			$.ajax({
				type: "POST",
				url: "/seller/goods/moreImage",
				contentType: false,
				processData: false,
				data: data,
				success: function (res) {					
					if (res.success) {
						var data2 = new FormData($('#mainImage-form')[0]);
						$.ajax({
							type: "POST",
							url: "/seller/goods/mainImage",
							contentType: false,
							processData: false,
							data: data2,
							success: function (res) {
								if (res.success) {
									$.ajax({
										url: '/seller/goods/do-edit',
										type: 'POST',
										data: $('#goods-add-form').serialize(),
										success: function (res) {
											if (res.success) {
												jSuccess("修改成功");						
												setTimeout(function() {
													location="/seller/goods/list";
												}, 3);																	
											} else {
												jError(res.info);		
											}
										}
									});									
								} else {
									jError(res.info);
								}								
							}
						});		
					} else {
						jError(res.info);
					} 
				}
			});
		}
	</script>
	<script>
      //图片上传预览    IE是用了滤镜。
      function previewImage(file, id)
      {
      	var MAXWIDTH  = 90; 
      	var MAXHEIGHT = 90;
      	var div = document.getElementById(id);
      	if (file.files && file.files[0])
      	{
      		div.innerHTML ='<img id=imghead' + id + ' onclick=$("#' + id + 'Img").click()>';
      		var img = document.getElementById('imghead' + id);
      		img.onload = function(){
      			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
      			img.width  =  rect.width;
      			img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
img.style.marginTop = rect.top+'px';
}
var reader = new FileReader();
reader.onload = function(evt){img.src = evt.target.result;}
reader.readAsDataURL(file.files[0]);
}
          else //兼容IE
          {
          	var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
          	file.select();
          	var src = document.selection.createRange().text;
          	div.innerHTML = '<img id=imghead'+ id + '>';
          	var img = document.getElementById('imghead' + id);
          	img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
          	var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
          	status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
          	div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
      }
      function clacImgZoomParam( maxWidth, maxHeight, width, height ){
      	var param = {top:0, left:0, width:width, height:height};
      	if( width>maxWidth || height>maxHeight ){
      		rateWidth = width / maxWidth;
      		rateHeight = height / maxHeight;

      		if( rateWidth > rateHeight ){
      			param.width =  maxWidth;
      			param.height = Math.round(height / rateWidth);
      		}else{
      			param.width = Math.round(width / rateHeight);
      			param.height = maxHeight;
      		}
      	}
      	param.left = Math.round((maxWidth - param.width) / 2);
      	param.top = Math.round((maxHeight - param.height) / 2);
      	return param;
      }
  </script>
</html>