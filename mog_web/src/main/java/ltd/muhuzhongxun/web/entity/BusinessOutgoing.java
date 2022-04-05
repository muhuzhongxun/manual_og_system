package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 外发详细信息
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
  @TableId(value = "og_id", type = IdType.ASSIGN_ID)
  private Long ogId;

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
   * 加工材料剩余量
   */
  private Integer surplus;

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
  private String industry;

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
  @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime ogDateEnd;

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

  @Version
  private Integer version;
}
