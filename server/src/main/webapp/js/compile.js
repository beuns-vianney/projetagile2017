function compile(div_editor, div_button){
        var editor = ace.edit(div_editor);
        $(document).ready(function() {

            $("#"+div_button).click(function() {
                console.log(editor.getValue());
                var url = "../v1/exercice";
                console.log("postUserGeneric " + url)
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    url: url,
                    dataType : "json",
                    data: JSON.stringify({
                        "code": editor.getValue()
                    }),
                    success: function(data, textStatus, jqXHR) {
                        console.log(data);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log('postUser error: ' + textStatus);
                    }
                });
            })

        });
}