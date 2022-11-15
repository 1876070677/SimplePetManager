<%@ page import="Domain.Pet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: fixer
  Date: 2022-08-30
  Time: 오후 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find</title>
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
<body>
<script>
    function submitForm() {
        if(document.findForm.query.value == "") {
            alert("검색하려는 단어가 없습니다.");
            return false;
        } else {
            document.findForm.submit();
        }
    }
    function gotomain() {
        window.location.href="/";
    }
</script>
<h2>동물 검색</h2>
<form name="findForm" action="/find" method="post">
    <input type="text" name="query">
    <input type="button" value="검색" onclick="submitForm()">
</form>
<%
    List<Pet> petList = (List<Pet>) request.getAttribute("petList");
    String resultChk = (String) request.getAttribute("result");
    int result;
    if (resultChk == null) {
        result = 0;
    } else {
        result = Integer.parseInt(resultChk);
    }
%>
<%
    if (result > 0 && petList.size() > 0) {
%>
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
<%
    }
    else if (result > 0 && petList.size() == 0) {
%>
<h5> 검색 결과가 없습니다. </h5>
<% } %>
<div>
    <input type="button" value="메인으로" onclick="gotomain()">
</div>
</body>
</html>
