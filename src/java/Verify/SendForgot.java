/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Verify;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Base64;
/**
 *
 * @author Anh Tuan
 */
public class SendForgot {

    public void sendForgot(String toEmail, int ExpireTime, long ResetTime) throws UnsupportedEncodingException {
        //cau hinh mail nguoi gui
        String fromEmail = "huangquan2208@gmail.com";
        String fromPassword = "kfiugmgourrqycpp";

        //thiet lap ket noi
        String host = "smtp.gmail.com";
        //cau hinh mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //tao phien
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });
        String email = URLEncoder.encode(toEmail, StandardCharsets.UTF_8.toString());
       
        String encodedResetTime = Base64.getEncoder().encodeToString(Long.toString(ResetTime).getBytes(StandardCharsets.UTF_8));
        try {
            //khoi tao doi tuong 
            Message message = new MimeMessage(session);

            //setup dia chi nguoi gui
            message.setFrom(new InternetAddress(fromEmail));

            //setup dia chi nguoi nhan
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            //tieu de cua mail
            message.setSubject("Here comes The Aroma Shop!");

            //noi dung mail
            message.setContent("<div\n"
                    + "             style=\"\n"
                    + "        width: 100%;\n"
                    + "        max-width: 600px;\n"
                    + "        margin: 20px auto;\n"
                    + "        padding: 20px;\n"
                    + "        text-align: center;\n"
                    + "        background-color: #ffffff;\n"
                    + "        border: 3px solid #4B5CED;\n"
                    + "        border-radius: 10px;\n"
                    + "        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "            \"\n"
                    + "            >\n"
                    + "            <h1 style=\"color: #4B5CED;\">Welcome to Aroma Shop!</h1>\n"
                    + "            <h3 style=\"color: #333333;\">Thank you for being one of our valued customers</h3>\n"
                    + "            <small style=\"color: #666666;\">Please enter click on the Verification Link button below to reset your password.</small>\n"
                    + "            <br>\n"
                    + "            <small style=\"color: #666666;\">The password reset link you have received will expire in " + ExpireTime / 60 + " minutes. Make sure to reset your password within this time frame.</small>\n"
                    + "            <br>\n"
                    + "            <a\n"
                    + "                href=\"http://localhost:9999/SWP_Group4/resetpassword?email=" + email + "&time=" + encodedResetTime + "\"\n"
                    + "    style=\"\n"
                    + "        display: block;\n"
                    + "        background-color: #4B5CED;\n"
                    + "        color: #ffffff;\n"
                    + "        padding: 15px 20px;\n"
                    + "        text-decoration: none;\n"
                    + "        border-radius: 10px;\n"
                    + "        font-size: 16px;\n"
                    + "        width: fit-content;\n"
                    + "        margin: 20px auto;\n"
                    + "        cursor: pointer;\n"
                    + "        transition: background-color 0.3s ease;;\n"
                    + "                \"\n"
                    + "                >\n"
                    + "                Reset Password Link\n"
                    + "            </a>\n"
                    + "            <div style=\"margin-top: 20px;\">\n"
                    + "                <h5 style=\"margin: 0;\">Questions? <a href=\"#\" style=\"color: #4B5CED; text-decoration: none;\">We're here to help</a></h5>\n"
                    + "            </div>\n"
                    + "        </div>",
                    "text/html");

            //gui email
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

}
