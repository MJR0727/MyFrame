package com.MJR.user.designPattem.filterPattem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mjr
 * @version 1.0
 * @description: article过滤器实际应用  传入实际的article分装成context然后遍历过滤器列表进行过滤
 * @date 2023/2/21 16:49
 */
public class ArticleDemo {

    private List<ArticleFilterEnum> articleFilterEnumList;

    private List<FilterTemplate> filterList;

    public void init(){
        this.filterList = ArticleFilterFactory.createArticleFilterList(articleFilterEnumList);
    }

    public void doFilter(List<Article> articleList){
        List<Article> collect = articleList.stream().filter(x -> {
            ArticleContext articleContext = new ArticleContext(x);
            return doFilter(articleContext);
        }).collect(Collectors.toList());
    }


    private boolean doFilter(ArticleContext articleContext){
        if(Objects.isNull(articleContext)||articleContext==null){
            return false;
        }
        for (FilterTemplate filterTemplate : this.filterList) {
            if (filterTemplate.doFilter(articleContext)) {
                return false;
            }
        }
        return true;
    }
}
