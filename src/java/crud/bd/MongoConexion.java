/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.bd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Ser_090
 */
public class MongoConexion {

    private static MongoClient mongoClient;

    //Metodo para obtener la conexion a la base de datos
    public static MongoDatabase getBaseDeDatos() {
        if (mongoClient == null) {
            String uri = "mongodb://localhost:27017";
            mongoClient = new MongoClient(new MongoClientURI(uri));
        }
        return mongoClient.getDatabase("miBaseDeDatos");
    }

    //Metodo para cerrar la conexion
    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

}
