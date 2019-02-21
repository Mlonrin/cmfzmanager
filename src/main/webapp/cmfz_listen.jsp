<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>

    <link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css"/>

</head>

<script>

    $(function(){
        //datagrid表格分页
        $("#userTable").datagrid({
            title:"轮播图列表",
            url:"${pageContext.request.contextPath}/album/selectByPage",
            pagination:true,
            rownumbers:true,
            columns:[[
                {checkbox:true},
                {field:"albumName",title:"专辑名"},
                {field:"albumAuthor",title:"作者"},
                {field:"albumTeller",title:"朗读者"},
                {field:"albumEpisodes",title:"集数"},
                {field:"albumDate",title:"上传时间"},
                {field:"albumContent",title:"专辑简介",formatter:function (value,rowData,rowIndex) {
                        return value.substring(0,10);
                    }
                },
                {field:"albumImage",title:"专辑封面",formatter:function (value,rowData,rowIndex){
                        return '<img src="http://192.168.133.1:8018/server/'+value+'" height="30px" width="24px"/>';
                    }
                },
                {field:"albumStar",title:"评分"},
                {field:"albumId",title:"操作",formatter:function (value,rowData,rowIndex) {
                        
                    }
                }
            ]],
            pageSize:4,
            pageList:[4,8,10],
            //loadMsg:"正在从契卡塔向您赶来",
            //在左上角添加组件 （添加删除）
            toolbar:'#myAdd',
            onDblClickRow:function (rowIndex, rowData) {
                //双击当前行，打开修改会话框
                //打开会话框
                $("#updateUser").dialog("open");
                //把修改的数据写入输入框中
                insertData(rowIndex,rowData);
            }
        });

        //添加开始======================================================

        //给添加栏格式，并隐藏
        $("#addUser").dialog({
            title:"添加用户",
            closed:true,
            width:350,
            height:200
        });

        $("#addButton").click(function () {
            //点击展开添加会话框
            $("#addUser").dialog("open");
        })

        $("input[value='添加']").click(function () {
            //给添加栏form表单格式化
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/album/addAlbum",
                success:function (data) {
                    //console.log(data)
                    if(data) {
                        //添加成功并关闭添加会话框，并刷新
                        $("#addUser").dialog("close");
                        $("#userTable").datagrid("reload");
                    }else{
                        //console.log(data)
                        $.messager.alert("添加提示信息","添加失败","error");
                    }
                }
            })
        })

        // 添加结束====================================================



        //修改部分=========================================================开始

        //给修改会话框格式并隐藏
        $("#updateUser").dialog({
            title:"修改用户",
            closed:true,
            width:260,
            height:200
        });

        $("input[value='修改']").click(function () {
            //给修改会话框form格式化
            $("#updateForm").form("submit",{
                url:"${pageContext.request.contextPath}/album/update",
                success:function (data) {
                    //console.log(data)
                    if(data) {
                        //alert("修改成功");
                        //关闭修改会话框
                        $("#updateUser").dialog("close");
                        //刷新
                        $("#userTable").datagrid("reload");
                    }else{
                        $.messager.alert("修改提示","修改失败","warning");
                    }
                }
            })
        })

        //修改结束=============================================================

        //批量删除============================================================开始

        $("#removeButton").click(function () {
            //获取所有被选中的行
            var selectRows = $("#userTable").datagrid("getSelections");
            //判断被选中的是为空
            if(selectRows.length==0){
                //为空，提示用户
                $.messager.alert("删除提示","请选中您要删除的行？","info");
            }else {
                //不为空
                var ids = new Array();
                for (var i = 0; i < selectRows.length; i++) {
                    //获取列属性值，id为列属性
                    ids[i] = selectRows[i].albumId;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/album/multiDelete",
                    type:"post",
                    data:"ids="+ids,
                    success:function (data) {
                        //console.log(data)
                        if(data){
                            $.messager.alert("删除提示","删除成功","info");
                            //重新加载
                            $("#userTable").datagrid("reload");
                        }else {
                            $.messager.alert("提示框","删除失败","warning");
                        }
                    }
                })
            }
        })
        //批量删除============================================================结束
    })

    //双击行时触发，
    function insertData(rowIndex,rowData){
        //给输入框赋值
        $("#bannerId").val(rowData.bannerId);
        $("#bannerOldName").val(rowData.bannerOldName);
        $("#bannerDescription").text(rowData.bannerDescription);
    }

    //搜索
    function sousuo() {

        //console.log("+++++++++++++")
        //获取搜索参数
        var name = $("#uname").val();
        //console.log(name)
        $("#userTable").datagrid("load",{
            name:name
        });
    }

</script>

<body>

    <input id="uname"/><input type="button" value="搜索" onclick="sousuo()"/>
    <table id="userTable" height="100%" width="100%"></table>

    <%--添加按钮--%>
    <div id="myAdd">
        <a id="addButton" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
        <a id="removeButton" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
    </div>

    <div id="addUser">
        <form id="addForm" method="post" enctype="multipart/form-data">
            选择文件：<input type="file" name="file"/><br/>
            图片描述：<textarea name="bannerDescription"></textarea><br/>
            <input type="button" value="添加"/>
        </form>
    </div>



    <div id="updateUser">
        <form id="updateForm" method="post">
            <input id="bannerId" type="hidden" name="bannerId"/>
            文件名：<input id="bannerOldName" name="bannerOldName"/><br/>
            文件描述：<textarea id="bannerDescription"></textarea><br/>
            <input type="button" value="修改"/>
        </form>
    </div>

</body>
</html>
