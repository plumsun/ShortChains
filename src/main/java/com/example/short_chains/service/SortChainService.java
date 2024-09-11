package com.example.short_chains.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.short_chains.pojo.ChainInfo;
import com.example.short_chains.resp.Response;

/**
 * @author plumsun Created on 2023/12/3
 */
public interface SortChainService extends IService<ChainInfo> {

    Response generation(String url);

    Response findLongChain(String sortChainUrl,String status);
}
