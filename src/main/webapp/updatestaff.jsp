<%--
  Created by IntelliJ IDEA.
  User: 王代傲
  Date: 2020/11/5
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
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
                    var v='${staffBean.cid}'
                    if (v==data[i].cid){
                        html+="<option value="+data[i].cid+" selected>"+data[i].cname+"</option>"
                    }
                    else{
                        html+="<option value="+data[i].cid+">"+data[i].cname+"</option>"
                    }

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

<form action="/update.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="sid" value="${staffBean.sid}">
    姓名：<input type="text" name="name" value="${staffBean.name}"><br>
    性别：男<input type="radio" name="sex" ${staffBean.sex=='M' ? 'checked':''} value="M">
    女<input type="radio" name="sex" ${staffBean.sex=='F' ? 'checked':''} value="F"><br>
    年龄：<input type="text" name="age" value="${staffBean.age}"><br>
    城市：<select id="classInfo" name="cid"></select><br>
    日期：<input type="date" name="date" value="${staffBean.date}"/><br>
    图片：<input type="file" name="filename"/><br>
    <input type="submit" value="修改">
</form>
</body>
</html>
