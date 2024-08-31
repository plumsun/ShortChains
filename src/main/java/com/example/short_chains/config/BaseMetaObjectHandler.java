package com.example.short_chains.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 操作数据库前自动填充需要更新的内容，只支持单个对象，不支持批量插入更新时的填充
 *
 * @author Lihaohan
 */
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        setCreateTime(metaObject);

        // 更新时间
        setUpdaterTime(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新数据时，更新时间
        setUpdaterTime(metaObject);
    }

    /**
     * Sets create time.
     *
     * @param metaObject the meta object
     */
    private void setCreateTime(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "create_time", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
    }

    /**
     * Sets updater time.
     *
     * @param metaObject the meta object
     */
    private void setUpdaterTime(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "update_time", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
    }
}
