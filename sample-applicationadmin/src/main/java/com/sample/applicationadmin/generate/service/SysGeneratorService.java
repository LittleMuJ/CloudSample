package com.sample.applicationadmin.generate.service;

import java.util.List;
import java.util.Map;

/**
 * @description:代码生成器
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:50
 */
public interface SysGeneratorService {
    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 生成代码
     */
    byte[] generatorCode(String[] tableNames);
}
