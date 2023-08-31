package com.rany.acl.common.mdc;

import com.rany.acl.common.Constants;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * 添加HttpClient拦截器
 *
 * @author zhongshenwang
 */
public class HttpClientTraceIdInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        String traceId = MDC.get(Constants.TRACE_ID);
        // 当前线程调用中有traceId，则将该traceId进行透传
        if (traceId != null) {
            // 添加请求体
            httpRequest.addHeader(Constants.TRACE_ID, traceId);
        }
    }
}
