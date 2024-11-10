package com.advanced.www.app.trace.logtrace;

import com.advanced.www.app.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
