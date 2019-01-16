package com.sample.applicationadmin.generate.service.impl;

import com.sample.applicationadmin.generate.dao.SysGeneratorDao;
import com.sample.applicationadmin.generate.dao.SysOracleGeneratorDao;
import com.sample.applicationadmin.generate.entity.ResultMap;
import com.sample.applicationadmin.generate.service.SysGeneratorService;
import com.sample.applicationadmin.generate.utils.GenUtils;
import com.sample.applicationadmin.util.Constant;
import com.sample.applicationadmin.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @description:代码生成器
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:50
 */
@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {

    @Autowired
    private SysGeneratorDao sysGeneratorDao;
    @Autowired
    private SysOracleGeneratorDao sysOracleGeneratorDao;


    @Override
    public Map<String, String> queryTable(String tableName) {
        if ("ORACLE".equals(Constant.USE_DATA)) {
            Map<String, String> objectMap = sysOracleGeneratorDao.queryTable(tableName);

            //oracle需转为驼峰命名
            Map<String, String> map = new HashMap<String, String>();
            for (String key : objectMap.keySet()) {
                String mapKey = StringUtils.lineToHump(key);
                map.put(mapKey, objectMap.get(key));
            }
            return map;
        }
        return sysGeneratorDao.queryTable(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        if ("ORACLE".equals(Constant.USE_DATA)) {
            List<ResultMap> list = sysOracleGeneratorDao.queryColumns(tableName);

            //oracle
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            for (ResultMap stringObjectMap : list) {
                // 获取实体类的所有属性，返回Field数组
                Field[] field = stringObjectMap.getClass().getDeclaredFields();

                Map<String, String> objectMap = new HashMap<String, String>();
                for (int j = 0; j < field.length; j++) {
                    // 获取属性的名字
                    String name = field[j].getName();
                    // 将属性的首字符大写，方便构造get，set方法
                    String Name = name.substring(0, 1).toUpperCase() + name.substring(1);

                    try {
                        Method m = stringObjectMap.getClass().getMethod("get" + Name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(stringObjectMap);
                        objectMap.put(name, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mapList.add(objectMap);
            }
            return mapList;
        }
        return sysGeneratorDao.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableNames);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableNames);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
       // }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
