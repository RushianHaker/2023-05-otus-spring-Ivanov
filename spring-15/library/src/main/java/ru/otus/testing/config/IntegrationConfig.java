package ru.otus.testing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.DirectChannelSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import ru.otus.testing.model.Incoming;
import ru.otus.testing.service.MessageService;

@Configuration
@Slf4j
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> messageChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public DirectChannelSpec homerSimpsonChannel() {
        DirectChannelSpec directChannelSpec = MessageChannels.direct("HomerSimpson");
        directChannelSpec.getObject().subscribe(this::processingIncomingMessage);
        return directChannelSpec;
    }

    @Bean
    public DirectChannelSpec margeSimpsonChannel() {
        DirectChannelSpec directChannelSpec = MessageChannels.direct("MargeSimpson");
        directChannelSpec.getObject().subscribe(this::processingIncomingMessage);
        return directChannelSpec;
    }

    @Bean
    public DirectChannelSpec bartSimpsonChannel() {
        DirectChannelSpec directChannelSpec = MessageChannels.direct("BartSimpson");
        directChannelSpec.getObject().subscribe(this::processingIncomingMessage);
        return directChannelSpec;
    }

    @Bean
    public DirectChannelSpec lisaSimpsonChannel() {
        DirectChannelSpec directChannelSpec = MessageChannels.direct("LisaSimpson");
        directChannelSpec.getObject().subscribe(this::processingIncomingMessage);
        return directChannelSpec;
    }

    @Bean
    public DirectChannelSpec meggeSimpsonChannel() {
        DirectChannelSpec directChannelSpec = MessageChannels.direct("MeggeSimpson");
        directChannelSpec.getObject().subscribe(this::processingIncomingMessage);
        return directChannelSpec;
    }

    @Bean
    public IntegrationFlow messageFlow(MessageService messageService) {
        return IntegrationFlow.from(messageChannel())
                .split()
                .handle(messageService, "sending")
                .split()
                .<Incoming, String>route(
                        Incoming::getRecipient,
                        mapping ->
                            mapping
                                .subFlowMapping("HomerSimpson", msg -> msg.channel(homerSimpsonChannel()))
                                .subFlowMapping("MargeSimpson", msg -> msg.channel(margeSimpsonChannel()))
                                .subFlowMapping("BartSimpson", msg -> msg.channel(bartSimpsonChannel()))
                                .subFlowMapping("LisaSimpson", msg -> msg.channel(lisaSimpsonChannel()))
                                .subFlowMapping("MeggeSimpson", msg -> msg.channel(meggeSimpsonChannel()))
                ).get();
    }

    private void processingIncomingMessage(Message<?> msg) {
        Incoming payload = (Incoming) msg.getPayload();
        log.info("Incoming message from {} to {}: {}", payload.getSender(), payload.getRecipient(), payload.getMessage());
    }
}
