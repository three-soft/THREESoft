/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer;

import com.threesoft.amoxcalitimer.models.Academico;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author damianri
 */
public class Correo {

    private final static String EMAIL = "gestordeespaciosculturalesfacu@gmail.com";
    private final static String PASSWORD = "gestordec123";
    private final static String CORREO_DE_ACTIVACION = "Buen día, %s<br>"
            + "Tu registro ha sido <b>Activado</b>, ahora cuenta con acceso al sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ahora puede iniciar sesión. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String CORREO_DE_CAMBIO_ESTADO_DESACT = "Buen día, %s<br>"
            + "Tu registro ha sido <b>Desactivado</b> temporalmente, por el momento no tendrá acceso al sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>.<br>"
            + "Para cualquier aclaración puedes contactar a la administración o esperar a tener acceso nuevamente. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String CORREO_DE_RECHAZO = "Buen día, %s<br>"
            + "Tu registro ha sido verificado y NO CUMPLIÓ con los estándares para tener acceso al sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", puedes intertar de nuevo registrandote: <br>"
            + "<a href=\"%s\"><b>Registro a Gestor de Espacios Culturades</b></a>. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String CORREO_DE_REGISTRO = "Buen día, %s<br>"
            + "Tu registro al sistema <br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + " está siendo revisado, por favor espera el correo de aceptación. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String CORREO_DE_ADMIN = "Buen día, %s<br>"
            + "Se te ha dado de alta como <b>administrador</b> en el sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ahora puede iniciar sesión con los siguientes datos: <br>"
            + "Correo: %s<br> Contraseña: %s<br>"
            + "Se recomienda ingresar al sistema y hacer el cambio de contraseña por una personal.<br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String EVENTO_ACEPTADO = "Buen día, %s<br>"
            + "Se te informa que la solicitud para reservar un espacio para tu evento en el sistema"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ha sido ACEPTADO. Puedes iniciar sesión para verificar que sea correcto."
            + "<br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String EVENTO_DENEGADO = "Buen día, %s<br>"
            + "Se te informa que la solicitud para reservar un espacio para tu evento en el sistema"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ha sido DENEGADO. Puedes solicitar un espacio diferente o intentar con otro horario."
            + "<br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    
    private final static Properties MAIL_SERVER_PROPERTIES;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;

    static {
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        MAIL_SERVER_PROPERTIES = System.getProperties();
        MAIL_SERVER_PROPERTIES.put("mail.smtp.port", "587");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.auth", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.starttls.enable", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("Mail Server Properties have been setup successfully..");
    }

    public static void correoDeRegistro(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Registro.");
        generateMailMessage.setContent(String.format(CORREO_DE_REGISTRO, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoDeActivacion(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Acceso Activado.");
        generateMailMessage.setContent(String.format(CORREO_DE_ACTIVACION, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoCambioEstado(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Acceso Desactivado.");
        generateMailMessage.setContent(String.format(CORREO_DE_CAMBIO_ESTADO_DESACT, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoDeActivacionAdmin(String mailDestinatario, String nombreCompletoUsuario, String passwordTemporal) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Acceso Activado.");
        generateMailMessage.setContent(String.format(CORREO_DE_ADMIN, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/", mailDestinatario, passwordTemporal), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoDeRechazo(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Acceso Denegado.");
        generateMailMessage.setContent(String.format(CORREO_DE_RECHAZO, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/", "http://localhost:8084/AmoxcaliTimer/views/general/registro.xhtml"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoEventoAceptado(Academico academico) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(academico.getCorreoAca()));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Evento Aceptado.");
        generateMailMessage.setContent(String.format(EVENTO_ACEPTADO, academico.getNombreCompleto(), "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }

    public static void correoEventoDenegado(Academico academico) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(academico.getCorreoAca()));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Evento Denegado.");
        generateMailMessage.setContent(String.format(EVENTO_DENEGADO, academico.getNombreCompleto(), "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
}
