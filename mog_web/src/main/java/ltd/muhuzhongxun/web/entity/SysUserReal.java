package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2021-12-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SysUserReal implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 认证编号
     */
        @TableId(value = "real_id", type = IdType.AUTO)
      private Integer realId;

      /**
     * 用户编号
     */
      private Integer userId;

      /**
     * 真实姓名
     */
      private String realName;

      /**
     * 身份证件号
     */
      private String cardId;

      /**
     * 有效身份证起始日期
     */
      private LocalDate cardStart;

      /**
     * 有效身份证截至日期
     */
      private LocalDate cardEnd;

      /**
     * 申请认证时间
     */
      private LocalDateTime realDateStart;

      /**
     * 认证时间
     */
      private LocalDateTime realDateEnd;

      /**
     * 认证状态 0为不通过,1为通过
     */
      private Integer realStatus;

      /**
     * 身份证正面照
     */
      private String picture1;

      /**
     * 身份证背面照
     */
      private String picture2;

      /**
     * 预留空位
     */
      private String picture3;


}
