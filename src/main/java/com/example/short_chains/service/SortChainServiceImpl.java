package com.example.short_chains.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.short_chains.exception.BusinessException;
import com.example.short_chains.pojo.ChainInfo;
import com.example.short_chains.pojo.ConstantEnum;
import com.example.short_chains.pojo.StatusEnum;
import com.example.short_chains.repository.ChainInfoMapper;
import com.example.short_chains.resp.Response;
import com.example.short_chains.utils.SortGenerationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author plumsun Created on 2023/12/3
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SortChainServiceImpl extends ServiceImpl<ChainInfoMapper, ChainInfo> implements SortChainService {

    private final SortGenerationUtils sortGenerationUtils;

    private final RedissonClient redisson;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response generation(String url) {
        RLock lock = redisson.getLock(ConstantEnum.SORT_CHAIN_KEY + ":" + url);
        if (!lock.tryLock()) {
            return Response.err("系统繁忙，请稍后再试");
        }

        StringBuilder sortChain = null;
        try {
            LambdaQueryWrapper<ChainInfo> wrapper = Wrappers
                    .lambdaQuery(ChainInfo.class)
                    .eq(ChainInfo::getLongChainUrl, url);
            ChainInfo one = this.getOne(wrapper);

            if (Objects.isNull(one)) {
                one = new ChainInfo();
            }

            sortChain = new StringBuilder(sortGenerationUtils.generation(url));

            // 先通过布隆过滤器过滤当前短链
            RBloomFilter<String> filter =
                    this.redisson.getBloomFilter(ConstantEnum.SORT_CHAIN_KEY.getValue());

            AtomicBoolean atomicBoolean = new AtomicBoolean(true);

            while (atomicBoolean.get()) {
                boolean contains = filter.contains(url);
                if (contains) {
                    sortChain.append(IdUtil.getSnowflake().nextIdStr());
                } else {
                    filter.addAsync(sortChain.toString());
                    atomicBoolean.set(false);
                }
            }
            log.info("{} 最终生成的短链为：{}", url, sortChain);
            one.setSortChainUrl(sortChain.toString());
            one.setLongChainUrl(url);

            this.save(one);
        } catch (Exception e) {
            log.error("generation()---->生成短链失败", e);
            throw new BusinessException(e);
        } finally {
            lock.unlock();
        }
        return Response.success(sortChain.toString());
    }


    @Override
    public Response findLongChain(String sortChainUrl) {
        // 先通过布隆过滤器过滤当前短链
        RBloomFilter<String> filter =
                this.redisson.getBloomFilter(ConstantEnum.SORT_CHAIN_KEY.getValue());
        boolean contains = filter.contains(sortChainUrl);
        if (!contains) {
            return Response.err();
        }

        LambdaQueryWrapper<ChainInfo> wrapper = Wrappers.lambdaQuery(ChainInfo.class)
                .eq(ChainInfo::getSortChainUrl, sortChainUrl)
                .eq(ChainInfo::getStatus, StatusEnum.EFFECTIVE);

        Optional<ChainInfo> optional = Optional.ofNullable(this.getOne(wrapper));

        return optional.map(Response::success).orElseGet(Response::err);
    }
}
