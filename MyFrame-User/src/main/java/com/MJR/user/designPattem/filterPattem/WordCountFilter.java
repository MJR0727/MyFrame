package com.MJR.user.designPattem.filterPattem;

/**
 * @author Mjr
 * @version 1.0
 * @description:
 * @date 2023/2/21 16:28
 */
public class WordCountFilter extends AbstractFilter{

    @Override
    public boolean doFilter(ArticleContext articleContext) {
        Article article = articleContext.getArticle();
        if(article.getWordCount()>1000){
            return false;
        }
        return true;
    }
}
