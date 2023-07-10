package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)") //파라미터의 @Retry 타입 정보가 적용된다고 보면 됨
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} args = {}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count = {}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
                // 실제 객체 실행시 예외가 터지면 catch로 잡히고 for문이 다시 돌아감
            }catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }

}
