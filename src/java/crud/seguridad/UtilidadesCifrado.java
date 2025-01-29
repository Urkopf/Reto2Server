package crud.seguridad;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class UtilidadesCifrado {

    private static final String RUTA_CLAVE_PUBLICA = "crud/seguridad/clave_publica.key";
    private static final String RUTA_CLAVE_PRIVADA = "crud/seguridad/clave_privada.key";
    private static final String RUTA_CLAVE_SIMETRICA = "crud/seguridad/clave_simetrica.key";

    static {
        Security.addProvider(new BouncyCastleProvider()); // Agregar soporte para ECIES
    }

    /**
     * * 🔹 Generación de Claves ECC (Asimétrico) 🔹 **
     */
    public static void generarYGuardarClaves() throws Exception {
        KeyPair claves = generarParDeClaves();
        guardarClave(claves.getPublic(), RUTA_CLAVE_PUBLICA);
        guardarClave(claves.getPrivate(), RUTA_CLAVE_PRIVADA);
    }

    private static KeyPair generarParDeClaves() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("EC");
        generador.initialize(new ECGenParameterSpec("secp256r1"));
        return generador.generateKeyPair();
    }

    private static void guardarClave(Key clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave.getEncoded());
        }
    }

    public static PublicKey cargarClavePublica() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PUBLICA);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePublic(new X509EncodedKeySpec(claveBytes));
    }

    public static PrivateKey cargarClavePrivada() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PRIVADA);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(claveBytes));
    }

    /**
     * * 🔹 Cifrado y Descifrado con ECIES (ECC) 🔹 **
     */
    public static String cifrarConClavePublica(String datos, PublicKey clavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes());
        return Base64.getEncoder().encodeToString(datosCifrados);
    }

    public static String descifrarConClavePrivada(String datosCifrados, PrivateKey clavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
        byte[] datosDecodificados = Base64.getDecoder().decode(datosCifrados);
        byte[] datosDescifrados = cipher.doFinal(datosDecodificados);
        return new String(datosDescifrados);
    }

    /**
     * * 🔹 Cifrado Simétrico (AES) 🔹 **
     */
    public static void generarYGuardarClaveSimetrica() throws Exception {
        KeyGenerator generador = KeyGenerator.getInstance("AES");
        generador.init(256);
        SecretKey claveSimetrica = generador.generateKey();
        guardarClaveSimetrica(claveSimetrica, RUTA_CLAVE_SIMETRICA);
    }

    private static void guardarClaveSimetrica(SecretKey clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave.getEncoded());
        }
    }

    public static SecretKey cargarClaveSimetrica() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_SIMETRICA);
        return new SecretKeySpec(claveBytes, "AES");
    }

    public static String cifrarConClaveSimetrica(String datos, SecretKey claveSimetrica) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, claveSimetrica);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(datosCifrados);
    }

    public static String descifrarConClaveSimetrica(String datosCifrados, SecretKey claveSimetrica) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, claveSimetrica);
        byte[] datosDecodificados = Base64.getDecoder().decode(datosCifrados);
        byte[] datosDescifrados = cipher.doFinal(datosDecodificados);
        return new String(datosDescifrados, "UTF-8");
    }

    /**
     * * 🔹 Utilidades 🔹 **
     */
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

    // Hashear una contraseña con SHA-256
    public static String hashearContraseña(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(contraseña.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Generar una contraseña temporal
    public static String generarContrasenaTemporal() {
        SecureRandom random = new SecureRandom();
        int longitud = 10;
        String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String caracteres = mayusculas + minusculas + numeros;

        // Asegurar que la contraseña tiene al menos una mayúscula, un número y cumple con la longitud
        StringBuilder contrasena = new StringBuilder(longitud);

        // Añadir al menos una mayúscula, un número y completar con caracteres aleatorios
        contrasena.append(mayusculas.charAt(random.nextInt(mayusculas.length())));
        contrasena.append(numeros.charAt(random.nextInt(numeros.length())));
        for (int i = 2; i < longitud; i++) {
            contrasena.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        // Mezclar los caracteres para evitar patrones previsibles
        char[] contrasenaArray = contrasena.toString().toCharArray();
        for (int i = 0; i < contrasenaArray.length; i++) {
            int randomIndex = random.nextInt(contrasenaArray.length);
            char temp = contrasenaArray[i];
            contrasenaArray[i] = contrasenaArray[randomIndex];
            contrasenaArray[randomIndex] = temp;
        }

        return new String(contrasenaArray);
    }

}
