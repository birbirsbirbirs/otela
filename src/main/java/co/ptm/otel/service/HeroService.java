package co.ptm.otel.service;

import co.ptm.otel.model.Book;
import co.ptm.otel.model.Hero;
import io.micrometer.tracing.BaggageInScope;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeroService {
    private final Tracer tracer;
    private final RestTemplate restTemplate;

    public Hero getHero(){

        Span otelaSpan = tracer.nextSpan().name("otelaSpan");
        otelaSpan.tag("heroTagKey","heroTagValue");

        try(Tracer.SpanInScope spanInScope = tracer.withSpan(otelaSpan.start())){
            try(BaggageInScope baggageInScope = tracer.createBaggageInScope("heroKey", "heroValue")){
                Hero hero = Hero.builder().name(UUID.randomUUID().toString()).power(UUID.randomUUID().toString()).build();
                log.info("returning: {}", hero);
                return hero;
            }
        }finally {
            otelaSpan.end();
        }

    }


    public Book getBook(){

        Span otelaSpan = tracer.nextSpan().name("otelaSpan");
        otelaSpan.tag("heroTagKey","heroTagValue");

        try(Tracer.SpanInScope spanInScope = tracer.withSpan(otelaSpan.start())){
            try(BaggageInScope baggageInScope = tracer.createBaggageInScope("heroKey", "heroValue")){
                try (BaggageInScope baggageInScopeSecond = tracer.createBaggageInScope("heroKeySecond", "heroValueSecond")) {
                    Book book = restTemplate.getForObject("http://localhost:8081/api/book", Book.class);
                    log.info("returning: {}", book);
                    return book;
                }
            }
        }finally {
            otelaSpan.end();
        }


    }

}
