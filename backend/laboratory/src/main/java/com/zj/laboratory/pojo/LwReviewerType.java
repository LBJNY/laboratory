package com.zj.laboratory.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审查员类型(lw_reviewer_type)实体类
 *
 * @author lbj
 * @since 2021-04-20 09:09:43
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwReviewerType  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 显示顺序
     */
    private Integer orderNum;

}