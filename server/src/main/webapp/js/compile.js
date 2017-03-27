function compile(div_editor, div_button, div_response) {
    var editor = ace.edit(div_editor);
    $(document).ready(function () {

        $("#" + div_button).click(function () {
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
                        exec(data.name, url, div_response);
                    } else {
                        /*    var tab = data.retour.split("</br>");
                            for(var i = 0; i<tab.length; i++){
                                $("#" + div_response).text( tab[i] );
                            }*/
                        $("#" + div_response).attr('class', 'console , error');
                    }
                    $("#" + div_response).text(data.retour);

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('postUser error: ' + textStatus);
                }
            });
        })

    });
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
