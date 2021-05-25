var TextModule = (function () {

    var Scroll = function (nombre) {
        this.id = nombre;
        this.avanza = avanza;
        this.anchoContenedor = document.getElementById(this.id)
                .parentNode.offsetWidth;
        document.getElementById(this.id)
                .parentNode.style.overflow = "hidden";
        document.getElementById(this.id)
                .parentNode.style.whiteSpace = "nowrap";
        document.getElementById(this.id)
                .parentNode.style.textIndent = "0px";
        this.el = document.getElementById(this.id).parentNode;
        this.as = this.el.getElementsByTagName("span");
        this.xo = this.anchoContenedor;
        this.contenido = this.as[0].innerHTML;
        this.anchoTexto = this.as[0].offsetWidth;
        this.dif = this.as[0].offsetWidth - this.xo;
    };

    var avanza = function (velocidad, modo) {
        this.as[0].parentNode.style.textIndent = "" + this.xo + "px";
        if (this.anchoTexto > this.anchoContenedor)
        {
            if (modo == 0)
            {
                if (this.xo <= -this.dif)
                    this.as[0].innerHTML = this.contenido + this.contenido;

                if (this.xo <= -this.dif - this.anchoContenedor)
                {
                    this.as[0].innerHTML = this.contenido;
                    this.xo = 0;
                }
            }

            if (modo == 1)
                if (this.xo <= -this.dif - this.anchoContenedor)
                    this.xo = this.anchoContenedor;

            if (modo == 2)
                if (this.xo <= -this.dif - this.anchoContenedor)
                {
                    this.xo = this.xo - velocidad;
                    velocidad = 0;
                }
        } else
        {
            if (modo != 2)
                if (this.xo <= -this.anchoTexto)
                    this.xo = this.anchoContenedor;
                else
                if (this.xo <= -this.anchoTexto)
                {
                    this.xo = this.xo - velocidad;
                    velocidad = 0;
                }
        }
        this.xo = this.xo - velocidad;
    };

    var init = function () {
        var scroll1 = new Scroll('scroll1Frase');
        window.setInterval(function () {
            scroll1.avanza(1, 0);
        }, 10);
        // 5=salto o velocidad en pixels, 10=msg.
    };
    
    return{
        init:init
    };
})();
