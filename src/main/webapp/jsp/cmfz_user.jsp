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

    $(function () {
        //datagrid表格分页
        $("#userTable").datagrid({
            title:"用户列表",
            url:"${pageContext.request.contextPath}/user/selectByPage",
            pagination:true,
            rownumbers:true,
            columns:[[
                {checkbox:true},
                {field:"nickname",title:"用户昵称"},
                {field:"userImage",title:"用户头像"},
                {field:"sex",title:"性别"},
                {field:"autograph",title:"法号"},
                {field:"telphone",title:"电话号码"},
                {field:"address",title:"用户地址",formatter:function (value,rowData,rowIndex) {
                        return rowData.userProvince+"省"+rowData.userCity+"市";
                    }
                },
                {field:"userStatus",title:"用户状态",formatter:function (value,rowData,rowIndex) {
                        if(value==0){
                            return "未激活";
                        }else if(value==1){
                            return "正常";
                        }else{
                            return "冻结";
                        }
                    }
                },
                {field:"operation",title:"操作",formatter:function (value,rowData,rowIndex) {
                        var userId = rowData.userId;
                        var userStatus = rowData.userStatus;
                        if(userStatus==0){
                            return '<input type="button" value="激活" onclick="activate(\''+userId+'\')"/>';
                        }else if(userStatus==1){
                            return '<input type="button" value="冻结" onclick="freeze(\''+userId+'\')"/>';
                        }else{
                            return '<input type="button" value="解冻" onclick="unfreeze(\''+userId+'\')"/>';
                        }
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
                    ids[i] = selectRows[i].userId;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/user/multiDelete",
                    type:"post",
                    data:"ids="+ids,
                    success:function (data) {
                        //console.log(data)
                        if(data==true){
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


    function activate(userId) {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/activate?id="+userId,
            //date:id = bannerId,
            success:function(date){
                console.log(date);
                if(date){
                    $("#userTable").datagrid("reload");
                    $.messager.alert("激活提示","激活成功","info");
                }else {
                    $.messager.alert("激活提示","激活失败","info");
                }
            }
        })
    }

    function freeze(userId) {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/freeze?id="+userId,
            //date:id = bannerId,
            success:function(date){
                console.log(date);
                if(date){
                    $("#userTable").datagrid("reload");
                    $.messager.alert("冻结提示","冻结成功","info");
                }else {
                    $.messager.alert("冻结提示","冻结失败","info");
                }
            }
        })
    }

    function unfreeze(userId) {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/unfreeze?id="+userId,
            //date:id = bannerId,
            success:function(date){
                console.log(date);
                if(date){
                    $("#userTable").datagrid("reload");
                    $.messager.alert("解冻提示","解冻成功","info");
                }else {
                    $.messager.alert("解冻提示","解冻失败","info");
                }
            }
        })
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
    <table id="userTable"></table>

    <%--添加按钮--%>
    <div id="myAdd">
        <a id="removeButton" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
    </div>

</body>
</html>
