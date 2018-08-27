package martak.jjdd4.aftercourse;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Producer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("TEST.MESSAGES.QUEUE");

        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter your message: ");
            String text = scanner.nextLine();
            Message message = session.createTextMessage(text);
            producer.send(message);

            if (text.equals("exit")) {
                break;
            }

            System.out.println("Message successfully sent.");

        }

        session.close();
        connection.close();
        producer.close();

    }
}
