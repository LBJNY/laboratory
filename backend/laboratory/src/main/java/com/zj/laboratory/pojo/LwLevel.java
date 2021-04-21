package com.zj.laboratory.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审查等级表(lw_level)实体类
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwLevel  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 等级int类型
     */
    private Integer level;
    /**
     * 等级描述
     */
    private String des;
    /**
     * 等级状态0->禁用；1->启用
     */
    private Integer status;

}