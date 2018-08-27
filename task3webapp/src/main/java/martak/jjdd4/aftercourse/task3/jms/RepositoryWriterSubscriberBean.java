package martak.jjdd4.aftercourse.task3.jms;

import martak.jjdd4.aftercourse.task3.data.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:/jms/topic/ISA.TOPIC"
        ),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"
        )
})

public class RepositoryWriterSubscriberBean implements MessageListener {

    private static Logger LOG = LoggerFactory.getLogger(RepositoryWriterSubscriberBean.class);

    @Inject
    private MessageRepository messageRepository;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                LOG.info("Received message {}:", text);
            }
        } catch (JMSException e) {
            LOG.error("Receiving message failed", e.getMessage());
        }
    }
}
