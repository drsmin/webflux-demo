package kr.pe.drs.webfluxdemo;

import io.netty.buffer.ByteBuf;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.netty.ByteBufFlux;
import reactor.netty.NettyOutbound;
import reactor.netty.tcp.TcpServer;

@EnableWebFlux
@SpringBootApplication
public class WebfluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }

    @Bean
    public void start() {
        TcpServer.create()
                .port(8089)
                .handle((nettyInbound, nettyOutbound) -> {
                    ByteBufFlux receive = nettyInbound.receive();
                    NettyOutbound send = nettyOutbound.send((Publisher<ByteBuf>)receive.retain());
                    return (Publisher<Void>)send;
                })
                // 1 줄로도 가능
                //.handle((nettyInbound, nettyOutbound) -> nettyOutbound.send(nettyInbound.receive().retain()))
                .bindNow();
    }
}
