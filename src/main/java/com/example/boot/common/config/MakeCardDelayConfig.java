package com.example.boot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MakeCardDelayConfig {

    /**
     * 延迟队列
     */
    private static final String DELAY_QUEUE = "makeCard.delay.queue";
    /**
     * DLX，dead letter发送到的 exchange
     * 延时消息就是发送到该交换机的
     */
    public static final String DELAY_EXCHANGE = "makeCard.delay.exchange";
    /**
     * routing key 名称
     * 具体消息发送在该 routingKey 的
     */
    public static final String DELAY_ROUTING_KEY = "makeCard.delay.routingKey";

    /**
     * 队列名称
     */
    public static final String ORDER_QUEUE_NAME = "makeCard.order.queue";
    /**
     * 交换机
     */
    private static final String ORDER_EXCHANGE_NAME = "makeCard.order.exchange";
    /**
     * 路由键
     */
    private static final String ORDER_ROUTING_KEY = "makeCard.order.routingKey";

    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue delayOrderQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);
        return new Queue(DELAY_QUEUE, true, false, false, params);
    }

    /**
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     * 这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，
     * 不会转发dog.puppy，也不会转发dog.guard，只会转发dog。
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE);
    }

    /**
     * 绑定交换机和队列，使用routingKey指定队列
     *
     * @return 绑定
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayOrderQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }

    /**
     * @return 接收队列（实际执行任务的队列）
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    /**
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
     **/
    @Bean
    public TopicExchange orderTopicExchange() {
        return new TopicExchange(ORDER_EXCHANGE_NAME);
    }

    /**
     * 绑定交换机和队列，使用routingKey指定队列
     * @return 绑定
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(ORDER_ROUTING_KEY);
    }

}
