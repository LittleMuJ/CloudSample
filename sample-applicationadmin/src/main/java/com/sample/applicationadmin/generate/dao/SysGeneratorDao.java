package com.sample.applicationadmin.generate.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:代码生成器
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:52
 */
@Mapper
public interface SysGeneratorDao {
    Map<String,String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
