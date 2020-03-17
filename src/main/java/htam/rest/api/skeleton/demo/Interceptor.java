package htam.rest.api.skeleton.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {
    private long startTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        HashMap<String, String> accessLogParam = new HashMap<>();
        accessLogParam.put("method", request.getMethod());
        accessLogParam.put("uri", request.getRequestURI());
        accessLogParam.put("timeExecute", getExecuteTime());

        log.info("<accessLog> " + accessLogParam.toString());
    }

    private String getExecuteTime() {
        long timeExecute = System.currentTimeMillis() - startTime;
        return timeExecute + "ms";
    }
}