<%--
  Created by IntelliJ IDEA.
  User: 王代傲
  Date: 2020/11/4
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加员工</title>
</head>
<script src="../jquery-3.3.1.min.js"></script>
<script>
    $(document).ready(function() {
        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/queryAllByCity.do",
            success:function(data) {
                var html=" <option value='0'>请选择</option>";
                for(var i=0;i<data.length;i++){
                    html+="<option value="+data[i].cid+">"+data[i].cname+"</option>"
                }
                $("#classInfo").html(html);
            },
            error : function() {
                alert("出现错误")
            },
        });
    });
</script>
<body>
<form action="${pageContext.request.contextPath}/add.do" method="post" enctype="multipart/form-data">
    姓名：<input type="text" name="name" required><br>
    性别：男<input type="radio" name="sex" value="M" checked>女<input type="radio" name="sex" value="F"/><br>
    年龄：<input type="text" name="age" required/><br>
    城市：<select id="classInfo" name="cid"></select><br>
    日期：<input type="date" name="date" required/><br>
    图片：<input type="file" name="filename"/><br>
    <input type="submit" value="添加"/>
</form>
</body>
</html>
