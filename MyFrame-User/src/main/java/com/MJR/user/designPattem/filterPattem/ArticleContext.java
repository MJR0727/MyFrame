package com.MJR.user.designPattem.filterPattem;

import lombok.Data;

/**
 * @author Mjr
 * @version 1.0
 * @description:
 * @date 2023/2/21 16:22
 */
@Data
public class ArticleContext {

    private Article article;

    public ArticleContext(Article article){
        this.article = article;
    }
}
