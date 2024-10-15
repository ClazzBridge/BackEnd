package com.example.academy.config;

import com.example.academy.domain.redis.ChatMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, ChatMessage> redisTemplate(
      RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, ChatMessage> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    // Redis에 저장되는 키와 값의 직렬화 방식 설정
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

    return template;
  }
}