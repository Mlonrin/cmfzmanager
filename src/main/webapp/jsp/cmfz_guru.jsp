
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/themes/default/easyui.css" type="text/css">

</head>
<script>
    $(function () {
        //给添加栏格式，并隐藏
        $("#addDiv").dialog({
            title:"上传大师",
            closed:true,
            width:350,
            height:200
        });

        $("input[value='上传大师']").click(function () {
            $("#addDiv").dialog("open");
        })

        $("input[value='添加上传']").click(function () {
            //给添加栏form表单格式化
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/guru/upLoad",
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

    })

</script>
<body>

    <div id="guruTools">
        <input id="name" name="guruName"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="toOpenAddDialog()">添加</a>

    </div>

    <div id="addDiv">
        <form id="addForm" method="post" enctype="multipart/form-data">
            选择文件：<input type="file" name="file"/><br/>
            <input type="button" value="添加上传"/>
        </form>
    </div>

    <table id="myTable"></table>
    <a href="${pageContext.request.contextPath}/guru/downLoad">文件下载</a>&nbsp;&nbsp;
    <input type="button" value="上传大师"/><br/>

    <div id="addGuruDialog" class="easyui-dialog" data-options="closed:true" style="width: 400px;height: 200px">
        <form id="addGuruForm" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        名称：
                    </td>
                    <td>
                        <input type="text" name="guruName" placeholder="请输入上师名称">
                    </td>
                </tr>
                <tr>
                    <td>
                        图片：
                    </td>
                    <td>
                        <input type="file" name="myFile">
                    </td>
                </tr>
                <tr>
                    <td>
                        内容：
                    </td>
                    <td>
                        <input type="text" name="guruNickname" placeholder="请输入内容">
                    </td>
                </tr>

                <tr>
                    <td colspan="2"><a id="insertGuruBtn" onclick="doInsertGuru()">添加</a></td>
                </tr>
            </table>
        </form>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#myTable").datagrid({
                url: "${pageContext.request.contextPath}/guru/selectByPage",
                pagination: true,
                fitColumns: true,
                loadMsg: "正在为您加载中...",
                pageSize: 10,
                pageList: [5, 10, 15],
                striped: true,
                
                onLoadSuccess: function (data) {
                    $("[name=changeGuruBtn]").linkbutton();
                    $("[name=changeGuruBtn]").bind("click",function () {
                        doChangeGuru();
                    });
                },
                
                onDblClickRow:function(rowIndex, rowData){
                    toOpenUpdateDialog(rowIndex,rowData);
                },
                columns: [[
                    {field: "cb", checkbox: true},
                    {title: "编号", field: "guruId", width: 150},
                    {title: "名称", field: "guruName", width: 150},
                    {title: "上师头像", field: "guruImage", width: 150 ,
                        formatter:function (value, row, index) {
                            return "<img style='width:24px;height:24px;' border='1' src='http://192.168.133.1:8018/server/"+value+"'/>"
                        }
                    },
                    {title: "上师真实姓名", field: "guruNickname", width: 150},
                    {title: "上师状态", field: "guruStatus", width: 150 ,
                        formatter:function (value , row , index) {
                            if (value == 1) {
                                return "冻结"
                            } else {
                                return "正常"
                            }
                        }
                    },

                    {title: "操作", field: "cz",width: 200,
                        formatter: function (value, row, index) {
                            var status = "冻结";
                            if (row.guruStatus==1){
                                status = "解冻";
                            }
                            return "<a name='changeGuruBtn'>"+status+"</a>"
                        }
                    }

                ]]

            });



            $("#addGuruForm").form({
                url:"${pageContext.request.contextPath}/guru/addGuru",
                success:function(data){
                    if(data){
                        $("#addGuruDialog").dialog("close");
                        $("#myTable").datagrid("reload");
                        $.messager.alert("添加提示框","添加成功","info");
                    }else{
                        $.messager.alert("添加提示框","添加失败，请确认","warning");
                    }
                }
            });

            $("#insertGuruBtn").linkbutton();

        });


        function toOpenAddDialog() {
            $("#addGuruDialog").dialog("open");
        }

        function doInsertGuru() {
            $("#addGuruForm").form("submit");
        }



        function doSearch() {
            var name = $("#name").val();
            $("#myTable").datagrid("load",{"name":name});
        }





        function doChangeGuru(change) {
            $.messager.confirm("确认框", "请确认是否要改变状态", function (result) {
                var changeRowData = $("#myTable").datagrid("getSelected");
                var status = 1;
                if(changeRowData.guruStatus==1){
                    status = 0;
                }
               
                if (result) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/guru/updateStatus",
                        data: "status=" + status + "&id="+changeRowData.guruId,
                        success: function (data) {
                            if (data) {
                                $.messager.alert("提示框", "变更成功", "info");
                                $("#myTable").datagrid("reload")
                            } else {
                                $.messager.alert("提示框", "变更失败，请确认", "warning");
                            }

                        }
                    })
                }
            });
        }

    </script>
</body>
</html>
