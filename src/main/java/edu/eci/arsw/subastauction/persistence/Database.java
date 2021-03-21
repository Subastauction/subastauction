package edu.eci.arsw.subastauction.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI; 
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.eci.arsw.subastauction.model.Evento;

import edu.eci.arsw.subastauction.model.Usuario;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class Database {
    private MongoClientURI uri;
    private MongoClient cliente;
    private MongoDatabase basedeDatos;
    
    public Database() {
        uri = new MongoClientURI("mongodb+srv://admin:admin@subastaction.rqgs0.mongodb.net/Subastaction?retryWrites=true&w=majority");
        cliente = new MongoClient(uri);
        basedeDatos = cliente.getDatabase("Subastaction");
    }

    public void añadirUsuario(Usuario usuario) {
        cliente = new MongoClient(uri);
        MongoCollection<Document> collection = basedeDatos.getCollection("Usuarios");
        Document document = new Document();
        document.put("name", usuario.getName());
        document.put("email", usuario.getEmail());
        document.put("phone", usuario.getPhone());
        document.put("date", usuario.getDate());
        document.put("password", usuario.getPassword());
        collection.insertOne(document);
    }
    
    public void añadirEvento(Evento evento) {
        cliente = new MongoClient(uri);
        MongoCollection<Document> collection = basedeDatos.getCollection("Evento");
        Document document = new Document();
        document.put("name", evento.getName());
        document.put("description", evento.getDescription());
        document.put("startDate", evento.getStartDate());
        document.put("endDate", evento.getEndDate());
        document.put("initialOffer", evento.getInitialOffer());
        collection.insertOne(document);
    }

    public void eliminarUsuario(String email){
        MongoCollection<Document> collection = basedeDatos.getCollection("Usuarios");
        Document findDocument= new Document("email",email);
        collection.findOneAndDelete(findDocument);
    }

    public boolean login(String email,String password) throws Exception {
        boolean lg=false;
        MongoCollection<Document> collection = basedeDatos.getCollection("Usuarios");
        Document findDocument= new Document("email",email);
        MongoCursor<Document> fit = collection.find(findDocument).iterator();
        while (fit.hasNext()) {
            Document i=fit.next();
            String em = (String) i.getString("email");
            String pw = (String) i.getString("password");
            if(email.equals(em)){
                if(password.equals(pw)) lg=true;
                else throw new Exception("Contraseña invalida");
                break;
            }

        }
        return lg;
    }
    
    public Set<Evento> consultarEventos(){
        Set<Evento> set = new HashSet<Evento>();
        MongoCollection<Document> collection = basedeDatos.getCollection("Evento");
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        while(cursor.hasNext()){
            
            Document doc = cursor.next();
            String name = doc.getString("name");
            String description = doc.getString("description");
            Date startDate = doc.getDate("startDate");
            Date endDate = doc.getDate("endDate");
            int initialOffer = doc.getInteger("initialOffer");
            set.add(new Evento(name, description, startDate, endDate, initialOffer));
        }
        return set;
    }

    public static void main(String[] args){
        Database db = new Database();
        try {
            db.login("james@gmail.com","james123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}