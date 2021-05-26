var misSubastas = (function(){
    
    var init = function(){
        var user = UserModule.getIdUsuario();
        // Local: 'http://localhost:8080/subastauction/consultar/eventos/usuario'
        // Heroku: 'https://subastauction.herokuapp.com/subastauction/consultar/eventos/usuario'
        fetch("https://subastauction.herokuapp.com/subastauction/consultar/eventos/usuario/" + user)
            .then(response => response.json())
            .then(json => verSubastas(json))
            .catch(err => {
                console.log(err);
            });
        
    };
    
    var verSubastas = function(json){
        console.log(json);
        for (var i in json){
            var nombre = json[i].name;
            var descripcion = json[i].description;
            var inicio = json[i].startDate;
            var fin = json[i].endDate;
            var oferta = json[i].initialOffer;
            
            var s = "<tr>" +
                    "<td>" + nombre + "</th>" +
                    "<td>" + descripcion + "</th>" +
                    "<td>" + inicio + "</th>" +
                    "<td>" + fin + "</th>" +
                    "<td>" + oferta + "</th>" +
                    "</tr>";
            $("#body").append(s);
        }
    };
    
    return{
        init: init 
    };
})();


