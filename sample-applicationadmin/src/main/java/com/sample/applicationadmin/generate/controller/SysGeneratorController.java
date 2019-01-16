package com.sample.applicationadmin.generate.controller;

import com.alibaba.fastjson.JSON;
import com.sample.applicationadmin.generate.service.SysGeneratorService;
import com.sample.applicationadmin.util.DateUtils;
import com.sample.applicationadmin.web.base.BaseController;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 11:49
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController extends BaseController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @RequestMapping("/code")
    public  void code(String tables, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        //tableNames = JSON.parseArray(tables).toArray(tableNames);

        byte[] data = sysGeneratorService.generatorCode(tables);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"AutoCode"
                + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS) + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
