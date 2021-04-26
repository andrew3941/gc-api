package com.preving.intranet.gestioncentrosapi.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * Created by felipe on 25/04/20.
 */
@Configuration
@Profile("prod")
public class RabbitConfigApplication {

    @Value(value="preving.topic")
    public String EXCHANGE_NAME;

    @Value(value="${queue.name}")
    public String ROUTING_KEY;

    @Value(value="${queue.name}")
    private String QUEUE_NAME;
    private static final boolean IS_DURABLE_QUEUE = true;

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, IS_DURABLE_QUEUE);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageReceiver receiver() {
        return new MessageReceiver();
    }

//    @Bean
//    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
//        return new MessageListenerAdapter(receiver, MessageReceiver.RECEIVE_METHOD_NAME);
//    }
}
