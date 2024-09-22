package com.example.short_chains.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Redisson config.
 *
 * @author LiHaoHan on 2024/9/22
 */
@Slf4j
@Configuration
public class RedissonConfig {

    /**
     * 单机模式
     *
     * @return redisson client
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 设置序列化方式为 JSON
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer()
                .setAddress("redis://app_redis:6379");
        log.info("Redisson init start ......");
        return Redisson.create(config);
    }

    /**
     * 配置redisson集群
     * @return
     */
    // @Bean(destroyMethod = "shutdown")
    // public RedissonClient getRedissonClient() {
    //     List<String> clusterNodes = new ArrayList<>();
    //     Config config = new Config();
    //     //对象编码选择纯字符串编码
    //     config.setCodec(new JsonJacksonCodec());
    //     config.setTransportMode(TransportMode.NIO);
    //     ClusterServersConfig clusterServersConfig = config.useClusterServers()
    //             .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
    //     //redis连接心跳检测，防止一段时间过后，与redis的连接断开
    //     clusterServersConfig.setPingConnectionInterval(32000);
    //     log.info("Redisson init start ......");
    //     return Redisson.create(config);
    // }

}
