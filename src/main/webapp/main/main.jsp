<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">   
<script type="text/javascript" src="../js/jquery.min.js"></script>   
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
    $(function () {

        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/menu/selectAll",
            success:function(date){

                $.each(date,function(index,p){

                    console.log(index);
                    console.log(p);

                    var array = p.menuList;

                    var tl = "";

                    $.each(array,function(i,q){
                        //console.log(q.menuUrl);
                        tl += '<input type="button" onclick="openTabs(\''+q.menuUrl+'\',\''+q.menuName+'\')" value="'+q.menuName+'" /><br/>';
                    })

                    console.log(tl)
                    $('#aa').accordion('add', {
                        title: p.menuName,
                        content: tl,
                        selected: false
                    });
                })
            }
        })

    })

    function openTabs(menuUrl,menuName){
	    // console.log(menuUrl);
	    // console.log(menuName);
        //判断书本列表是否存在，判断是否有该页签
        var isExistsUserManager = $("#tt").tabs("exists",menuName);
        if(isExistsUserManager){
            //存在则打开该类别页签
            $("#tt").tabs("select",menuName);
        }else {
            //不存在则，在页签 div 中新建一个页签，调用add 方法
            $('#tt').tabs('add', {
                title: menuName,
                //iframe标签可以引入jsp，请求jsp页面时可以把参数存于请求路径后边，发送请求，将类别id传入，查询出来，展示图书
                content: "<iframe src='${pageContext.request.contextPath}"+menuUrl+"' height='100%' width='100%'></iframe>",
                //可关闭
                closable: true,
                //选中
                selected:true
            });
        }
    }


</script>

</head>
<body class="easyui-layout">



    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 400px;float:right;padding-top:15px">
            欢迎您:${sessionScope.admin.username} &nbsp;
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/admin/exit" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
        </div>
    </div>

    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">

		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>