package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @author muhuzhongxun
 * @since 2022-02-22
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Advertisement implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 广告id
   */
  @TableId(type = IdType.AUTO)
  private Integer avId;

  /**
   * 广告标题
   */
  private String avTitle;

  /**
   * 广告投放位置
   */
  private String avPosition;

  /**
   * 投放时间
   */
  private LocalDateTime avDate;

  /**
   * 投放状态，0未审核1未投放2投放中3投放完毕4冻结中
   */
  private Integer avStatus;

  /**
   * 联系人姓名
   */
  private String avPerson;

  /**
   * 联系人电话
   */
  private String avPhone;

  /**
   * 素材图片
   */
  private String avPhoto;

  /**
   * 跳转参数
   */
  private String avUrl;

  /**
   * 广告点击量
   */
  private Integer avNum;


}
