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
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 消息id
   */
  @TableId(value = "cid", type = IdType.AUTO)
  private Long cid;

  /**
   * 发送者编号
   */
  private Integer fromid;

  /**
   * 接收者编号
   */
  private Integer id;

  /**
   * 消息内容
   */
  private String content;

  /**
   * 消息发送者名称
   */
  private String username;

  /**
   * 发送者头像
   */
  private String avatar;

  /**
   * 是我标记，为true，前台消息从右发出，false从左
   */
  private Integer mine;

  /**
   * 具体时间
   */
  private LocalDateTime timestamp;

  /**
   * 与接收者关系：'friend':好友，'group':群，'sys_msg':系统消息
   */
  private String type;

  /**
   * 删除标记（0:不可用 1:可用）
   */
  private Integer isDeleted;


}
