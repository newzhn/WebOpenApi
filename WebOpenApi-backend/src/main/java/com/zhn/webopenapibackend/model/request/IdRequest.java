package com.zhn.webopenapibackend.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 只包含id请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class IdRequest implements Serializable {

    /**
     * id
     */
    @NotNull(message="接口Id不能为空")
    private Long id;

    private static final long serialVersionUID = 1L;
}