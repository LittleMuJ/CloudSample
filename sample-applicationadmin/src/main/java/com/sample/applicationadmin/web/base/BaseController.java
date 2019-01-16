package com.sample.applicationadmin.web.base;

import com.sample.applicationadmin.common.interceptor.ShiroUtils;
import com.sample.applicationadmin.util.Code;
import com.sample.applicationadmin.web.sys.entity.SysUserEntity;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:25
 */
public class BaseController {
    /**
     * 抽取由逗号分隔的主键列表
     * @param ids
     * @return
     */
    protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.hasText(ids)) {
            for (String id : ids.split(",")) {
                if (StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }


    /** 把 ,连接的字符串 转化为 int[]数组
     * */
    protected int[] str2Arr(String delitems){
        delitems=delitems.trim();
        String[] idStrArr = delitems.split(",");

        int[] idArr=new int[idStrArr.length];

        for (int i=0;i<idStrArr.length;i++) {
            idArr[i]=Integer.parseInt(idStrArr[i]);
        }
        return idArr;
    }

    /** 设置响应代码 */
    protected Object actionResult(Code code){
        return actionResult(code,null);
    }

    /** 设置响应代码
     * */
    protected Object actionResult(Code code, Object message){
        Map<String, Object> map = new HashMap<String, Object>();
		/*if (data != null) {
			map.put("result", data);
		}*/
        map.put("code", code.value());
        map.put("message", message);
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }

    /** 设置响应代码
     * */
    protected Object actionResult(Code code, Object message,Object data){
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null) {
            map.put("result", data);
        }
        map.put("code", code.value());
        map.put("message", message);
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }


    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
