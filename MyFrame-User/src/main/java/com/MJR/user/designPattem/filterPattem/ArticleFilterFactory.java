package com.MJR.user.designPattem.filterPattem;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mjr
 * @version 1.0
 * @description: 文章过滤器工厂 根据枚举初始化过滤器对象列表。
 * @date 2023/2/21 16:36
 */
public class ArticleFilterFactory {

    public static List<FilterTemplate> createArticleFilterList(List<ArticleFilterEnum> articleFilterEnumList){
        List<FilterTemplate> filterList = null;
        if(!CollectionUtils.isEmpty(articleFilterEnumList)){
             filterList = articleFilterEnumList.stream()
                    .map(articleFilterEnum -> createFilter(articleFilterEnum))
                    .collect(Collectors.toList());
        }
        return filterList;
    }

    private static FilterTemplate createFilter(ArticleFilterEnum articleFilterEnum){
        FilterTemplate filter = null;
        switch (articleFilterEnum){
            case WORD_FILTER:
                filter = new WordCountFilter();
                break;
            default:
                break;
        }
        return filter;
    }
}
