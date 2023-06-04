//package com.nhnacademy.minidooray_gateway.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.session.data.redis.RedisSessionRepository;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
//
//import java.time.Duration;
//
//@Configuration
////@EnableSpringHttpSession
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60) // 1 hour
//public class RedisConfig extends AbstractHttpSessionApplicationInitializer {
//
//
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    //private String password = "pwd";
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);
//        //redisConfig.setPassword(password);
//        return new LettuceConnectionFactory(redisConfig);
//    }
//
//    @Bean
//    public RedisOperations<String, Object> sessionRedisOperations() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        return template;
//    }
//
//    @Bean
//    public RedisSessionRepository sessionRepository(RedisOperations<String, Object> sessionRedisOperations) {
//        RedisSessionRepository redisSessionRepository = new RedisSessionRepository(sessionRedisOperations);
//
//        redisSessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(3));
//        return redisSessionRepository;
//    }
//
//
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(factory);
////        return template;
////    }
//
//
//
//}
