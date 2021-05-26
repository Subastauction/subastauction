var socket = (function (){
    
    var stompClient = null;
    var idsubasta = null;
    var ofertaInicial;
    var getQuerystring = function (name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    };
    
    var initEvento = function () {

        idsubasta = getQuerystring('value');
        // Local: 'http://localhost:8080/subastauction/consultar/eventos/'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/consultar/eventos/'
        fetch("https://subastauction.herokuapp.com/subastauction/consultar/eventos/" + idsubasta)
            .then(response => response.json())
            .then(json => verEvento(json))
            .catch(err => {
                console.log(err);
            });

        fetch("https://subastauction.herokuapp.com/subastauction/oferta/" + idsubasta)
            .then(response => response.json())
            .then(json => crearTabla(json))
            .catch(err => {
                console.log(err);
            });

    };
    
    var verEvento = function (json) {
        $("#nombre_evento").text(json.name);
        $("#descripcion_evento").text(json.description);
        ofertaInicial=json.initialOffer;
    };
    
    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        
        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newsubasta.'+idsubasta, function (eventbody) {
                var oferta = JSON.parse(eventbody.body);
                $("#scroll1Frase").text(oferta.idUsuario + " ha ofertado " + oferta.cantidad);
            });
            stompClient.subscribe('/topic/alloffers.'+idsubasta, function (evt) {
                var ofertas = JSON.parse(evt.body);
                crearTabla(ofertas);
            });
        });
    };
    
    var ofertar = function(){
        var data = {};
        data.cantidad = document.getElementById("cantidad").value;
        data.idUsuario = UserModule.getNombre();
        data.idEvento = idsubasta;
        var hoy = Date.now();
        data.fecha = new Date(hoy);
        if(ofertaInicial<=data.cantidad) {
            // Local: 'http://localhost:8080/subastauction/registrar/oferta'
            // Heroku: 'https://subastauction.herokuapp.com/subastauction/registrar/oferta'
            fetch("https://subastauction.herokuapp.com/subastauction/registrar/oferta", {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => obtenerOfertas())
                .catch(err => {
                    console.log(err);
                });
        }
        else{
            console.log("La oferta no supera a la oferta minima.");
        }
    };

    var obtenerOfertas= function (){
        fetch("https://subastauction.herokuapp.com/subastauction/oferta/" + idsubasta)
            .then(response => response.json())
            .then(json => {
                stompClient.send("/app/alloffers."+ idsubasta, {}, JSON.stringify(json.slice(0,4)));
                stompClient.send("/app/newsubasta."+ idsubasta, {}, JSON.stringify({cantidad:document.getElementById("cantidad").value, idUsuario:UserModule.getNombre(), idEvento:idsubasta}));
            })
            .catch(err => {
                console.log(err);
            });
    }

    var crearTabla = function(json){
        ofertaInicial=json[0].cantidad;
        var trs = [[document.getElementById("name1"),document.getElementById("fecha1"),document.getElementById("val1")],[document.getElementById("name2"),document.getElementById("fecha2"),document.getElementById("val2")],[document.getElementById("name3"),document.getElementById("fecha3"),document.getElementById("val3")],[document.getElementById("name4"),document.getElementById("fecha4"),document.getElementById("val4")]];
        let i=0;
        while (i<json.length && i<trs.length){
            trs[i][0].innerText=json[i].idUsuario;
            trs[i][1].innerText=json[i].fecha;
            trs[i][2].innerText=json[i].cantidad;
            i++;
        }
    }
       
    return{
        
        init: function () {
            initEvento();
            TextModule.init();
            //websocket connection
            connectAndSubscribe();
        },
        
        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        },
        
        ofertar: ofertar
        
        
    };
    
})();


