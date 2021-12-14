package ltd.muhuzhongxun.web.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 用户编号
     */
      @TableId(type = IdType.AUTO)
      private Integer userId;

      /**
     * 登录账号
     */
      private String loginName;

      /**
     * 登录密码
     */
      private String password;

      /**
     * 用户名
     */
      private String userName;

      /**
     * 认证编号默认为0
     */
      private String realId;

      /**
     * 手机号
     */
      private String phone;

      /**
     * 性别：0为男 1为女 2未知
     */
      private String sex;

      /**
     * 0：启用，1：禁用
     */
      private String isUsed;

      /**
     * 0：用户，1：商家，2：管理员，3：站长
     */
      private Integer userType;

      /**
     * 信誉初始5.0最高10
     */
      private Float credit;

      /**
     * 评级
     */
      private String rating;

      /**
     * 用户注册日期
     */
      private LocalDate userCreateDate;


}
