<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--远程请求js--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>



</body>

<script>
    var goEasy = new GoEasy({
        appkey: "BS-c63779afdd1a4c0a9211648876b201a0"
    });

    goEasy.subscribe({
        channel: "testChannel",
        onMessage: function (message) {
            console.log("Channel:" + message.channel + " content:" + message.content);
        }
    });

</script>
</html>
