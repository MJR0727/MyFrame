package com.MJR.user.designPattem;

import lombok.Data;

@Data
public class SkuDO {

    private Long skuId;

    private String skuName;

    private Integer promotionId;

    private Integer couponId;

}
