package crud.seguridad;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * La clase {@code UtilidadesCifrado} proporciona métodos para la generación,
 * almacenamiento, carga y utilización de claves criptográficas tanto
 * asimétricas (ECC) como simétricas (AES). Además, incluye utilidades para
 * cifrado y descifrado, hash de contraseñas y generación de contraseñas
 * temporales.
 * <p>
 * Se utiliza el proveedor BouncyCastle para dar soporte a algoritmos
 * criptográficos avanzados.
 * </p>
 */
public class UtilidadesCifrado {

    private static final String RUTA_CLAVE_PUBLICA = "crud/seguridad/clave_publica.key";
    private static final String RUTA_CLAVE_PRIVADA = "crud/seguridad/clave_privada.key";
    private static final String RUTA_CLAVE_SIMETRICA = "crud/seguridad/clave_simetrica.key";

    static {
        Security.addProvider(new BouncyCastleProvider()); // Agregar soporte para ECIES
    }

    /**
     * Genera un par de claves (pública y privada) utilizando criptografía ECC y
     * las guarda en los archivos especificados por {@code RUTA_CLAVE_PUBLICA} y
     * {@code RUTA_CLAVE_PRIVADA}.
     *
     * @throws Exception si ocurre algún error durante la generación o el
     * guardado de las claves.
     */
    public static void generarYGuardarClaves() throws Exception {
        KeyPair claves = generarParDeClaves();
        guardarClave(claves.getPublic(), RUTA_CLAVE_PUBLICA);
        guardarClave(claves.getPrivate(), RUTA_CLAVE_PRIVADA);
    }

    /**
     * Genera un par de claves ECC utilizando el algoritmo "EC" y la curva
     * "secp256r1".
     *
     * @return un objeto {@code KeyPair} que contiene la clave pública y privada
     * generadas.
     * @throws NoSuchAlgorithmException si no se encuentra el algoritmo "EC".
     * @throws InvalidAlgorithmParameterException si los parámetros
     * especificados no son válidos.
     */
    private static KeyPair generarParDeClaves() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("EC");
        generador.initialize(new ECGenParameterSpec("secp256r1"));
        return generador.generateKeyPair();
    }

    /**
     * Guarda una clave criptográfica en el archivo especificado.
     *
     * @param clave la clave que se desea guardar.
     * @param ruta la ruta del archivo donde se almacenará la clave.
     * @throws IOException si ocurre algún error al escribir en el archivo.
     */
    private static void guardarClave(Key clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave.getEncoded());
        }
    }

    /**
     * Carga la clave pública almacenada en el archivo definido por
     * {@code RUTA_CLAVE_PUBLICA}.
     *
     * @return la clave pública cargada.
     * @throws Exception si ocurre algún error al leer el archivo o reconstruir
     * la clave.
     */
    public static PublicKey cargarClavePublica() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PUBLICA);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePublic(new X509EncodedKeySpec(claveBytes));
    }

    /**
     * Carga la clave privada almacenada en el archivo definido por
     * {@code RUTA_CLAVE_PRIVADA}.
     *
     * @return la clave privada cargada.
     * @throws Exception si ocurre algún error al leer el archivo o reconstruir
     * la clave.
     */
    public static PrivateKey cargarClavePrivada() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_PRIVADA);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(claveBytes));
    }

    /**
     * Cifra una cadena de texto utilizando la clave pública y el algoritmo
     * ECIES (ECC).
     *
     * @param datos la cadena de texto que se desea cifrar.
     * @param clavePublica la clave pública utilizada para el cifrado.
     * @return la cadena cifrada en formato Base64.
     * @throws Exception si ocurre algún error durante el proceso de cifrado.
     */
    public static String cifrarConClavePublica(String datos, PublicKey clavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes());
        return Base64.getEncoder().encodeToString(datosCifrados);
    }

    /**
     * Descifra una cadena de texto cifrada utilizando la clave privada y el
     * algoritmo ECIES (ECC).
     *
     * @param datosCifrados la cadena de texto cifrada en formato Base64.
     * @param clavePrivada la clave privada utilizada para el descifrado.
     * @return la cadena de texto original descifrada.
     * @throws Exception si ocurre algún error durante el proceso de descifrado.
     */
    public static String descifrarConClavePrivada(String datosCifrados, PrivateKey clavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
        byte[] datosDecodificados = Base64.getDecoder().decode(datosCifrados);
        byte[] datosDescifrados = cipher.doFinal(datosDecodificados);
        return new String(datosDescifrados);
    }

    /**
     * Genera una clave simétrica utilizando el algoritmo AES con una longitud
     * de 256 bits y la guarda en el archivo especificado por
     * {@code RUTA_CLAVE_SIMETRICA}.
     *
     * @throws Exception si ocurre algún error durante la generación o el
     * guardado de la clave simétrica.
     */
    public static void generarYGuardarClaveSimetrica() throws Exception {
        KeyGenerator generador = KeyGenerator.getInstance("AES");
        generador.init(256);
        SecretKey claveSimetrica = generador.generateKey();
        guardarClaveSimetrica(claveSimetrica, RUTA_CLAVE_SIMETRICA);
    }

    /**
     * Guarda una clave simétrica en el archivo especificado.
     *
     * @param clave la clave simétrica a guardar.
     * @param ruta la ruta del archivo donde se almacenará la clave.
     * @throws IOException si ocurre algún error al escribir en el archivo.
     */
    private static void guardarClaveSimetrica(SecretKey clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave.getEncoded());
        }
    }

    /**
     * Carga la clave simétrica almacenada en el archivo definido por
     * {@code RUTA_CLAVE_SIMETRICA}.
     *
     * @return la clave simétrica cargada.
     * @throws Exception si ocurre algún error al leer el archivo o reconstruir
     * la clave.
     */
    public static SecretKey cargarClaveSimetrica() throws Exception {
        byte[] claveBytes = leerArchivo(RUTA_CLAVE_SIMETRICA);
        return new SecretKeySpec(claveBytes, "AES");
    }

    /**
     * Cifra una cadena de texto utilizando una clave simétrica AES.
     *
     * @param datos la cadena de texto que se desea cifrar.
     * @param claveSimetrica la clave simétrica utilizada para el cifrado.
     * @return la cadena cifrada en formato Base64.
     * @throws Exception si ocurre algún error durante el proceso de cifrado.
     */
    public static String cifrarConClaveSimetrica(String datos, SecretKey claveSimetrica) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, claveSimetrica);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(datosCifrados);
    }

    /**
     * Descifra una cadena de texto cifrada utilizando una clave simétrica AES.
     *
     * @param datosCifrados la cadena de texto cifrada en formato Base64.
     * @param claveSimetrica la clave simétrica utilizada para el descifrado.
     * @return la cadena de texto original descifrada.
     * @throws Exception si ocurre algún error durante el proceso de descifrado.
     */
    public static String descifrarConClaveSimetrica(String datosCifrados, SecretKey claveSimetrica) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, claveSimetrica);
        byte[] datosDecodificados = Base64.getDecoder().decode(datosCifrados);
        byte[] datosDescifrados = cipher.doFinal(datosDecodificados);
        return new String(datosDescifrados, "UTF-8");
    }

    /**
     * Lee un archivo desde el classpath y devuelve su contenido en un arreglo
     * de bytes.
     *
     * @param nombreArchivo el nombre (o ruta relativa) del archivo a leer.
     * @return un arreglo de bytes con el contenido del archivo.
     * @throws IOException si ocurre algún error al leer el archivo o si no se
     * encuentra.
     */
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

    /**
     * Genera un hash SHA-256 de la contraseña proporcionada.
     *
     * @param contraseña la contraseña que se desea hashear.
     * @return el hash de la contraseña en formato hexadecimal.
     * @throws NoSuchAlgorithmException si el algoritmo SHA-256 no está
     * disponible.
     */
    public static String hashearContraseña(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(contraseña.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Genera una contraseña temporal compuesta por una combinación de letras
     * mayúsculas, minúsculas y números.
     * <p>
     * La contraseña tendrá una longitud de 10 caracteres y se garantiza que
     * contendrá al menos una letra mayúscula y un número.
     * </p>
     *
     * @return la contraseña temporal generada.
     */
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
