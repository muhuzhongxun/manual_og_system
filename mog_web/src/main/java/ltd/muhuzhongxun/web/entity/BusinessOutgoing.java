package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class BusinessOutgoing implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 外发编号
     */
        @TableId(value = "og_id", type = IdType.AUTO)
      private Integer ogId;

      /**
     * 用户编号
     */
      private Integer userId;

      /**
     * 外发标题
     */
      private String ogTitle;

      /**
     * 需求信息
     */
      private String details;

      /**
     * 加工材料数量
     */
      private Integer quantity;

      /**
     * 加工材料数量单位
     */
      private String quantitUnit;

      /**
     * 单价
     */
      @TableField("unit_Price")
    private Float unitPrice;

      /**
     * 总价
     */
      private Float totalPrice;

      /**
     * 备注
     */
      private String remark;

      /**
     * 行业
     */
      private Integer industry;

      /**
     * 加工方式
     */
      private String processingMethod;

      /**
     * 创建外发信息的日期
     */
      @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
      private LocalDateTime ogDateStart;

      /**
     * 外发信息失效时间
     */
      private LocalDate ogDateEnd;

      /**
     * 最晚收货时间
     */
      private LocalDate deliveryDate;

      /**
     * 加工厂所在地域(省code)
     */
      private String provinceCode;

      /**
       * 加工厂所在地域(市code)
       */
      private String cityCode;

      /**
     * 加工用户所提交的保证金
     */
      private Integer deposit;

      /**
     * 联系方式
     */
      private String workphone;

     /**
      * 状态 0为有效，1为失效
      */
     private String isUsed;


}
