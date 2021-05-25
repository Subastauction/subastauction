var user = (function(){
    
    var init = function(){
        if(UserModule.set()){
            
            $("#nombre").text(UserModule.getNombre());
            $("#correo").text(UserModule.getEmail());
            $("#telefono").text(UserModule.getTelefono());
            $("#fecha").text(UserModule.getFecha());
            
        } else {
            location.href = "index.html";
        }
    };
    
    return{
        
        init: init
        
    };
    
})();

