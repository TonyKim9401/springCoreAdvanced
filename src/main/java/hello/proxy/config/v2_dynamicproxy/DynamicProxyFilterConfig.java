package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;


@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        LogTraceFilterHandler handler = new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS);
        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, handler);
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace){
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        LogTraceFilterHandler handler = new LogTraceFilterHandler(orderService, logTrace, PATTERNS);
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, handler);
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace){
        OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        LogTraceFilterHandler handler = new LogTraceFilterHandler(orderController, logTrace, PATTERNS);
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, handler);

    }


}
