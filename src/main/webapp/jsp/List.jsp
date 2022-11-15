<%@ page import="java.util.List" %>
<%@ page import="Domain.Pet" %><%--
  Created by IntelliJ IDEA.
  User: fixer
  Date: 2022-08-30
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<style>
    table {
        border: 1px solid #444444;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #444444;
    }
</style>
<body onload="selectCombo()">
<script>
    function selectFilter() {
        filterNum = document.getElementById("filter").value;
        path = "/list?filter=" + filterNum;
        window.location.href=path;
    }

    function gotomain() {
        window.location.href="/";
    }
</script>
<%
    List<Pet> petList = (List<Pet>) request.getAttribute("petList");
    int filter = (int) request.getAttribute("filter");
%>
<script>
    function selectCombo() {
        const combo = document.getElementById("filter");
        const len = combo.options.length;

        for (let i = 0; i < len; i++) {
            if (combo.options[i].value == <%= filter %>) {
                console.log(combo.options[i].value)
                combo.options[i].selected = true;
            }
        }
    }
</script>
<h2>동물 목록 조회</h2>
<div>
    <select id="filter" onchange="selectFilter()">
        <option value="0">등록일순</option>
        <option value="1">동물이름순</option>
        <option value="2">주인이름순</option>
    </select>
</div>
<table style="border: 1px solid black;">
    <tr>
        <th>동물 이름</th>
        <th>생년월일</th>
        <th>품종</th>
        <th>주인 이름</th>
        <th>핸드폰 번호</th>
    </tr>
    <%
        for (int i = 0 ; i < petList.size(); i++) {
    %>
    <tr>
        <th><%= petList.get(i).getName() %></th>
        <th><%= petList.get(i).getBirth() %></th>
        <th><%= petList.get(i).getKind() %></th>
        <th><%= petList.get(i).getOwnerName() %></th>
        <th><%= petList.get(i).getOwnerPhone() %></th>
    </tr>
    <% } %>
</table>
<div>
    <input type="button" value="메인으로" onclick="gotomain()">
</div>
</body>
</html>
