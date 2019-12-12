package com.ruiyun.message;

import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringRedisMessageTestApplication {
	
	public static final Logger log = LoggerFactory.getLogger(SpringRedisMessageTestApplication.class);
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext cxt = SpringApplication.run(SpringRedisMessageTestApplication.class, args);
		
		CountDownLatch latch = cxt.getBean(CountDownLatch.class);
		StringRedisTemplate redisTemplate = cxt.getBean(StringRedisTemplate.class);
		
		redisTemplate.opsForList().leftPush("redis", "test");
		redisTemplate.convertAndSend("chat", "hello world redis");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			log.error("countdownlatch error", e);
		}
		
	    System.exit(0);
	}
	
	/**
	 * 配置redis容器
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
		      MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter,  new PatternTopic("chat"));
		return container;
	}
	/**
	 * 监听listenner
	 * @param receiver
	 * @return
	 */
	@Bean
	public MessageListenerAdapter listennerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	/**
	 * 向spring ioc 容器中注入 Receiver bean
	 * @param latch
	 * @return
	 */
	@Bean
	public Receiver receiver(CountDownLatch latch){
		Receiver receiver = new Receiver(latch);
		return receiver;
	}
	
	/**
	 * 配置countDownLatch
	 * @return
	 * @throws UnknownHostException
	 */
	@Bean
	public CountDownLatch countDownLatch( ) {
		CountDownLatch latch = new CountDownLatch(1);
		return latch;
	}
	
}
