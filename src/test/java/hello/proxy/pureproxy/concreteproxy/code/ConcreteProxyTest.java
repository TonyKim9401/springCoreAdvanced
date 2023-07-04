package hello.proxy.pureproxy.concreteproxy.code;

import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        //부모 타입(concreteLogic)에 자식 타입(timeProxy)를 다형성 활용하여 주입
        ConcreteClient concreteClient = new ConcreteClient(timeProxy);
        concreteClient.execute();
    }


}
