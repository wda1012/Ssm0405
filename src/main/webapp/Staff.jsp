<%--
  Created by IntelliJ IDEA.
  User: 王代傲
  Date: 2020/11/3
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工信息管理</title>
</head>
<script src="../jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    function xuanze(flag) {
        $('input[name=ids]').each(
            function () {
                $(this).prop('checked',flag);
            }
        )
    }
</script>

<body>

<form action="/modelAndView.do">
    员工查询：<input type="text" name="search" placeholder="姓名,年龄,城市模糊查询">
    <input type="submit" value="查询">
</form>
<a href="/addStaff.jsp"><button>添加员工</button></a>

<form action="/delDataFilesByIds.do" method="post">
    <input type="submit" value="批量删除">
<table border="1">
    <tr>
        <td><input type="checkbox"  onclick="xuanze(this.checked)"></td>
        <td>序号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>城市</td>
        <td>入职日期</td>
        <td>图片</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${page.records}" var="list" varStatus="lists">
        <tr>
            <td><input type="checkbox" id="ids" name="ids" value="${list.sid}"></td>
            <td>${lists.index+1}</td>
            <td>${list.name}</td>
            <td>${list.sex=='F'?'女':'男'}</td>
            <td>${list.age}</td>
            <td>${list.cityBean.cname}</td>
            <td>${list.date}</td>
            <td><img src="/${list.img}" width="80px" height="50px"></td>
            <td><a href="${pageContext.request.contextPath}/delete.do?sid=${list.sid}">删除</a>|<a href="/queryId.do?sid=${list.sid}">修改</a></td>
        </tr>
    </c:forEach>
</table>
</form>
        <a href="${pageContext.request.contextPath}/modelAndView.do?page=1">首页</a>

        <c:if test="${(page.current-1)!=0}">
            <a href="${pageContext.request.contextPath}/modelAndView.do?page=${page.current-1}">上一页</a>
        </c:if>

        <c:if test="${page.current!=pages}">
            <a href="${pageContext.request.contextPath}/modelAndView.do?page=${page.current+1}">下一页</a>
        </c:if>

        <a href="${pageContext.request.contextPath}/modelAndView.do?page=${pages}">尾页</a>

        一共有 ${pages} 页

        当前在 ${page.current} 页
</body>
</html>
