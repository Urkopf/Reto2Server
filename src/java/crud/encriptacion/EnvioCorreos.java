package crud.encriptacion;

import java.io.File;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioCorreos {

    public static void main(String[] args) {
        // Configuración del servidor SMTP de SendGrid
        final String SENDGRID_SMTP_HOST = "smtp.sendgrid.net";
        final String SMTP_PORT = "587"; // Puerto seguro para TLS
        final String SENDGRID_USERNAME = "apikey"; // Usuario fijo
        final String SENDGRID_API_KEY = "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"; // API Key generada

        // Lista de destinatarios
        String[] recipients = {
            "lucianmikeldi@gmail.com"
        };

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
            message.setRecipients(Message.RecipientType.TO, addressTo);

            message.setSubject("Prueba de correo con SendGrid y adjuntos");

            // Crear el contenido del correo
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Este es un mensaje de prueba con texto plano y un archivo adjunto.", "utf-8");

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<h1>Este es un mensaje con adjunto</h1><p>Revisa el archivo adjunto para más detalles.</p>", "text/html");

            // Crear la parte del archivo adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("C:/Users/2dam/Documents/NetBeansProjects/Reto_2_DEV/pspEnvioCorreos/src/pspenviocorreos/nicolas_colagado.jpg")); // Cambia por la ruta de tu archivo

            // Combinar las partes en un Multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(attachmentPart);

            // Establecer el contenido del mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Correo enviado con éxito a múltiples destinatarios con archivo adjunto.");

        } catch (MessagingException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
