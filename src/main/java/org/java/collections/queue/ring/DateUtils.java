package org.java.collections.queue.ring;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
 
    public static String now() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }
     
    public static String plusSeconds(int seconds) {
        LocalDateTime time = LocalDateTime.now();
        time.plusSeconds(seconds);
        return time.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }
}