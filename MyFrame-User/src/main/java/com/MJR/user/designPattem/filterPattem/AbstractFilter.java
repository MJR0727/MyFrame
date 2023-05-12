package com.MJR.user.designPattem.filterPattem;

/**
 * @author Mjr
 * @version 1.0
 * @description:
 * @date 2023/2/21 16:25
 */
public abstract class AbstractFilter implements FilterTemplate{

    public abstract boolean doFilter(ArticleContext articleContext);

    public void filter(ArticleContext articleContext){
        doFilter(articleContext);
        sentMsg("msg");
    }

    public void sentMsg(String msg){
        //send msg
    }
}
