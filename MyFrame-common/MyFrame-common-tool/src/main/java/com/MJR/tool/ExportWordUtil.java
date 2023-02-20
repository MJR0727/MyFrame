package com.MJR.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Mjr
 * @version 1.0
 * @description: 导出Word文件工具类
 * @date 2023/2/13 16:50
 */
public class ExportWordUtil {

    private static Configuration configuration = null;

    private static final String SUFFIX = ".doc";

    static{
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(ExportWordUtil.class,"/template/word");
    }

    /**
     * @description: 导出Word方法 map:数据源，title：文件名，ftlname：模板文件名
     * @author Mjr
     * @date 2023/2/13 16:59
     * @version 1.0
     */
    public static void exprotWord(Map map, String title, String ftlName) throws Exception {
        Template template = configuration.getTemplate(ftlName);
        File file = null;
        InputStream inputStream = null;
        ServletOutputStream out = null;
        file = createFile(map,template);
        inputStream = new FileInputStream(file);
        String fileName = title + SUFFIX;
        HttpServletResponse response = SpringContextUtils.getHttpServletResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/maword");
        response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        out = response.getOutputStream();
        byte[] buffer = new byte[512];
        int byteToRead = -1;
        while((byteToRead = inputStream.read(buffer))!=-1){
            out.write(buffer,0,byteToRead);
        }
        if(inputStream!=null){
            inputStream.close();
        }
        if(out != null){
            out.close();
        }
        if(file != null){
            file.delete();
        }
    }

    public static File createFile(Map dataMap,Template template) throws Exception {
        File file = new File("init.doc");
        Writer out = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
        template.process(dataMap,out);
        out.close();
        return file;
    }

}
