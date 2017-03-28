var oolean;
function compile(div_editor, div_button, div_response, div_tests, next_button) {
    var editor = ace.edit(div_editor);

    $(document).ready(function () {

            console.log(editor.getValue());
            var url = "../v1/exercice";
            console.log("postUserGeneric " + url)
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: url,
                dataType: "json",
                data: JSON.stringify({
                    "code": editor.getValue()
                }),
                success: function (data, textStatus, jqXHR) {
                    
                    if (data.retour == "Compilation Successful !") {
                        $("#" + div_response).attr('class', 'console , valid');
                        tests(data.name, url, div_response, div_tests, next_button);
                        $("#" + div_response).text(data.retour);
                        
                        oolean = true;
                    } else {
                        $("#" + div_response).attr('class', 'console , error');
                        $("#" + div_response).text(data.retour);
                        
                        oolean = false;
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('postUser error: ' + textStatus);
                    oolean = false;
                }
        })

    });
    return oolean;
}

function compil_exec(div_editor, div_response) {
    var editor = ace.edit(div_editor);

    $(document).ready(function () {

            console.log(editor.getValue());
            var url = "../v1/exercice";
            console.log("postUserGeneric " + url)
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: url,
                dataType: "json",
                data: JSON.stringify({
                    "code": editor.getValue()
                }),
                success: function (data, textStatus, jqXHR) {
                    
                    if (data.retour == "Compilation Successful !") {
                        exec(data.name, url, div_response);
                        
                    } else {
                        $("#" + div_response).attr('class', 'console , error');                        
                    }
                    $("#" + div_response).text(data.retour);

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('postUser error: ' + textStatus);
                }
        })

    });
}


function exec(name, url, div_reponse){
    $.ajax({
       type: 'GET',
        url: url,
        dataType: "json",
        success: function (data) {
            $('#' + div_reponse).text("");
            for(var i = 0; i<data.retour.length; i++){
                console.log(data.retour[i]);
                $('#' + div_reponse).text($('#' + div_reponse).text() +"\n"+ data.retour[i]);
            }
       },
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}        
    });
}

function tests(name, url, div_reponse, div_tests, next_button){
    $.ajax({
       type: 'GET',
        url: url+"/tests",
        dataType: "json",
        success: function (data) {
            
                for(var i = 0; i<data.retour.length; i++){
                console.log(data.retour[i]);
                $('#' + div_reponse).text(data.retour[i]);
                }
                if(!testOK($('#' + div_reponse).text())){
                   $('#' + div_reponse).attr("class", 'console , error')
                }
                if(testOK($('#' + div_reponse).text()) && testOK($('#' + div_tests).text())){
                    $('#'+next_button).attr("class", "button btn-action");
                }
            
            
       },
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}        
    });
}

function testOK(tests){
    var tab = tests.split('%');
    var pourcentage = tab[0].substring(tab[0].length - 3);
    if(pourcentage == "100"){
        return true;
    }else return false;
}
  function jsontoTable(text) {
                    var col = [];
                    for (var i = 0; i < text.length; i++) {
                        for (var key in text[i]) {
                            if (col.indexOf(key) === -1) {
                                col.push(key);
                            }
                        }
                    }
                    var thead = document.createElement("thead");
                    var table = document.createElement("table");
                    table.appendChild
                    var tr = table.insertRow(-1); // TABLE ROW.
                    var cpt = 0;
                    for (var i = 0; i < col.length; i++) {
                        if (col[i] == "Tests") {
                            cpt = i;
                        }
                        var th = document.createElement("th"); // TABLE HEADER.
                        th.innerHTML = col[i];
                        tr.appendChild(th);
                    }
                    thead.appendChild(tr);
                    var tbody = document.createElement("tbody");
                    for (var i = 0; i < text.length; i++) {

                        tr = tbody.insertRow(-1);

                        for (var j = 0; j < col.length; j++) {
                            var tabCell = tr.insertCell(-1);
                            if (cpt == j) {
                                tabCell.innerHTML = "<div class=\"progress\"><div class=\"progress-bar\" role=\"progressbar\" style=\"width: " + (text[i][col[j]]) + "%\"></div></div>";
                            } else {

                                tabCell.innerHTML = text[i][col[j]];
                            }
                        }
                    }
                    var divContainer = document.getElementById("tableau");
                    divContainer.innerHTML = "";
                    divContainer.appendChild(thead);
                    divContainer.appendChild(tbody);

                }
function statsEtu(){
            var url = "../v1/stats";
                $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: url,
                dataType: "json",
                data: JSON.stringify({
                    "login": "belsa"
                }),
                success: function (data, textStatus, jqXHR) {
                    
                    if (data.categ != "") {
                       jsontoTable(data);
                        
                    } else {
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('postUser error: ' + textStatus);
                }
        })

}
