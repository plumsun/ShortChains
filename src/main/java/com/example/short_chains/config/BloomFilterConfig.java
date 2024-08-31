package com.example.short_chains.config;

import com.example.short_chains.pojo.ConstantEnum;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author plumsun on 2024/8/31
 */
@RequiredArgsConstructor
@Configuration
public class BloomFilterConfig {

    private final RedissonClient redissonClient;

    private static final long EXPECTED_INSERTIONS = 200L;

    private static final double FALSE_PROBABILITY = 0.01d;


    @PostConstruct
    public void bloomFilterInit() {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(ConstantEnum.SORT_CHAIN_KEY.getValue());
        bloomFilter.tryInit(EXPECTED_INSERTIONS, FALSE_PROBABILITY);
    }
}
