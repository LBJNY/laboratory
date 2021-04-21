package com.zj.laboratory.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 资金支持类型(lw_order_fundsupport)实体类
 *
 * @author lbj
 * @since 2021-04-20 09:09:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwOrderFundSupport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * （1：正常、0：无效）
     */
    private Integer status;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志 （0代表存在 1代表删除）
     */
    private Integer deleted;

}