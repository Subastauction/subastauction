package edu.eci.arsw.subastauction.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Oferta")
public class Oferta {
    
    @Id
    private String id;
    
    private int cantidad;
    private String idUsuario;
    private String idEvento;
    private Date fecha;

    public Oferta(String id, int cantidad, String idUsuario, String idEvento, Date fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.fecha = fecha;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return "Oferta{" + "cantidad=" + cantidad + ", idUsuario=" + idUsuario + ", idEvento=" + idEvento + ", fecha=" + fecha + '}';
    }

    
    
}
