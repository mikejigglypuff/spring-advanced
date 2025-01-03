package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.config.wrapper.CachedBodyHttpServletRequest;
import org.example.expert.config.wrapper.CachedBodyHttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        CachedBodyHttpServletRequest cachedBodyRequest = new CachedBodyHttpServletRequest(request);

        log.info(
            "Request ID: {}\nTime: {}\nURI: {}\nBody: {}",
            request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE),
            LocalDateTime.now(),
            request.getRequestURI(),
            cachedBodyRequest.getReader()
        );

        return true;
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        @Nullable ModelAndView modelAndView
    ) throws Exception {
        CachedBodyHttpServletResponse cachedBodyResponse = new CachedBodyHttpServletResponse(response);
        log.info("Response Body: {}", cachedBodyResponse.getBody());
    }

}
