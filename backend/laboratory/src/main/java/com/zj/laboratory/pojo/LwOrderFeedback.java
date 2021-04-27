package com.zj.laboratory.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 反馈信息表(lw_order_feedback)实体类
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwOrderFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 服务委托单编号
     */
    private String serviceno;
    /**
     * 测试服务进度（0：全部完成、1：部分完成、2：没有完成）
     */
    private Integer serviceadvance;
    /**
     * 测试服务质量（0：很好、1：一般、2：较差）
     */
    private Integer servicequality;
    /**
     * 测试服务收费（0：非常合理、1：基本合理、2：不太合理）
     */
    private Integer servicecharge;
    /**
     * 满意度（0：非常满意、1：基本满意、3：不太满意）
     */
    private Integer satisfaction;
    /**
     * 其他建议
     */
    private String suggestions;
    /**
     * 申请单位和部门完工确认签字
     */
    private String signature;
    /**
     * 完工日期
     */
    private Date completion;
    /**
     * 状态（1：正常、0：无效）
     */
    private Integer status;

}