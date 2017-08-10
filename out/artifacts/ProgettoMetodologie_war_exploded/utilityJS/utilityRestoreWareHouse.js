/**
 * Created by Andrea on 10/08/2017.
 */


$(document).ready(function () {

    $(".ordinare").click(function () { //classe del botone
        var elemento = $(this).attr('id').replace(/[^0-9]/g, ''); //id del tasto che clicki
        var txt = $("input[name=ordina" + elemento + "]");//nome della textbox
        var $tr = $(this).parents("tr");
        var idprodotto= $tr.find("td").eq(0).html(); // idprodotto
        $.post("restoreWareHousePage.jsp", {prodotto: idprodotto, valore: txt.val()}, function(){
            alert('Ordine effettuato con successo');
            location.reload();
        });
    });
});