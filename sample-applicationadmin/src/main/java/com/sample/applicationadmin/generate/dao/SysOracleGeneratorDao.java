package com.sample.applicationadmin.generate.dao;

import com.sample.applicationadmin.generate.entity.ResultMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:53
 */
@Mapper
public interface SysOracleGeneratorDao {
    Map<String,String> queryTable(String tableName);

    List<ResultMap> queryColumns(String tableName);
}
