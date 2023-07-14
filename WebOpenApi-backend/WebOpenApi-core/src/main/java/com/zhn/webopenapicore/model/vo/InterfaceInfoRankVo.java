package com.zhn.webopenapicore.model.vo;

import lombok.Data;

/**
 * The type Interface info rank vo.
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/13 22:33
 * @blog www.zhnblog.icu
 */
@Data
public class InterfaceInfoRankVo {
    private Long id;

    private String name;

    /**
     * 调用次数
     */
    private Integer num;
}
