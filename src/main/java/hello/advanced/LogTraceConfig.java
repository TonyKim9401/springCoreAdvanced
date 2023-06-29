package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    /**
     * Singleton concurrent thread issue 발생
     */
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
