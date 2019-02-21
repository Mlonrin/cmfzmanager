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
            title:"上师文章列表",
            url:"${pageContext.request.contextPath}/article/selectByPage",
            pagination:true,
            rownumbers:true,
            columns:[[
                {checkbox:true},
                {field:"articleName",title:"文章名"},
                {field:"articleImage",title:"配图",formatter:function (value,rowData,rowIndex){
                        return '<img src="http://192.168.133.1:8018/server/'+value+'" height="30px" width="24px"/>';
                    }
                },
                {field:"guru",title:"上师",formatter:function (value,rowData,rowIndex) {
                        return value.guruName;
                    }
                },
                {field:"articleDate",title:"发布时间"},
                {field:"articleContent",title:"文章内容",formatter:function (value,rowData,rowIndex) {
                        return '<textarea>'+value+'</textarea>';
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
            height:260
        });

        $("#addButton").click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/guru/selectAll",
                type:"get",
                success:function (data) {
                    // console.log(data)
                    var op = "";
                    for (var i = 0; i < data.length; i++) {
                        op += '<option value="'+data[i].guruId+'">'+data[i].guruName+'</option>';
                    }
                    $("#guruId").html(op);
                }
            })
            //点击展开添加会话框
            $("#addUser").dialog("open");
        })

        $("input[value='添加']").click(function () {
            //给添加栏form表单格式化
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/article/addArticle",
                success:function (data) {
                    //console.log(data) guruId
                    if(data=="true") {
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
            width:300,
            height:260
        });

        $("input[value='修改']").click(function () {
            //给修改会话框form格式化
            $("#updateForm").form("submit",{
                url:"${pageContext.request.contextPath}/article/update",
                success:function (data) {
                    //console.log(data)
                    if(data=="true") {
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
                    ids[i] = selectRows[i].articleId;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/article/multiDelete",
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
        var articleId = rowData.articleId;
        console.log(articleId);
        //给输入框赋值
        $.ajax({
            url:"${pageContext.request.contextPath}/article/selectOne?id="+articleId,
            type:"get",
            success:function (data) {
                //console.log(data);
                var article = data.article;

                $("#articleId").val(article.articleId);
                $("#articleName").val(article.articleName);
                $("#imgId").prop("src","http://192.168.133.1:8018/server/"+article.articleImage);
                $("#articleContent").text(article.articleContent);

                var op = "";
                for (var i = 0; i < data.guruList.length; i++) {
                    op += '<option value="'+data.guruList[i].guruId+'">'+data.guruList[i].guruName+'</option>';
                }
                $("#guruId1").html(op);
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

    //搜索
    function search() {

        //console.log("+++++++++++++")
        //获取搜索参数
        var name = $("#sname").val();
        //console.log(name)
        $("#userTable").datagrid(
            {
                title:"上师文章列表",
                url:"${pageContext.request.contextPath}/article/search?keyWrod="+name,
                // data:{"keyWrod":name},
                pagination:true,
                rownumbers:true,
                columns:[[
                    {checkbox:true},
                    {field:"articleName",title:"文章名"},
                    {field:"articleContent",title:"文章内容",formatter:function (value,rowData,rowIndex) {
                            return '<textarea>'+value+'</textarea>';
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
            }
        );
    }

</script>

<body>

    <input id="uname"/><input type="button" value="搜索" onclick="sousuo()"/>&nbsp;&nbsp;
    <input id="sname"/><input type="button" value="全文检索" onclick="search()"/>
    <table id="userTable"></table>

    <%--添加按钮--%>
    <div id="myAdd">
        <a id="addButton" class="easyui-linkbutton" data-options="iconCls:'icon-add'">发布文章</a>
        <a id="removeButton" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
    </div>

    <div id="addUser">
        <form id="addForm" method="post" enctype="multipart/form-data">
            文章题目：<input name="articleName"/><br/>
            选择文件：<div class="lf salebd"><a href="#" id="preview"></a></div>
                    <input type="file" name="file" onchange="preview(this)"/><br/>
            文章内容：<textarea name="articleContent"></textarea><br/>
            上师：<select name="guruId" id="guruId">
                    <option value=""></option>
                  </select><br/>
            <input type="button" value="添加"/>
        </form>
    </div>



    <div id="updateUser">
        <form id="updateForm" method="post" enctype="multipart/form-data">
            <input id="articleId" type="hidden" name="articleId"/>
            文章题目：<input name="articleName" id="articleName"/><br/>
            选择文件：<div class="lf salebd"><a href="#" id="preview1"><img id="imgId" src="" height="45px" width="36px"/></a></div>
                      <input type="file" name="file" onchange="preview1(this)"/><br/>
            文章内容：<textarea name="articleContent" id="articleContent"></textarea><br/>
            上师：<select name="guruId" id="guruId1">
                  </select><br/>
            <input type="button" value="修改"/>
        </form>
    </div>



</body>
<script type="text/javascript">
    function preview(file) {
        var prevDiv = document.getElementById('preview');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt) {
                prevDiv.innerHTML = '<img width="36px" height="45px" src="' + evt.target.result + '" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }

    function preview1(file) {
        var prevDiv = document.getElementById('preview1');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt) {
                prevDiv.innerHTML = '<img width="36px" height="45px" src="' + evt.target.result + '" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }
</script>
</html>
