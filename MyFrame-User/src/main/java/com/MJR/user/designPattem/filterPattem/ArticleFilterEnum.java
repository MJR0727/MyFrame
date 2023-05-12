package com.MJR.user.designPattem.filterPattem;



public enum ArticleFilterEnum {

    WORD_FILTER(0,"字数过滤器");

    private int code;

    private String desc;

    ArticleFilterEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    //根据code查找枚举
    public static ArticleFilterEnum getBycode(int codeVal){
        for (ArticleFilterEnum articleFilterEnum : ArticleFilterEnum.values()) {
            if(articleFilterEnum.code==codeVal){
                return articleFilterEnum;
            }
        }
        //找不到枚举
        return null;
    }
}
