package vttp2022.miniproject02.server.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    @Value("${character.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;
    
    public void saveListOfDealsToRedis(String payload) {
        // save entire payload into redis
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set("deals", payload, Duration.ofMinutes(cacheTime));
    }

    public Optional<String> getListOfDealsFromRedis() {
        ValueOperations<String,String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get("deals");
        if (null == value)
            return Optional.empty();
        return Optional.of(value);
    }
    
}
