package com.example.short_chains.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.short_chains.pojo.ChainInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author plumsun Created on 2023/12/3
 */
@Mapper
public interface ChainInfoMapper extends BaseMapper<ChainInfo> {

    List<String> querySortChainUrlList();
}
