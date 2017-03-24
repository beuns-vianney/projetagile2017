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
                data: JSON.stringify({
                    "code": editor.getValue()
                }),
                success: function (data, textStatus, jqXHR) {
                    if (data.retour == "Compilation Successful !") {
                        $("#" + div_response).attr('class', 'console , valid');
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
