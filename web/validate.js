function addRow(tableID) {

    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var colCount = table.rows[0].cells.length;

    for (var i = 0; i < colCount; i++) {

        var newcell = row.insertCell(i);

        newcell.innerHTML = table.rows[0].cells[i].innerHTML;
        //alert(newcell.childNodes);
        switch (newcell.childNodes[0].type) {
            case "text":
                newcell.childNodes[0].value = "";
                break;
            case "checkbox":
                newcell.childNodes[0].checked = false;
                break;
            case "select-one":
                newcell.childNodes[0].selectedIndex = 0;
                break;
        }
    }
}

function deleteRow(tableID) {
    try {
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
                table.deleteRow(i);
                rowCount--;
                i--;
            }


        }
    } catch (e) {
        alert(e);
    }
}

function myFunction() {
    var str = "Back to main table";
    var result = str.link("/");
    document.getElementById("demo").innerHTML = result;
}
function check_string(element) {
    var regexp = /^[a-zA-Z]+$/;
    if (element.value == "" || !element.value.match(regexp) ) {
        document.getElementById("submit").disabled = true;
        element.style.borderColor = "E05858";
    } else {
        document.getElementById("submit").disabled = false;
        element.style.borderColor = "";
    }
}
function check_age(element) {
    if (element.value.length > 2 || !isInt(element.value)) {
        document.getElementById("submit").disabled = true;
        element.style.borderColor = "E05858";
    } else {
        document.getElementById("submit").disabled = false;
        element.style.borderColor = "";
    }
}
function isInt(value) {
    var x;
    return isNaN(value) ? !1 : (x = parseFloat(value), (0 | x) === x);
}
function check_series(element) {
    if (element.value.length == 4 && isInt(element.value)) {
        document.getElementById("submit").disabled = false;
        element.style.borderColor = "";
    } else {
        document.getElementById("submit").disabled = true;
        element.style.borderColor = "E05858";
    }
}
function check_email(element) {
    var emailvalid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (element.value.match(emailvalid)) {
        document.getElementById("submit").disabled = false;
        element.style.borderColor = "";
    } else {
        document.getElementById("submit").disabled = true;
        element.style.borderColor = "E05858";
    }
}
function check_numb(element){
    if (element.value.length == 6 && isInt(element.value)) {
        document.getElementById("submit").disabled = false;
        element.style.borderColor = "";
    } else {
        document.getElementById("submit").disabled = true;
        element.style.borderColor = "E05858";
    }
}

function check(){
    check_string(document.getElementById("name"));
    check_string(document.getElementById("surname"));
    check_age(document.getElementById("age"));
    check_email(document.getElementById("email"));
    check_series(document.getElementById("series"));
    check_numb(document.getElementById("numb"))
}