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

function exec(name, url, div_reponse){
    $.ajax({
       type: 'GET',
        url: url,
        dataType: "json",
        success: function (data) {
            for(var i = 0; i<data.retour.length; i++){
                console.log(data.retour[i]);
                $('#' + div_reponse).text(data.retour[i]);
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
