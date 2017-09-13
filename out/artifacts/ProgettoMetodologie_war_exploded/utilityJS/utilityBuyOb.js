/**
 * Created by Andrea on 12/09/2017.
 */


//NUOVA VENDITA
$('.vendi').click(function () {
    var rowCount = $('#lm').find('tr').length - 1;
    var idprod = [], q = [];
    for (var i = 0; i < rowCount; i++) {
        var txt = $("input[name=ordina" + i + "]");
        if (parseInt(txt.val()) > 0 && $.isNumeric(txt.val())) {
            var $tr = $(txt).parents("tr");
            idprod.push($tr.find("td").eq(0).html().replace(/[^0-9]/g, '')); // idprodotto
            q.push(txt.val());
        }
    }
    $.ajaxSettings.traditional = true;
    $.ajax({
        url: "../startsell.do",
        type: 'POST',
        data: $.param({prodotti: idprod, quantita: q}, true),
        success: function (response) {
            if(response==="error")
                window.location.href = "/page-sell/error.jsp"
            if(response==="sell-made")
                window.location.href = "/page-sell/sell-made.jsp"
            if(response==="sell-continue")
                window.location.href = "/page-sell/sell-continue.jsp"
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
    return true;
});



    //AGGIUTA ELEMENTO
    $(".add").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        //var size = $('#lm').children('thead').length
        //var quantita = $tr.find("td").eq(size-2).html().replace(/[^0-9]/g, ''); // quantita
        //if (parseInt(txt.val()) <= quantita)
        txt.val(parseInt(txt.val()) + 1);

    });

    //DIMINUIZIONE ELEMENTO
    $(".sub").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        if (parseInt(txt.val()) != 0)
            txt.val(parseInt(txt.val()) - 1);
    });