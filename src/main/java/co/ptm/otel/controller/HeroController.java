package co.ptm.otel.controller;

import co.ptm.otel.model.Book;
import co.ptm.otel.model.Hero;
import co.ptm.otel.service.HeroService;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hero")
public class HeroController {
    private final HeroService heroService;
    private final Tracer tracer;

    @GetMapping
    public Hero getHero(){
        log.info("calling getHero");
      return  heroService.getHero();
    }

    @GetMapping("/book")
    public Book getBook(){
        return  heroService.getBook();
    }

}
