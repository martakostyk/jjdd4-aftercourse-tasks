package martak.jjdd4.aftercourse;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Consumer implements Runnable, ExceptionListener {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        new Consumer().run();
    }

    @Override
    public void run() {

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("TEST.MESSAGES.TOPIC");

            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                Message message = consumer.receive();

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();

                    if (text.equals("exit")) {
                        break;
                    }

                    System.out.println("Message received: " + text);
                }
            }

            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            LOG.info(e.getMessage());
        }
    }

    @Override
    public void onException(JMSException e) {
        System.out.println("JMS Exception occured:" + e.getMessage());
    }
}
