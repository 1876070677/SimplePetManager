<%--
  Created by IntelliJ IDEA.
  User: fixer
  Date: 2022-08-30
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<script>
    function checkform() {
        // 오늘 날짜와 동물의 생년월일을 비교해서 오늘 이후는 선택할 수 없도록 한다.
        today = new Date();
        year = today.getFullYear();
        month = today.getMonth() + 1;
        if (month < 10)
            month = "0" + (today.getMonth() + 1)
        date = today.getDate();
        if (date < 10)
            date = "0" + today.getDate();
        now = new Date(year + "-" + month + "-" + date);
        pick = new Date(document.registerForm.petBirth.value);

        if (document.registerForm.petName.value == "") {
            alert("동물 이름을 입력해주세요.");
            return false;
        }
        else if (document.registerForm.petBirth.value > today.toString()) {
            alert("잘못된 날짜가 선택되었습니다.")
            return false;
        }
        else if (document.registerForm.petBirth.value == "") {
            alert("생년월일을 선택해주세요.");
            return false;
        }
        else if (document.registerForm.kind.value == "") {
            alert("품종을 입력해주세요.")
            return false;
        }
        else if (document.registerForm.ownerName.value == "") {
            alert("주인 이름을 입력해주세요.")
            return false;
        }
        else if (document.registerForm.ownerPhone.value == "") {
            alert("주인 연락처를 입력해주세요.");
            return false;
        }
        else if (pick.getTime() > now.getTime()) {
            alert("잘못된 날짜를 선택하였습니다.");
        }
        else
            document.registerForm.submit();
    }

    function gotomain() {
        window.location.href="/";
    }
</script>
<h2>회 원 가 입</h2>
<form name="registerForm" action="/register" method="post" accept-charset="utf-8">
    <div>
        동물 이름 : <input type="text" name="petName" />
    </div>
    <div>
        동물 생년월일 : <input type="date" name="petBirth" maxDate/>
    </div>
    <div>
        동물 품종 : <input type="text" name="kind" />
    </div>
    <div>
        주인 이름 : <input type="text" name="ownerName" />
    </div>
    <div>
        주인 연락처 : <input type="number" name="ownerPhone" placeholder="-를 빼고 입력해주세요."/>
    </div>
    <%
        String res = request.getParameter("res");
        if (res.equals("fail")) {
    %>
    <div>
        <h5>주인 연락처가 이미 존재하나, 주인 이름과 맞지 않습니다.</h5>
    </div>
    <% } %>
    <div>
        <input type="button" value="등록" onclick="checkform()">
        <input type="button" value="메인으로" onclick="gotomain()">
    </div>
</form>
</body>
</html>
