package com.MJR.tool;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 客户端工具类
 * 
 * @author ruoyi
 */
public class ServletUtils
{

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param msg 待渲染的字符串
     * @param httpStatusCode 状态码
     */
    public static void renderSuccessMsg(HttpServletResponse response, Integer httpStatusCode,String msg)
    {
        try
        {
            response.setStatus(httpStatusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void renderFailMsg(HttpServletResponse response, Integer httpStatusCode,String msg)
    {
        try
        {
            response.setStatus(httpStatusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
