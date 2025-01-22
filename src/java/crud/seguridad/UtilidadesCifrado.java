package crud.seguridad;

import java.io.ByteArrayOutputStream;
import javax.crypto.Cipher;
import java.security.*;

import java.util.Base64;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class UtilidadesCifrado {

    private static final String RUTA_CLAVE_PUBLICA = "crud/seguridad/clave_publica.key";
    private static final String RUTA_CLAVE_PRIVADA = "crud/seguridad/clave_privada.key";

    // Generar y guardar claves en archivos
    public static void generarYGuardarClaves() throws Exception {
        KeyPair claves = generarParDeClaves();
        guardarClave(claves.getPublic(), RUTA_CLAVE_PUBLICA);
        guardarClave(claves.getPrivate(), RUTA_CLAVE_PRIVADA);
    }

    // Generar par de claves públicas y privadas
    public static KeyPair generarParDeClaves() throws NoSuchAlgorithmException {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        return generador.generateKeyPair();
    }

    // Guardar clave en archivo
    private static void guardarClave(Key clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave.getEncoded());
        }
    }

    // Cargar clave pública desde archivo
    public static PublicKey cargarClavePublica() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PUBLICA);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(claveBytes));
    }

    // Cargar clave privada desde archivo
    public static PrivateKey cargarClavePrivada() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PRIVADA);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(claveBytes));
    }

    // Leer archivo desde el classpath
    private static byte[] leerArchivo(String nombreArchivo) throws IOException {
        try (InputStream is = UtilidadesCifrado.class.getResourceAsStream("/" + nombreArchivo);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            if (is == null) {
                throw new FileNotFoundException("No se pudo encontrar el archivo: " + nombreArchivo);
            }
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    // Encriptar datos con clave pública
    public static String encriptarConClavePublica(String datos, PublicKey clavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
        byte[] datosEncriptados = cipher.doFinal(datos.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }

    // Desencriptar datos con clave privada
    public static String desencriptarConClavePrivada(String datosEncriptados, PrivateKey clavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
        byte[] datosDecodificados = Base64.getDecoder().decode(datosEncriptados);
        byte[] datosDesencriptados = cipher.doFinal(datosDecodificados);
        return new String(datosDesencriptados);
    }

    // Hashear datos (contraseña)
    public static String hashearContraseña(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(contraseña.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
