package main.shared.infrastructure.exception.aspects;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    /**
     * Method that allow to log the execution of the methods annotated with @Loggable.
     */
    @Around("@annotation(Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Loggable loggable = signature.getMethod().getAnnotation(Loggable.class);

        try {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();

            if (loggable.logRequest()) {
                log.info("Log request is enabled, start logging request");
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
                ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) result;
                Collection<String> allHeaders = response.getHeaderNames();
                allHeaders.addAll(responseEntity.getHeaders().keySet());

                log.info("Request started {}, {}, {}, {}, {}, {}, {}, {},{}, {}, {}, {}, {}, {}, {}, {}",
                        StructuredArguments.kv("ClassName", signature.getDeclaringTypeName()),
                        StructuredArguments.kv("Method", signature.getName()),
                        StructuredArguments.kv("HttpMethod", request.getMethod()),
                        StructuredArguments.kv("RequestURI", request.getRequestURI()),
                        StructuredArguments.kv("QueryString", request.getQueryString()),
                        StructuredArguments.kv("ResponseHeaders", response.getHeaderNames()),
                        StructuredArguments.kv("ResponseStatusCode", responseEntity.getStatusCodeValue()),
                        StructuredArguments.kv("ResponseContentType", responseEntity.getHeaders().getContentType()),
                        StructuredArguments.kv("ResponseContentLength", responseEntity.getHeaders().getContentLength()),
                        StructuredArguments.kv("ResponseBody", responseEntity.getBody()),
                        StructuredArguments.kv("RequestHeaders", request.getHeaderNames()),
                        StructuredArguments.kv("ExecutionTime", System.currentTimeMillis() - start),
                        StructuredArguments.kv("RemoteAddr", request.getRemoteAddr()),
                        StructuredArguments.kv("RemoteHost", request.getRemoteHost()),
                        StructuredArguments.kv("RemotePort", request.getRemotePort()),
                        StructuredArguments.kv("LocalAddr", request.getLocalAddr())
                );
            }
            return result;
        } catch (Throwable e) {
            log.error("Method {} has thrown an exception", signature.getName());
            throw e;
        }
    }

}
