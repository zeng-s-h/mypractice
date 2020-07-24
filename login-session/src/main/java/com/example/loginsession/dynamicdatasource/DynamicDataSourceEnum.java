package com.example.loginsession.dynamicdatasource;

import lombok.Getter;

/**
 * @author 小白i
 * @date 2020/7/24
 */
@Getter
public enum DynamicDataSourceEnum {
    /**
     * 主数据库（写入功能）
     */
    MASTER("master"),
    /**
     * 从数据库，读取功能
     */
    SLAVE("slave");

    private final String dataSourceName;

    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

}
