package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloTraceV2Test {

    @Test
    void begin_end() {
        final HelloTraceV2 trace = new HelloTraceV2();
        final TraceStatus status1 = trace.begin("hello");

        final TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        final TraceStatus status3 = trace.beginSync(status2.getTraceId(), "hello3");
        trace.end(status3);

        trace.end(status2);

        trace.end(status1);
    }

    @Test
    void begin_exception() {
        final HelloTraceV2 trace = new HelloTraceV2();
        final TraceStatus status = trace.begin("hello");

        final TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());

        trace.exception(status, new IllegalStateException());
    }

}