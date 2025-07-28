package co.ptm.otel.config;


import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OtelAWebFilter extends OncePerRequestFilter {
    private final Tracer  tracer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String heroXCorrelationValue = request.getHeader("hero-x-correlation");
            String name=request.getParameter("name");

            if(heroXCorrelationValue != null){
                MDC.put("hero-x-correlation", heroXCorrelationValue);
            }

            if(name != null){
                MDC.put("cid",name);
            }
            filterChain.doFilter(request,response);
        }finally{
            MDC.clear();
        }

    }
}
