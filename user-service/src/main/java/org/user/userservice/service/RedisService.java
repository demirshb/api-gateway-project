package org.user.userservice.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    void redisSaveToken(String key, Object value, long timeout, TimeUnit timeUnit);
}
