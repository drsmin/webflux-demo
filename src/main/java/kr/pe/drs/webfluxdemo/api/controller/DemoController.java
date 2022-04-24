package kr.pe.drs.webfluxdemo.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class DemoController {

    @GetMapping("/demo/mono")
    public Mono<Map<String, String>> testMono(@RequestParam(name = "param", required = false) String param) {
        Map<String, String> result = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        result.put("now", sdf.format(now));

        if (param != null) result.put("param", param);

        return Mono.just(result);
    }
}
