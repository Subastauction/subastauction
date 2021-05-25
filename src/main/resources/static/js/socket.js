var socket = (function (){
    
    var stompClient = null;
    var idsubasta = null;
    
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
        
    };
    
    var verEvento = function (json) {
        $("#nombre_evento").text(json.name);
        $("#descripcion_evento").text(json.description);
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
        });
    };
    
    var registrartOferta = function(){
        var data = {};
        data.cantidad = document.getElementById("cantidad").value;
        data.idUsuario = UserModule.getIdUsuario();
        data.idEvento = idsubasta;
        var hoy = Date.now();
        data.fecha = new Date(hoy);
        
        // Local: 'http://localhost:8080/subastauction/registrar/oferta'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/registrar/oferta'
        fetch("https://subastauction.herokuapp.com/subastauction/registrar/oferta", {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => console.log("registrado"))
            .catch(err => {
                console.log(err);
            });
    };
    
    var ofertar = function(){
        registrartOferta();
        var evento = idsubasta;
        var usuario = "user";
        var cantidad = document.getElementById("cantidad").value;
        TextModule.init();
        stompClient.send("/app/newsubasta."+ idsubasta, {}, JSON.stringify({cantidad:cantidad, idUsuario:UserModule.getNombre(), idEvento:evento}));
        
    };
       
    return{
        
        init: function () {
            initEvento();
     
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


