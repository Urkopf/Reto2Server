/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.bd;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.BasicBSONObject;

/**
 * Es un ejemplo de implementacion de MongoDB
 *
 * @author Ser_090
 */
public class EjemploUso {
    /*
    private final MongoCollection<BasicBSONObject> userCollection;

    public EjemploUso() {
        this.userCollection = MongoConexion.getBaseDeDatos().getCollection("usuarios", BasicBSONObject.class);
    }

    public List<BasicBSONObject> getAllUsers() {
        List<BasicBSONObject> users = new ArrayList<>();
        for (BasicBSONObject doc : userCollection.find()) {
            users.add(doc);
        }
        return users;
    }

    public void addUser(String name, int age) {
        BasicBSONObject user = new BasicBSONObject();
        user.put("nombre", name);
        user.put("edad", age);
        userCollection.insertOne(user);
    }
     */
}
