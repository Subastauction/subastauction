package edu.eci.arsw.subastauction.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI; 
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import edu.eci.arsw.subastauction.model.Usuario;

import org.bson.Document;
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

    public static void main(String[] args){
        Database db = new Database();
        try {
            db.login("james@gmail.com","james123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}