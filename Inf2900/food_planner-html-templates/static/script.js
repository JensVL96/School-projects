
function upTo(element, tagName) {
    tagName = tagName.toLowerCase();
  
    while (element && element.parentNode) {
      element = element.parentNode;
    //   alert(element.className);
      if (element.className && element.className.toLowerCase() == tagName) {
        return element;
      }
    }
    return null;
}

function findSibling(element, tagName) {
    tagName = tagName.toLowerCase();
    var parent = element.parentNode;
  
    for (var j = 0; j < parent.childNodes.length; j++) {
        var child = parent.childNodes[j]
        if (child.className == tagName) {
            return child;
        }
    }
    return null;
}

function updateButton(tableID, posID) {
    var table = document.getElementById(tableID);
    var rowCnt = table.rows.length;

    alert("going in");
    var element = document.getElementById(posID);
    var btnID = upTo(element, "row").id;
    alert(btnID);
}

var i = 0;

function buttonINC(element) {
    var input = findSibling(element, "quantity btn-sm");

    if (input.value > 98) {
        element.className = "button_disable";
    } else {
        input.value++;
    }
}

function buttonDEC(element) {
    var input = findSibling(element, "quantity btn-sm");

    if (input.value == 1) {
        element.className = "button_disable";
    } else {
        input.value--;
    }
}

function addRow(tableID) {
    var table = document.getElementById(tableID);
    // rowID = document.getElementById("adjust").parentNode.parentNode.parentNode.parentNode.parentNode.id
    // alert(rowID);

    var rowCnt = table.rows.length;
    var row = table.insertRow(rowCnt);
    row.id = rowCnt;
    row.className = "row";/*  + row.id; */

    // document.getElementById("btnID").value = rowCnt;
    
    var cell1 = row.insertCell(0);
    cell1.innerHTML = document.getElementById("item1").innerHTML;
    cell1.className = "row-item";
    cell1.id = "item1";

    var cell2 = row.insertCell(1);
    cell2.innerHTML = document.getElementById("item2").innerHTML;
    cell2.className = "row-item";
    cell2.id = "item2";

    var cell3 = row.insertCell(2);
    cell3.innerHTML = document.getElementById("item3").innerHTML;
    cell3.className = "row-item";
    cell3.id = "item3";

    var cell4 = row.insertCell(3);
    cell4.innerHTML = document.getElementById("item4").innerHTML;
    cell4.className = "row-item";
    cell4.id = "item4";
    // var element4 = document.getElementById("item4");
    // cell4.appendChild(element4);
}

function deleteRow(tableID, element) {
    try {
        var table = document.getElementById(tableID);
        var rowCnt = table.rows.length;

        if(rowCnt == 1) {
            alert("Cannot delete last row");
            return;
        }

        // var row = table.closest("tr");
        var rownr = upTo(element, "row");
        var dltrow = rownr.id;
        // alert(dltrow);

        table.deleteRow(dltrow);
        rowCnt--;

    } catch(e) {
        alert(e);
    }
}
