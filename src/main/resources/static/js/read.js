var UserModule =  (function(){
    var nombre = null;
    var telefono = null;
    var email = null;
    var fecha = null;
    var idUsuario = null;
    
    var readCookie = function(name){
        return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + name.replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
    };
    
    var set = function(){
        var cookie = readCookie("usuarioActual");
        if(cookie != null && cookie != ""){
            var data = JSON.parse(cookie);
            console.log(data);
            nombre = data.name;
            telefono = data.phone;
            email = data.email;
            fecha = data.date;
            idUsuario = data.id;
            return true;
        }   
        return false;
    };
    
    return{
        
        set: set,
        
        readCookie: readCookie,
        
        getNombre: function(){
            return nombre;
        },
        
        getEmail: function(){
            return email; 
        },
        
        getTelefono: function(){
            return telefono; 
        },
        
        getFecha: function(){
            return fecha; 
        },
        
        getIdUsuario: function(){
            return idUsuario;
        }
        
    };
    
})();


