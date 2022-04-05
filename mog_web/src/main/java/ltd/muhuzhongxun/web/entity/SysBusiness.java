package ltd.muhuzhongxun.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-01-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SysBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 企业编号
     */
        @TableId(value = "business_id", type = IdType.AUTO)
      private Integer businessId;

      /**
     * 用户编号
     */
      private Integer userId;

      /**
     * 企业名称
     */
      private String businessName;

      /**
       * 联系方式
       */
      private String businessPhone;

      /**
     * 企业类型0123
     */
      private String businessType;

      /**
     * 负责人
     */
      private String businessPerson;

      /**
     * 企业规模人数
     */
      private String businessNum;

      /**
     * 涉及行业种类
     */
      private String industry;

      /**
     * 产品种类
     */
      private String product;

      /**
     * 品牌
     */
      private String brand;

      /**
     * 经营区域
     */
      private Integer area;

      /**
     * 经营模式
     */
      private String model;

      /**
     * 公司成立时间
     */
      private LocalDate time;

      /**
     * 公司介绍
     */
      private String introduction;

      /**
     * 公司展示图
     */
      private String picture;


      /**
       * 审核创建时间
       */
      private LocalDateTime realDateStart;

      /**
       * 审核结果时间
       */
      private LocalDateTime realDateEnd;

      /**
       * 审核状态:0为不通过，1为通过
       */
      private int realStatus;

}
