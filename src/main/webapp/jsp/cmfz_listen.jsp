<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>

    <link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>

</head>

<script type="text/javascript">

    $(function () {

        $('#tt').treegrid({
            url:'${pageContext.request.contextPath}/album/selectAll',
            idField:'albumId',
            treeField:'albumName',
            columns:[[
                {title:'音频名',field:'albumName',width:180},
                {title:'顺序',field:'audioOrder',width:180},
                {title:'大小',field:'audioSize',width:180}
                /*{title:'朗读者',field:'albumTeller',width:180},
                {title:'集数',field:'albumEpisodes',width:180},*/
            ]],
            toolbar:[{
                text: "专辑详情",
                iconCls: 'icon-edit',
                handler: function () {
                    // 1.通过getSelect方法获取被选中行  可以获取到id
                    var row = $('#tt').treegrid("getSelected");
                    //console.log(row.albumAuthor);
                    //console.log(row);
                    // 2.通过id查询到对应的数据
                    openShow(row);
                    // 3.打开一个对话框显示被选中的数据
                }
            }, '-', {
                text: "添加专辑",
                iconCls: 'icon-help',
                handler: function () {
                    //alert('请自己写')
                    $("#addUser").dialog("open");
                }
            }, '-', {
                text: "添加章节",
                iconCls: 'icon-help',
                handler: function () {
                    // 1.获取到行
                    // 2.判断是专辑还是音频
                    $("#addDiv2").dialog("open");
                    $.ajax({
                        type: 'GET',
                        url: '${pageContext.request.contextPath}/album/selectAll',
                        dataType:'json',
                        async: false,
                        timeout: 2000,
                        success: function (data) {
                            $("#cid").html("");
                            for (var i = 0; i < data.length; i++) {

                                $("#cid").append("<option value='"+data[i].albumId+"'>" + data[i].albumName + "</option>");
                            };
                            $("#cid").append("<option value='0'  selected='selected'>全部</option>");
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("获取机构类型数据失败！");
                        }
                    })
                }
            }],
            onDblClickRow:function (row) {
                // 1.使用双击方法  能够获取被双击行的信息 例如拿到链接
                $("#audio_dd").dialog("open")
                console.log(row)
                // 2.打开一个对话框 对话框中只有audio标签  更改标签的src属性为音频url
                $("#audio_id").prop("src","http://192.168.133.1:8018/server/"+row.audioUrl);
            }
        });

        $("#addDiv2").dialog({
            title: "add",
            closed: true,
            width: 500,
            height: 430,
            modal: true
        });

        //修改部分=========================================================开始

        //给修改会话框格式并隐藏
        $("#show").dialog({
            title:"修改用户",
            closed:true,
            width:260,
            height:300
        });

        $("input[value='修改']").click(function () {
            //给修改会话框form格式化

            $("#showForm").form("submit",{
                url:"${pageContext.request.contextPath}/album/update",
                success:function (data) {
                    //console.log(data)
                    if(data) {
                        //alert("修改成功");
                        //关闭修改会话框
                        $("#show").dialog("close");
                        //刷新
                        $("#userTable").datagrid("reload");
                    }else{
                        $.messager.alert("修改提示","修改失败","warning");
                    }
                }
            })
        })

        //修改结束=============================================================

        //添加开始======================================================

        //给添加栏格式，并隐藏
        $("#addUser").dialog({
            title:"添加用户",
            closed:true,
            width:300,
            height:400
        });

        $("input[value='添加专辑']").click(function () {
            //给添加栏form表单格式化
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/album/addAlbum",
                success:function (data) {
                    //console.log(data) guruId
                    if(data) {
                        //添加成功并关闭添加会话框，并刷新
                        $("#addUser").dialog("close");
                        $("#tt").datagrid("reload");
                    }else{
                        //console.log(data)
                        $.messager.alert("添加提示信息","添加失败","error");
                    }
                }
            })
        })

        // 添加结束====================================================


        $("#addFormBtn2").click(function () {
            console.log("+++++++++++++++++++++++++++++++++")
            $("#addForm2").form("submit", {
                url: "${pageContext.request.contextPath}/audio/addAudio",
                success: function (data) {
                    if (data) {
                        $.messager.alert("添加提示", "添加成功!", "info");
                        $("#addDiv2").dialog("close");
                        $("#showTable").treegrid("reload");
                    } else {
                        $.messager.alert("添加提示", "添加失败!", "warning");
                    }
                }
            })
        });

    })
    
    function openShow(rowData) {

        $("#show").dialog("open");

        var albumId = rowData.albumId;
        //console.log(albumId);
        //给输入框赋值
        $.ajax({
            url:"${pageContext.request.contextPath}/album/selectOne?id="+albumId,
            type:"get",
            success:function (data) {
                console.log(data);

                $("#albumId").val(data.albumId);
                $("#albumName").val(data.albumName);
                $("#albumAuthor").val(data.albumAuthor);
                $("#albumTeller").val(data.albumTeller);
                $("#albumEpisodes").val(data.albumEpisodes);
                $("#albumDate").val(data.albumDate);
                $("#imgId").prop("src","http://192.168.133.1:8018/server/"+data.albumImage);
                $("#albumContent").text(data.albumContent);
                $("#albumStar").val(data.albumStar);
            }
        })
    }



</script>

<body>


    <table id="tt"></table>


    <div id="audio_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
        <audio id="audio_id" src="" autoplay="autoplay" controls="controls"></audio>
    </div>

    <div id="show">
        <form id="showForm" method="post" enctype="multipart/form-data">
            <input id="albumId" type="hidden" name="albumId"/>
            专辑名：<input name="albumName" id="albumName"/><br/>
            专辑作者：<input name="albumAuthor" id="albumAuthor"/><br/>
            朗读者：<input name="albumTeller" id="albumTeller"/><br/>
            集数：<input name="albumEpisodes" id="albumEpisodes"/><br/>
            发布日期：<input name="albumDate1" id="albumDate"/><br/>
            专辑描述：<textarea name="albumContent" id="albumContent"></textarea><br/>
            专辑封面：<div class="lf salebd"><a href="#" id="preview"><img id="imgId" src="" height="45px" width="36px"/></a></div>
            <input type="file" name="file" onchange="preview(this)"/><br/>
            评分：<input name="albumStar" id="albumStar"/><br/>
            <input type="button" value="修改"/>
        </form>
    </div>

    <div id="addUser">
        <form id="addForm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="albumId"/>
            专辑名：<input name="albumName" /><br/>
            专辑作者：<input name="albumAuthor" /><br/>
            朗读者：<input name="albumTeller" /><br/>
            集数：<input name="albumEpisodes" /><br/>
            专辑描述：<textarea name="albumContent" ></textarea><br/>
            专辑封面：<div class="lf salebd"><a href="#" id="preview1"></a></div>
            <input type="file" name="file" onchange="preview1(this)"/><br/>
            评分：<input name="albumStar" /><br/>
            <input type="button" value="添加专辑"/>
        </form>
    </div>

    <div id="addDiv2">
        <form id="addForm2" method="post" enctype="multipart/form-data">
            <table align="center" cellpadding="10">
                <tr>
                    <th>音频名称:</th>
                    <td><input type="text" name="audioName"></td>
                </tr>
                <tr>
                    <th>所属专辑:</th>
                    <td><select id="cid" name="albumId"></select></td>
                </tr>

                <tr>
                    <th>排序:</th>
                    <td><input type="text" name="audioOrder" value="0"></td>
                </tr>

                <tr>
                    <th>音频文件:</th>
                    <td><input type="file" name="myFile"/></td>
                </tr>

                <tr>
                    <th>大小:</th>
                    <td><input type="text" name="audioSize" value="0"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" id="addFormBtn2" value="添加"/></td>
                </tr>
            </table>
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
