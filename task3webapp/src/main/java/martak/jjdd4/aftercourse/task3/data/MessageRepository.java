package martak.jjdd4.aftercourse.task3.data;

import javax.ejb.Singleton;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class MessageRepository {

    private Map<LocalDateTime, String> messages = new ConcurrentHashMap<>();

    public void addMessage (String message) {
        messages.put(LocalDateTime.now(), message);
    }

    public  Map<LocalDateTime, String> getMessages() {
        return messages;
    }
}
