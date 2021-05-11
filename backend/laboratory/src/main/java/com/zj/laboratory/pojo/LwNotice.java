package com.zj.laboratory.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 小程序端信息(lw_notice)实体类
 *
 * @author lbj
 * @since 2021-05-10 14:01:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 公告对象Id
     */
    private Long userId;

    /**
     * 公告类型
     */
    private Integer noticeType;
    /**
     * 公告对象类型 0:管理员  1:用户
     */
    private Integer role;
    /**
     * 公告标题
     */
    private String noticeTitle;
    /**
     * 公告内容
     */
    private String noticeContent;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 是否删除，1是0否
     */
    private Integer deleted;
    /**
     * 0:未看过  1:看过
     */
    private Integer isLook;

}