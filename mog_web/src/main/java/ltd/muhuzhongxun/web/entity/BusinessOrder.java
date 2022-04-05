package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 厂商与加工户交易时生成的订单
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 订单编号
   */
  @TableId(value = "order_id", type = IdType.AUTO)
  private Integer orderId;

  /**
   * 外发编号
   */
  private Integer ogId;

  /**
   * 用户编号
   */
  private Integer userId;

  /**
   * 交易加工数量
   */
  private Integer orderRawNum;

  /**
   * 订单生成时间
   */
  private LocalDateTime orderDateStart;

  /**
   * 订单交易有效截至时间
   */
  private LocalDate orderDateEnd;

  /**
   * 订单状态0:交易完成,1:发货中,2:加工中,3:交货审核中
   */
  private String status;


}
