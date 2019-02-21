
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="../themes/default/easyui.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#lessonGrid").datagrid({
                url: "${pageContext.request.contextPath}/lesson/selectByPage",
                pagination: true,
                fitColumns: true,
                loadMsg: "正在为您加载中...",
                pageSize: 10,
                pageList: [5, 10, 15],
                toolbar: "#lessonTools",
                striped: true,

                columns: [[
                    {field: "cb", checkbox: true},
                    {title: "编号", field: "lessonId", width: 150},
                    {title: "名称", field: "lessonName", width: 200},

                    {title: "用户名", field: "name", width: 300 ,
                        formatter:function (value , row , index) {

                        console.log(row.userId)
                            if (row.userId != null) {
                                return row.user.name;
                            } else {
                                return null;
                            }
                        }
                    }
                ]]

            });

            $("#insertLessonBtn").linkbutton();



            $("#addLessonForm").form({
                url:"${pageContext.request.contextPath}/lesson/addLesson",
                success:function(data){
                    if(data){
                        $("#addLessonDialog").dialog("close");
                        $("#lessonGrid").datagrid("reload");
                        $.messager.alert("添加提示框","添加成功","info");
                    }else{
                        $.messager.alert("添加提示框","添加失败，请确认","warning");
                    }
                }
            });


        });

        function toOpenAddDialog() {
            $("#addLessonDialog").dialog("open");
        }

        function doInsertLesson() {
            $("#addLessonForm").form("submit");
        }

        function doSearch() {
            var name = $("#name").val();
            $("#lessonGrid").datagrid("load",{"lessonName":name});
        }

        function doMulDeleteProduct() {
            var selectedRows = $("#lessonGrid").datagrid("getSelections");
            if(selectedRows.length==0){
                $.messager.alert("删除提示框","请选中要删除的内容","info");
            }else{
                $.messager.confirm("删除确认框","请确认是否要删除选中的内容",function(result){
                    if(result){
                        var selectedId = new Array();
                        for(var i=0;i<selectedRows.length;i++){
                            selectedId[i]=selectedRows[i].lessonId;
                        }
                        $.ajax({
                            url:"${pageContext.request.contextPath}/lesson/multiDelete",
                            data:"ids="+selectedId,
                            success:function(data){
                                if(data){
                                    $.messager.alert("提示框","删除成功","info");
                                    $("#lessonGrid").datagrid("reload")
                                }else{
                                    $.messager.alert("提示框","删除失败，请确认","warning");
                                }

                            }
                        })
                    }
                });
            }
        }
    </script>
</head>
<body>
<table id="lessonGrid"></table>
<div id="lessonTools">
    <input id="name" name="keyword"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="toOpenAddDialog()">添加</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="doMulDeleteProduct()">批量删除</a>
</div>

<div id="addLessonDialog" class="easyui-dialog" data-options="closed:true" style="width: 400px;height: 200px">
    <form id="addLessonForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    功课名称：
                </td>
                <td>
                    <input type="text" name="lessonName" placeholder="请输入功课名称">
                </td>
            </tr>

            <tr>
                <td colspan="2"><a id="insertLessonBtn" onclick="doInsertLesson()">添加</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
