package crud.enviocorreo;

import crud.seguridad.UtilidadesCifrado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.crypto.SecretKey;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * La clase {@code EnvioCorreos} se encarga de enviar correos electrónicos
 * utilizando el servicio SMTP de SendGrid.
 * <p>
 * Esta clase permite enviar diferentes tipos de mensajes (por ejemplo, para
 * recuperación o cambio de contraseña) incluyendo contenido en texto y HTML,
 * así como imágenes embebidas en el cuerpo del mensaje.
 * </p>
 *
 * @author
 */
public class EnvioCorreos {

    /**
     * Envía un correo electrónico de acuerdo al tipo especificado.
     * <p>
     * El método configura la conexión SMTP utilizando las propiedades definidas
     * en el archivo de recursos {@code mail.properties} (ubicado en el paquete
     * {@code crud.enviocorreo}). Se autentica con el usuario y clave cifrados,
     * los cuales se descifran mediante una clave simétrica obtenida a través de
     * {@link UtilidadesCifrado}.
     * </p>
     *
     * @param tipo el tipo de correo a enviar; se esperan valores como
     * {@code "recupera"} o {@code "cambio"} para configurar el mensaje
     * correspondiente.
     * @param correo la dirección de correo electrónico del destinatario.
     * @param nueva en caso de ser necesario, la nueva contraseña o información
     * relevante a incluir en el mensaje.
     */
    public static void enviar(String tipo, String correo, String nueva) {
        try {
            // Configuración del servidor SMTP de SendGrid
            final String SENDGRID_SMTP_HOST = ResourceBundle.getBundle("crud.enviocorreo.mail")
                    .getString("SENDGRID_SMTP_HOST");
            final String SMTP_PORT = ResourceBundle.getBundle("crud.enviocorreo.mail")
                    .getString("SMTP_PORT");
            //final String SENDGRID_USERNAME = "apikey"; // Usuario fijo

            //final String SENDGRID_API_KEY = "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"; // API Key generada
            String USERNAME
                    = ResourceBundle.getBundle("crud.enviocorreo.mail")
                            .getString("API_USER");
            String API_KEY
                    = ResourceBundle.getBundle("crud.enviocorreo.mail")
                            .getString("API_PASS");
            SecretKey claveSimetrica = UtilidadesCifrado.cargarClaveSimetrica();
            final String SENDGRID_USERNAME = UtilidadesCifrado.descifrarConClaveSimetrica(USERNAME, claveSimetrica);
            final String SENDGRID_API_KEY = UtilidadesCifrado.descifrarConClaveSimetrica(API_KEY, claveSimetrica);
            // Lista de destinatarios
            String[] recipients = {correo};

            // Propiedades de la conexión SMTP
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SENDGRID_SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            // Sesión de correo con autenticación
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDGRID_USERNAME, SENDGRID_API_KEY);
                }
            });

            try {
                // Crear el mensaje de correo
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ceotartanga@gmail.com")); // Remitente

                // Configurar los destinatarios
                InternetAddress[] addressTo = new InternetAddress[recipients.length];
                for (int i = 0; i < recipients.length; i++) {
                    addressTo[i] = new InternetAddress(recipients[i]);
                }
                // Crear el contenido del correo
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText("Este es un mensaje de Amamazon."); // Añade un texto predeterminado o descriptivo

                // Crear el contenido del correo en HTML
                MimeBodyPart htmlPart = new MimeBodyPart();
                MimeBodyPart imagePart = new MimeBodyPart();

                imagePart.setDataHandler(cargarImagenDesdeClasspath("crud/enviocorreo/nicolas.jpg"));
                imagePart.setHeader("Content-ID", "<nicolas>");
                imagePart.setDisposition(MimeBodyPart.INLINE); // La imagen se mostrará inline en lugar de como adjunto

                MimeBodyPart imagePart2 = new MimeBodyPart();

                imagePart2.setDataHandler(cargarImagenDesdeClasspath("crud/enviocorreo/logoFullrecortado.PNG"));
                imagePart2.setHeader("Content-ID", "<logo>");
                imagePart2.setDisposition(MimeBodyPart.INLINE);

                message.setRecipients(Message.RecipientType.TO, addressTo);
                switch (tipo.toLowerCase()) {
                    case "recupera":
                        message.setSubject("Amamazon Info - Solicitud de recuperación de contraseña");
                        htmlPart.setContent("<img src='cid:logo' alt='Logo Amamazon'>" + "<p>Su nueva contraseña es: " + nueva + "</p>" + "<img src='cid:nicolas' alt='Logo nicolas'>", "text/html");
                        break;
                    case "cambio":
                        message.setSubject("Amamazon Info - Solicitud de cambio de contraseña");
                        htmlPart.setContent("<img src='cid:logo' alt='Logo Amamazon'>" + "<p>Su contraseña ha sido modificada a petición del usuario.</p>" + "<img src='cid:nicolas' alt='Logo nicolas'>", "text/html");
                        break;
                    default:
                        htmlPart.setContent("<h1>Amamazon</h1><p>Mensaje predeterminado.</p>", "text/html");
                }

                // Combinar las partes en un Multipart
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart); // Ahora textPart tiene contenido
                multipart.addBodyPart(htmlPart);
                multipart.addBodyPart(imagePart);
                multipart.addBodyPart(imagePart2);

                // Establecer el contenido del mensaje
                message.setContent(multipart);

                // Enviar el mensaje
                Transport.send(message);
                System.out.println("Correo enviado con éxito a destinatario con archivo adjunto.");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga una imagen desde el classpath y la prepara para ser utilizada en un
     * mensaje MIME.
     * <p>
     * El método busca el recurso de la imagen a partir de la ruta relativa
     * especificada. Si se encuentra la imagen, se crea un archivo temporal en
     * el sistema para poder adjuntarla al mensaje. En caso contrario, se lanza
     * una excepción {@link FileNotFoundException}.
     * </p>
     *
     * @param rutaRelativa la ruta relativa en el classpath donde se encuentra
     * la imagen.
     * @return un objeto {@link DataHandler} que contiene la imagen cargada.
     * @throws Exception si ocurre algún error al cargar la imagen o al crear el
     * archivo temporal.
     */
    public static DataHandler cargarImagenDesdeClasspath(String rutaRelativa) throws Exception {
        // Cargar la imagen desde el classpath
        InputStream is = UtilidadesCifrado.class.getClassLoader().getResourceAsStream(rutaRelativa);
        if (is == null) {
            throw new FileNotFoundException("No se encontró la imagen en el classpath: " + rutaRelativa);
        }

        // Crear un archivo temporal para que MimeBodyPart pueda adjuntarlo
        File tempFile = File.createTempFile("imagen_temp", ".jpg");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        return new DataHandler(new javax.activation.FileDataSource(tempFile));

    }

}
