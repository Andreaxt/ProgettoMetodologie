
$(document).ready(function () {

    $(".add").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        var $tr = $(this).parents("tr");
        var size = $tr.children("td").length;
        var quantita = $tr.find("td").eq(size - 2).html().replace(/[^0-9]/g, ''); // quantita
        if (parseInt(txt.val()) < quantita)
            txt.val(parseInt(txt.val()) + 1);

    });
    $(".sub").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        if (parseInt(txt.val()) != 0)
            txt.val(parseInt(txt.val()) - 1);
    });
    $(".ordinare").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        var $tr = $(this).parents("tr");
        var idprodotto = $tr.find("td").eq(0).html().replace(/[^0-9]/g, ''); // idprodotto
        if (parseInt(txt.val()) <= 0 || !$.isNumeric(txt.val())) {
            alert('Quantità minore o uguale a zero o non numerico');
            return;
        }
        else {
            $.post("magazzino.jsp", {prodotto: idprodotto, valore: txt.val()}, function () {
                alert('Ordine effettuato con successo');
                location.reload();
            });
        }
    });
    //NUOVA FARMACIA
    $('#creaf').submit(function () {
        var namef = $.trim($('#nf').val());
        var namet = $.trim($('#namet').val());
        var pass = $.trim($('#pass').val());
        var telefono = $.trim($('#tel').val());
        var indirizzo = $.trim($('#if').val());
        var cognomet = $.trim($('#cognomet').val());
        var user = $.trim($('#user').val());
        if (namef === '' || namef.search(';') != -1) {
            alert('Nome farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (namet === '' || namet.search(';') != -1) {
            alert('Nome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (pass === '' || pass.search(';') != -1 || pass.length < 8) {
            alert('Password titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (indirizzo === '' || indirizzo.search(';') != -1) {
            alert('Indirizzo farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (telefono === '' || !$.isNumeric(telefono)) {
            alert('Numero telefono non valido (può contenere solo numeri)');
            return false;
        }
        if (user === '' || user.search(';') != -1) {
            alert('Username titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (cognomet === '' || cognomet.search(';') != -1) {
            alert('Cognome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
    });

    //NUOVO OPERATORE
    $('#creaop').submit(function () {
        var nameop = $.trim($('#no').val());
        var pass = $.trim($('#pass').val());
        var cognomeop = $.trim($('#co').val());
        var user = $.trim($('#user').val());
        if (nameop === '' || nameop.search(';') != -1) {
            alert('Nome operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (pass === '' || pass.search(';') != -1 || pass.length < 8) {
            alert('Password operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (user === '' || user.search(';') != -1) {
            alert('Username operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (cognomeop === '' || cognomeop.search(';') != -1) {
            alert('Cognome operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
    });

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
                if (response ==="purchase")
                    window.location.href = "../purchase-continue.jsp";
                if (response === "error")
                    window.location.href = "../erroreVendita.jsp";
                if (response === "sell-made")
                    window.location.href = "../venditaConclusa.jsp";

            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
        return true;
    });

    $('.ricetta').click(function () {
        var rowCount = $('#lm').find('tr').length - 1;
        for (var i = 0; i < rowCount; i++) {
            var txt = $("input[name=cr" + i + "]");
            if (parseInt(txt.length) === 0) {
                alert('Riempi tutti i campi');
                return false;
            }
        }
        return true;
    });

    $('#creapat').submit(function () {
        var namepat = $.trim($('#np').val());
        if (namepat === '' || namepat.search(';') != -1) {
            alert('Nome paziente vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
    });
});