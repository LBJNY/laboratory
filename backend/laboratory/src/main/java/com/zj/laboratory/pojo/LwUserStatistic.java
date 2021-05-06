package com.zj.laboratory.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户统计信息(lw_user_statistic)实体类
 *
 * @author lbj
 * @since 2021-04-19 13:36:47
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwUserStatistic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *  id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 服务委托总单数量
     */
    private Integer serviceCount;
    /**
     * 进场单总数量
     */
    private Integer entryCount;
    /**
     * 反馈次数
     */
    private Integer feedCount;
    /**
     * 登陆次数
     */
    private Integer loginCount;
    /**
     * 服务委托单通过数量
     */
    private Integer servicePassCount;
    /**
     * 服务委托单审核失败数量
     */
    private Integer serviceFailCount;
    /**
     * 进场单审核通过数量
     */
    private Integer entryPassCount;
    /**
     * 进场单审核失败数量
     */
    private Integer entryFailCount;

}