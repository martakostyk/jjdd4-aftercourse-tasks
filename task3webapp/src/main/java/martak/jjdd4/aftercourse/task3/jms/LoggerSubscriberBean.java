package martak.jjdd4.aftercourse.task3.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:/jms/topic/ISA.TOPIC"),
        @ActivationConfigProperty(
                propertyValue = "destinationType",
                propertyName = "javax.jms.Topic")
})

public class LoggerSubscriberBean implements MessageListener {

    private static Logger LOG = LoggerFactory.getLogger(LoggerSubscriberBean.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                LOG.info("Received message: {}", ((TextMessage) message).getText());
            }
        } catch (JMSException e) {
            LOG.error("Receiving message failed", e.getMessage());
        }
    }
}
