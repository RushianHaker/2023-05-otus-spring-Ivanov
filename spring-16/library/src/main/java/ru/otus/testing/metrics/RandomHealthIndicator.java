package ru.otus.testing.metrics;

import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnEnabledHealthIndicator("random")
public class RandomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        var indicatorWork = true;
        var status = Health.up();

        double chance = ThreadLocalRandom.current().nextDouble();
        if (chance > 0.9) {
            indicatorWork = false;
            status = Health.down(new RuntimeException("The indicator is down!"));
        }

        Map<String, Object> details = new HashMap<>();
        details.put("chance", chance);
        details.put("indicatorWork", indicatorWork);

        return status.withDetails(details).build();
    }
}
