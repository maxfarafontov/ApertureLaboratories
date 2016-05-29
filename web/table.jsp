<%@ page import="DAO.Dao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Database</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">

</head>
<body>

<%
    request.setAttribute("users", new Dao().getPersons());
%>

    <h2 align="center">Table of users</h2>



<button class="pure-button pure-button-primary"
        onclick="addRow('dataTable'); this.disabled = true">Log in
</button>

    <div class="pure-control-group">
        <button class="pure-button pure-button-primary"
                onclick="addRow('dataTable'); this.disabled = true">Add Row
        </button>

        <a id="deleteURL" href="/?action=delete&id=">
            <button class="pure-button pure-button-primary"
                    onclick="deleteRow('dataTable')">Delete Row
            </button>
        </a>
    </div>
    <br>
    <table id="dataTable" class="pure-table pure-table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Age</th>
            <th>Passport Series</th>
            <th>Passport Number</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="User">
            <tr>
                <td><INPUT type="checkbox" onchange="test(this)" name="chk"/>
                    <input type="text" hidden disabled value=<c:out value="${User.id}"/>>
                <td><c:out value="${User.name}"/></td>
                <td><c:out value="${User.surname}"/></td>
                <td><c:out value="${User.email}"/></td>
                <td><c:out value="${User.age}"/></td>
                <td><c:out value="${User.getpassSeries()}"/></td>
                <td><c:out value="${User.getpassNumb()}"/></td>
                <td><a href="/?action=edit&id=<c:out value="${User.id}"></c:out>">
                    <button class="pure-button pure-button-primary">Edit</button>
                </a>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<SCRIPT>
    function addRow(tableID) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var colCount = table.rows[0].cells.length;
        for (var i = 0; i < colCount; i++) {
            var cell = row.insertCell(i);
            switch (i) {
                case 0:
                    cell.innerHTML = '<input type = "checkbox" onchange="add_imagine()" name = "checkbox">';
                    break;
                case 1:
                    cell.innerHTML = '<input type= "text"  id="name" name= "name" onchange=checkAndParams()>';
                    break;
                case 2:
                    cell.innerHTML = '<input type= "text"  id="surname" name="surname" onchange=checkAndParams()>';
                    break;
                case 3:
                    cell.innerHTML = '<input type= "text" id = "email" name="email" onchange=checkAndParams()>';
                    break;
                case 4:
                    cell.innerHTML = '<input type= "text" maxlength="3" size="3" id="age" name= "age" onchange=checkAndParams()>';
                    break;
                case 5:
                    cell.innerHTML = '<input type= "text" maxlength="4" size="4" id="series" name= "series" onchange=checkAndParams()>';
                    break;
                case 6:
                    cell.innerHTML = '<input type= "text" maxlength="6" size="6" id="number" name= "number" onchange=checkAndParams()>';
                    break;
                case 7:
                    cell.innerHTML = '<a id="url" href="/?action=save"> <button id="saveBtn" disabled class="pure-button pure-button-primary">Save</button> </a>';
                    break;
            }
        }
    }
    function deleteRow(tableID) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if (null != chkbox && true == chkbox.checked) {
                if (rowCount <= 1) {
                    alert("Cannot delete all the rows.");
                    break;
                }
                if (i == (rowCount - 1)) {
                    document.getElementById("addBtn").disabled = false;
                }
                alert(document.getElementsByName("id")[i - 1].value) // returning id
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    }
    function check_string(element) {
        var regexp = /^[a-zA-Z]+$/;
        if (element.value != "" && element.value.match(regexp)) {
            element.style.borderColor = "";
            return true;
        } else {
            element.style.borderColor = "E05858";
            return false;
        }
    }
    function check_age(element) {
        if (element.value.length > 1 && element.value.length < 3 && isInt(element.value) && !(element.value == "")) {
            element.style.borderColor = "";
            return true;
        } else {
            element.style.borderColor = "E05858";
            return false;

        }
    }
    function isInt(value) {
        var x;
        return isNaN(value) ? !1 : (x = parseFloat(value), (0 | x) === x);
    }
    function check_series(element) {
        if (element.value.length == 4 && isInt(element.value) && !(element.value == "")) {
            element.style.borderColor = "";
            return true;
        } else {
            element.style.borderColor = "E05858";
            return false;
        }
    }
    function check_email(element) {
        var emailvalid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (element.value.match(emailvalid) && !(element.value == "")) {
            element.style.borderColor = "";
            return true;
        } else {
            element.style.borderColor = "E05858";
            return false;
        }
    }
    function check_numb(element) {
        if (element.value.length == 6 && isInt(element.value) && !(element.value == "")) {
            element.style.borderColor = "";
            return true;
        } else {
            element.style.borderColor = "E05858";
            return false;
        }
    }
    function add_params(element) {
        document.getElementById("url").href += "&" + element.name + "=" + element.value;
    }
    function checkAndParams() {
        var name = document.getElementById("name");
        var surname = document.getElementById("surname");
        var email = document.getElementById("email");
        var age = document.getElementById("age");
        var series = document.getElementById("series");
        var numb = document.getElementById("number");
        if (check_string(name) && check_string(surname) && check_email(email)
                && check_age(age) && check_series(series) && check_numb(numb)) {
            document.getElementById("saveBtn").disabled = false;
            add_params(name);
            add_params(surname);
            add_params(email);
            add_params(age);
            add_params(series);
            add_params(numb);
        }

    }

    function test(element) {
        var x = element.nextElementSibling;
        var url = document.getElementById("deleteURL");
        var s = x.value.toString();
        if (url.href.indexOf(x.value) == -1) {
                url.href += " " + s;
        } else {
            if (url.href.indexOf(x.value) > 0) {
                url.href = url.href.replace(new RegExp(x.value), " ");
            }
        }

    }

    function add_imagine(){
        document.getElementById("deleteURL").href +=" 001";
    }
</SCRIPT>
</body>
</html>