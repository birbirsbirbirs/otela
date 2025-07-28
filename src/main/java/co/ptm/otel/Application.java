package co.ptm.otel;

import io.micrometer.context.ContextRegistry;
import jakarta.servlet.Filter;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Hooks;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Hooks.enableAutomaticContextPropagation();
        ContextRegistry.getInstance().registerThreadLocalAccessor(
          "cid",
                () -> MDC.get("cid"),
                cid -> MDC.put("cid",cid),
                () -> MDC.remove("cid")
        );

        SpringApplication.run(Application.class, args);
    }

    @Bean
    Filter correlationFilter(){
        return (request,response,chain)->{
            String name=request.getParameter("name");
            if(name != null){
                MDC.put("cid",name);
            }
            chain.doFilter(request,response);
        };
    }

}
