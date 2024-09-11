package com.example.short_chains.utils;

import cn.hutool.core.codec.Base62;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * @author plumsun Created on 2023/12/3
 */
@Slf4j
@Component
public class SortGenerationUtils {

    @Value("${system.addr}")
    private String addr;

    public String generation(String url) {
        byte[] bytes = Hashing.murmur3_32_fixed().hashUnencodedChars(url).asBytes();
        String encode = Base62.encode(bytes);
        ServerProperties properties = new ServerProperties();
        // NOTE: 17:27 此处链接写死，未来会通过数据库维护
        String sortUrl = "http://" + addr + ":" + properties.getPort() + "/" + encode;
        log.info("长链：{}，初始短链生成成功:{}", url, sortUrl);
        return sortUrl;
    }
}
