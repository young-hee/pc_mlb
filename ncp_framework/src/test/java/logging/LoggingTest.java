package logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by narusas on 2016. 5. 18..
 */
@Slf4j
public class LoggingTest {
    @Test
    public void logging() {
        log.error("{}", new NullPointerException());
        log.error("{}", new NullPointerException());
        log.error("{}", new NullPointerException());
        log.error("{}", new NullPointerException());
    }
}
