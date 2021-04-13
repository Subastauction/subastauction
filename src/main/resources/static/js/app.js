var controlador = (function(){
    
    var registrar = function(){
        
        var data = {};
        data.name = document.getElementById("nombre").value;
        data.email = document.getElementById("correo").value;
        data.date = document.getElementById("fecha").value;
        data.phone = document.getElementById("telefono").value;
        data.password = document.getElementById("password").value;
        
        
        // Local: 'http://localhost:8080/subastauction/registrar/usuario'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/registrar/usuario'
        fetch('https://subastauction.herokuapp.com/subastauction/registrar/usuario', {
            method: 'POST',
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
    
    var crearEvento = function(){
        
        var nombre = document.getElementById("nombre").value;
        var descripcion = document.getElementById("descripcion").value;
        var fechaInicio = document.getElementById("fechaInicio").value;
        var fechaFin = document.getElementById("fechaFin").value;
        var ofertaInicial = document.getElementById("ofertaInicial").value;
        
        var data = {};
        data.name = nombre;
        data.description = descripcion;
        data.startDate = fechaInicio;
        data.endDate = fechaFin;
        data.initialOffer = ofertaInicial;
        
        // Local: 'http://localhost:8080/subastauction/crear/evento'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/crear/evento'
        fetch('https://subastauction.herokuapp.com/subastauction/crear/evento', {
            method: 'POST',
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
    
    var init = function(){
        
        // Local: 'http://localhost:8080/subastauction/consultar/eventos'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/consultar/eventos'
        fetch('https://subastauction.herokuapp.com/subastauction/consultar/eventos')
            .then(response => response.json())
            .then(json => verEventos(json))
            .catch(err => {
                console.log(err);
            });
        
    };
    
    var verEventos = function(json){
        
        var name;
        var description;
        var id;
        
        for(var i in json){
            id = json[i].id;
            name = json[i].name;
            description = json[i].description;
            
            
            var s = "<div class='col-lg-3 col-md-6 mb-4 mb-lg-0'>" +
                    "<div class='card rounded shadow-sm border-0'>" +
                    "<div class='card-body p-4'>" +
                    "<img src='https://res.cloudinary.com/mhmd/image/upload/v1556485078/shoes-4_vgfjy9.jpg' alt='' class='img-fluid d-block mx-auto mb-3'>" +
                    "<h5>"+name+"</h5>" +
                    "<p class='small text-muted font-italic'>"+description+"</p>" +
                    "<input onclick=\"window.location='./evento.html?value="+id+"';\"  type='button' value='ingresar'>"+
                    "</div>" +
                    "</div>" +
                    "</div>";
            
            $("#divEvents").append(s);
        }
        
    };
    
    var getQuerystring = function(name){
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    };
    
    var initEvento = function(){
        var value = getQuerystring('value');
        // Local: 'http://localhost:8080/subastauction/consultar/eventos/'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/consultar/eventos/'
        fetch('https://subastauction.herokuapp.com/subastauction/consultar/eventos/'+value)
            .then(response => response.json())
            .then(json => verEvento(json))
            .catch(err => {
                console.log(err);
            });  
    };
    
    var verEvento = function(json){
        $("#nombre_evento").text(json.name);
        $("#descripcion_evento").text(json.description);
    };
    
    return{
        registrar: registrar,
        crearEvento: crearEvento,
        init: init,
        initEvento: initEvento
    };
    
})();

