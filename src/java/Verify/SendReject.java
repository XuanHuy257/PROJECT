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
public class SendReject {

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
            message.setSubject("Order has been Rejected - Here comes The Aroma Shop!");

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
                    + "            <h2 style=\"color: #ff4c4c; text-align: center;\">Order Rejected - Aroma Shop</h2>\n"
                    + "\n"
                    + "            <p>Hello <strong>" + name + "</strong>,</p>\n"
                    + "            <p>We regret to inform you that your order has been <strong>Rejected</strong>.</p>\n"
                    + "            <p>Please find the details of your order below:</p>\n"
                    + "\n"
                    + "            <div\n"
                    + "                style=\"\n"
                    + "                background-color: #fff;\n"
                    + "                padding: 15px;\n"
                    + "                border-radius: 8px;\n"
                    + "                margin-bottom: 20px;\n"
                    + "                border: 1px solid #e0e0e0;\n"
                    + "                \"\n"
                    + "                >\n"
                    + "                <p><strong>Order ID:</strong> " + orderid + "</p>\n"
                    + "                <p><strong>Date Order:</strong> " + date + "</p>\n"
                    + "                <p><strong>Total Amount:</strong> " + amount + " $</p>\n"
                    + "                <p><strong>Order Status:</strong> Rejected</p>\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <h3 style=\"color: #ff4c4c;\">Reason:</h3>\n"
                    + "            <p>Your order was <strong>Rejected</strong> due to one of the following <strong>Reasons</strong>:</p>\n"
                    + "            <ul style=\"margin-left: 20px;\">\n"
                    + "                <li><strong>Sales Reject:</strong> Your order was rejected due to a decision made by our sales team, possibly due to inventory issues, product unavailability, or other internal factors.</li>\n"
                    + "                <li><strong>Customer Reject:</strong> The order was rejected based on customer preferences or information that did not meet our criteria, such as incomplete or incorrect shipping details.</li>\n"
                    + "                <li><strong>Payment Failure:</strong> The payment for your order could not be processed successfully. Please verify your payment details and try again.</li>\n"
                    + "            </ul>\n"
                    + "\n"
                    + "            <h3 style=\"color: #4B5CED;\">Next Steps:</h3>\n"
                    + "            <ul style=\"margin-left: 20px;\">\n"
                    + "                <li>If your payment was made via <strong>VNPAY</strong>, please contact our support team to initiate a refund.</li>\n"
                    + "                <li>If you still wish to purchase the items, you can place a new order on our website.</li>\n"
                    + "                <li>If you need assistance or have any questions, feel free to reach out to our support team.</li>\n"
                    + "            </ul>\n"
                    + "\n"
                    + "            <h3 style=\"color: #ff4c4c;\">Contact us:</h3>\n"
                    + "            <p style=\"margin-left: 20px;\">\n"
                    + "                - <strong>Mobile:</strong> 0866224138<br />\n"
                    + "                - <strong>Email:</strong> \n"
                    + "                <a href=\"mailto:[sontug9xx@gmail.com]\" style=\"color: #ff4c4c; text-decoration: none;\">\n"
                    + "                    sontug9xx@gmail.com\n"
                    + "                </a><br />\n"
                    + "                - <strong>Working hours:</strong> Monday to Friday, from 8:00 AM to 6:00 PM\n"
                    + "            </p>\n"
                    + "\n"
                    + "            <div style=\"text-align: center; margin-top: 30px;\">\n"
                    + "                <p style=\"font-size: 18px; color: #ff4c4c;\">We apologize for any inconvenience caused.</p>\n"
                    + "                <p style=\"color: #ff4c4c;\">Sincerely,<br /><strong>Aroma Shop</strong></p>\n"
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
