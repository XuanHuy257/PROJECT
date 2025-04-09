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
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Anh Tuan
 */
public class SendThank {

    public void send(String toEmail, int orderid, String name, Date date, double amount) {
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

        try {
            //khoi tao doi tuong 
            Message message = new MimeMessage(session);

            //setup dia chi nguoi gui
            message.setFrom(new InternetAddress(fromEmail));

            //setup dia chi nguoi nhan
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            //tieu de cua mail
            message.setSubject("Thank You For Your Purchase and Feedback Instructions - Here comes The Aroma Shop!");

            //noi dung mail
            message.setContent("<div\n"
                    + "            style=\"\n"
                    + "            width: 100%;\n"
                    + "            max-width: 600px;\n"
                    + "            margin: 20px auto;\n"
                    + "            padding: 30px;\n"
                    + "            background-color: #f9f9f9;\n"
                    + "            border: 2px solid #e0e0e0;\n"
                    + "            border-radius: 10px;\n"
                    + "            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);\n"
                    + "            font-family: 'Helvetica Neue', Arial, sans-serif;\n"
                    + "            font-size: 16px;\n"
                    + "            color: #333;\n"
                    + "            line-height: 1.6;\n"
                    + "            \"\n"
                    + "            >\n"
                    + "            <h2 style=\"color: #4B5CED; text-align: center;\">Thank you for shopping at Aroma!</h2>\n"
                    + "\n"
                    + "            <p>Hello <strong>" + name + "</strong>,</p>\n"
                    + "            <p>Thank you for your order! We are happy to inform you that your order has been completed\n"
                    + "            <p>Here are the details of your order:</p>\n"
                    + "\n"
                    + "            <div style=\"background-color: #fff; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #e0e0e0;\">\n"
                    + "                <p><strong>Order ID:</strong> " + orderid + "</p>\n"
                    + "                <p><strong>Date Order:</strong> " + date + "</p>\n"
                    + "                <p><strong>Total Amount:</strong> " + amount + " $</p>\n"
                    + "                <p><strong>Order Status:</strong> Completed</p>\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <h3 style=\"color: #4B5CED;\">Feedback Instructions:</h3>\n"
                    + "            <ul style=\"margin-left: 20px;\">\n"
                    + "                <li><strong>Step 1:</strong> Log in to your account on the website.</li>\n"
                    + "                <li><strong>Step 2:</strong> Go to <strong>\"Shop - My Feedback\"</strong> in the main menu.</li>\n"
                    + "                <li><strong>Step 3:</strong> Select the product you want to send feedback on.</li>\n"
                    + "                <li><strong>Step 4:</strong> If you have pictures of the product or your experience, please attach them in your feedback. This will help us better understand your opinion.</li>\n"
                    + "            </ul>\n"
                    + "\n"
                    + "            <h3 style=\"color: #4B5CED;\">Contact us:</h3>\n"
                    + "            <p style=\"margin-left: 20px;\">\n"
                    + "                - <strong>Mobile:</strong> 0866224138<br>\n"
                    + "                - <strong>Email:</strong> <a href=\"mailto:[sontug9xx@gmail.com]\" style=\"color: #4B5CED; text-decoration: none;\">sontug9xx@gmail.com</a><br>\n"
                    + "                - <strong>Working hours:</strong> Monday to Friday, from 8:00 AM to 6:00 PM\n"
                    + "            </p>\n"
                    + "\n"
                    + "            <div style=\"text-align: center; margin-top: 30px;\">\n"
                    + "                <p style=\"font-size: 18px; color: #4B5CED;\">Thank you for shopping at <strong>Aroma</strong>!</p>\n"
                    + "                <p style=\"color: #4B5CED;\">Sincerely,<br><strong>Aroma</strong></p>\n"
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
