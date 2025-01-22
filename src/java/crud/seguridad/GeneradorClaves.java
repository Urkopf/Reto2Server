package crud.seguridad;

import static crud.seguridad.UtilidadesCifrado.generarYGuardarClaves;

public class GeneradorClaves {

    public static void main(String[] args) {
        try {
            // Generar y guardar claves en archivos
            generarYGuardarClaves();
            /*
            // Cargar claves desde archivos
            PublicKey clavePublica = cargarClavePublica();
            PrivateKey clavePrivada = cargarClavePrivada();

            // Contraseña del cliente
            String contraseña = "MiSuperContraseña123";
            System.out.println("Contraseña original: " + contraseña);

            // Cliente encripta la contraseña
            String contraseñaEncriptada = encriptarConClavePublica(contraseña, clavePublica);
            System.out.println("Contraseña encriptada: " + contraseñaEncriptada);

            // Servidor desencripta la contraseña
            String contraseñaDesencriptada = desencriptarConClavePrivada(contraseñaEncriptada, clavePrivada);
            System.out.println("Contraseña desencriptada: " + contraseñaDesencriptada);

            // Servidor hashea la contraseña antes de almacenarla
            String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);
            System.out.println("Contraseña hasheada: " + contraseñaHasheada);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
