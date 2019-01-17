package com.sample.applicationadmin.web.sys.dao;


import com.sample.applicationadmin.web.base.BaseDao;
import com.sample.applicationadmin.web.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author murunse
 * @email 939961241@qq.com
 * @date 2016年12月4日 下午6:46:16
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

    /**
     * 根据key，查询value
     */
    String queryByKey(String paramKey);

    /**
     * 根据key，更新value
     *
     * @param key
     * @param value
     * @return
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);

}
