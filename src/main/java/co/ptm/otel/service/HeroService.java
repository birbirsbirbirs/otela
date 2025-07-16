package co.ptm.otel.service;

import co.ptm.otel.model.Hero;
import io.micrometer.tracing.BaggageInScope;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeroService {
    private final Tracer tracer;

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
}
