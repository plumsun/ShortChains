package com.example.short_chains.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author plumsun Created on 2023/12/3
 */
@TableName(value = "chain_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChainInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "long_chain_url")
    private String longChainUrl;

    @TableField(value = "sort_chain_url")
    private String sortChainUrl;

    /**
     * @see com.example.short_chains.pojo.StatusEnum
     */
    @TableField(value = "status")
    private String status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "create_update_user", fill = FieldFill.INSERT)
    private String createUpdateUser = "0";

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "last_update_user", fill = FieldFill.INSERT_UPDATE)
    private String lastUpdateUser = "0";
}
