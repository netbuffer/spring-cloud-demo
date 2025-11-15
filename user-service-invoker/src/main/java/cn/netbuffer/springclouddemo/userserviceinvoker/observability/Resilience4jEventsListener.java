package cn.netbuffer.springclouddemo.userserviceinvoker.observability;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.event.RetryOnErrorEvent;
import io.github.resilience4j.retry.event.RetryOnSuccessEvent;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Resilience4jEventsListener {

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;
    private final MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        // 针对现有实例注册监听
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(this::registerCircuitBreakerListeners);
        retryRegistry.getAllRetries().forEach(this::registerRetryListeners);

        // 针对新创建实例注册监听
        circuitBreakerRegistry.getEventPublisher()
                .onEntryAdded(event -> registerCircuitBreakerListeners(event.getAddedEntry()))
                .onEntryReplaced(event -> registerCircuitBreakerListeners(event.getNewEntry()));

        retryRegistry.getEventPublisher()
                .onEntryAdded(event -> registerRetryListeners(event.getAddedEntry()))
                .onEntryReplaced(event -> registerRetryListeners(event.getNewEntry()));
    }

    private void registerCircuitBreakerListeners(CircuitBreaker cb) {
        cb.getEventPublisher().onStateTransition(this::onCircuitBreakerStateTransition);
    }

    private void registerRetryListeners(Retry retry) {
        retry.getEventPublisher()
                .onError(this::onRetryError)
                .onSuccess(this::onRetrySuccess);
    }

    private void onCircuitBreakerStateTransition(CircuitBreakerOnStateTransitionEvent e) {
        String name = e.getCircuitBreakerName();
        String stateTransition = e.getStateTransition().name();
        log.warn("circuitBreaker state transition: name={} transition={}", name, stateTransition);
        Counter.builder("resilience4j_circuitbreaker_state_transitions_total")
                .tag("name", name)
                .tag("transition", stateTransition)
                .register(meterRegistry)
                .increment();
    }

    private void onRetryError(RetryOnErrorEvent e) {
        String name = e.getName();
        int attempt = e.getNumberOfRetryAttempts();
        Throwable lastThrowable = e.getLastThrowable();
        log.warn("retry onError: name={} attempt={} ex={}", name, attempt,
                lastThrowable != null ? lastThrowable.getClass().getSimpleName() : "null");
        Counter.builder("resilience4j_retry_errors_total")
                .tag("name", name)
                .register(meterRegistry)
                .increment();
    }

    private void onRetrySuccess(RetryOnSuccessEvent e) {
        String name = e.getName();
        int attempt = e.getNumberOfRetryAttempts();
        log.info("retry onSuccess: name={} attempts={}", name, attempt);
        Counter.builder("resilience4j_retry_success_total")
                .tag("name", name)
                .register(meterRegistry)
                .increment();
    }
}
