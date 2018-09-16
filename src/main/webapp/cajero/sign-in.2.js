$(function(){
    var $idCliente 
    $("#button").on('click',function(){
        var $idCliente2 = $('#idCliente').val();
		$.ajax({
            success: function(){
                console.log($idCliente2);
                //$(location).attr('href',"index.html");
                $('#sign-in').hide();
                $('#cuerpo').show();
                $idCliente=$idCliente2;
                }
            }); 
    });
    $("#b_cuentas").click(function(){
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/BancoAguilesi-master/rest/cuenta/deCliente/"+$idCliente,
            success: function(data){
                console.log('success',$idCliente);
                $("li").remove();
                console.log(data);
                for(var i in data){
                    $("#cuentas").append(
                        $("<li/>", {text:"ID Cuenta: "+data[i].id+" | Número de cuenta: "+data[i].numero})
                        );
                }
            },
            error:function(){
                alert('Error'),
                console.log('error',$idCliente);
            }
           
        });    
    });
    $("#b_movimientos").click(function(){
        var $idCuenta = $("#idCuenta").val();
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/BancoAguilesi-master/rest/movimiento/deCuenta/"+$idCuenta,
            success: function(data){
                $("li").remove();
                for(var i in data){
                    $("#movimientos").append(
                        $("<li/>",{text:"Tipo: "+data[i].tipo+", Importe: $"+data[i].importe+", Descripcion: "+data[i].descripcion})
                        );
                }
            }
        })
    });
    $("#b_saldo").click(function(){
        var $idCuenta = $("#idCuenta").val();
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/BancoAguilesi-master/rest/cuenta/saldo/"+$idCuenta,
            success: function(data){
                $("li").remove();
                console.log(data),
                $("#saldo").append($("<li/>",{text:"Saldo: $"+data}));
                
            }
        })
    });
    $("#b_deposito").on('click',function(){
        var deposito = $("#c_deposito").val();
        var idCuenta = $("#idCuenta").val();
        var movimiento = {
            "creado": "2018-09-11T19:19:41Z[UTC]",
            "descripcion": "Deposito",
            "estado": 3,
            "id": 1,
            "idCuenta": idCuenta,
            "importe": deposito,
            "tipo": 2
          };  
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/BancoAguilesi-master/rest/movimiento",
            data:JSON.stringify(movimiento),
            success: function(){
                alert("Deposito Registrado: $"+deposito),
                console.log(movimiento);
            },
            error:function(){
                alert('Error')
            },
            contentType:"application/json"
        });
    });
    $("#b_extraccion").on('click',function(){
        var extraccion = $("#c_extraccion").val();
        var idCuenta = $("#idCuenta").val();
        var movimiento = {
            "creado": "2018-09-11T19:19:41Z[UTC]",
            "descripcion": "Extraccion",
            "estado": 3,
            "id": 1,
            "idCuenta": idCuenta,
            "importe": extraccion,
            "tipo": 1
          };  
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/BancoAguilesi-master/rest/movimiento",
            data:JSON.stringify(movimiento),
            success: function(){
                alert("Extraccion Registrada: -$"+extraccion),
                console.log(movimiento);
            },
            error:function(){
                alert('Error')
            },
            contentType:"application/json"
        });
    });
});