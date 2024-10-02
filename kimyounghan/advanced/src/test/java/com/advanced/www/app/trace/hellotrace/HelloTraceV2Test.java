package com.advanced.www.app.trace.hellotrace;

import com.advanced.www.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    @Test
    void begin_end() {
        // HelloTraceV1 trace = new HelloTraceV1();
        // TraceStatus status = trace.begin("hello");
        // trace.end(status);
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        // HelloTraceV1 trace = new HelloTraceV1();
        // TraceStatus status = trace.begin("hello");
        // trace.exception(status, new IllegalStateException());
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}
