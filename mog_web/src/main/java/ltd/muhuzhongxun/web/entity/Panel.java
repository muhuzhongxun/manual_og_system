package ltd.muhuzhongxun.web.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Panel implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Integer panelId;

  /**
   * 卡片标题
   */
  private String panelTitle;

  /**
   * 表名
   */
  private String panelTableName;

  /**
   * 卡片链接
   */
  private String panelLink;

  /**
   * 图标类名
   */
  private String panelIcon;

  /**
   * 卡片颜色
   */
  private String panelColor;

  /**
   * 表里对应的时间列名，用于分组
   */
  private String panelPrimaryDate;

  /**
   * 卡片放置位置
   */
  private String panelPosition;

  /**
   * 卡片权重
   */
  private Integer panelWeight;

  /**
   * 卡片的值
   */
  private Integer panelValue;


}
