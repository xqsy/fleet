package dev.fleet.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String TRIP_COST_EXCHANGE = "trip-cost.exchange";
    public static final String CALCULATE_TRIP_COST_QUEUE = "trip-cost.calculate.queue";
    public static final String CALCULATE_TRIP_COST_ROUTING_KEY = "trip-cost.calculate";

    @Bean
    public DirectExchange tripCostExchange() {
        return new DirectExchange(TRIP_COST_EXCHANGE);
    }

    @Bean
    public Queue tripCostQueue() {
        return new Queue(CALCULATE_TRIP_COST_QUEUE, true);
    }

    @Bean
    public Binding tripCostBinding() {
        return BindingBuilder.bind(tripCostQueue()).to(tripCostExchange()).with(CALCULATE_TRIP_COST_ROUTING_KEY);
    }

    @Bean
    public MessageConverter rabbitMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
