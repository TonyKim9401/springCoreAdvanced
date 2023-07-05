package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // cglib 을 만드는 코드
        enhancer.setSuperclass(ConcreteService.class); // 어떤 클래스를 기반으로 만들건지 설정, Proxy의 부모 타입 지정
        enhancer.setCallback(new TimeMethodInterceptor(target));
//        Object proxy = enhancer.create();// proxy 생성
        ConcreteService proxy = (ConcreteService) enhancer.create();// proxy 생성

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }

}
