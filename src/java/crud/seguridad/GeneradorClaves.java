package crud.seguridad;

import static crud.seguridad.UtilidadesCifrado.generarYGuardarClaves;

/**
 * La clase {@code GeneradorClaves} es la clase principal utilizada para generar
 * y almacenar un par de claves criptográficas asimétricas (ECC) mediante el
 * método {@link UtilidadesCifrado#generarYGuardarClaves()}.
 * <p>
 * Esta clase permite, en un futuro, cargar las claves y realizar operaciones de
 * cifrado, descifrado y hashing.
 * </p>
 *
 */
public class GeneradorClaves {

    /**
     * Método principal que invoca la generación y almacenamiento de las claves
     * criptográficas.
     * <p>
     * Se llama al método {@link UtilidadesCifrado#generarYGuardarClaves()} para
     * crear y guardar las claves en archivos. El bloque comentado ilustra cómo
     * se podrían cargar las claves, cifrar y descifrar una contraseña, y
     * posteriormente hashearla, aunque no se ejecuta en esta versión.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no se utilizan en esta
     * implementación).
     */
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
