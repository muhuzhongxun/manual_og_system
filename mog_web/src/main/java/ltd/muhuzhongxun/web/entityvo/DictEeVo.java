package ltd.muhuzhongxun.web.entityvo;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 组织架构表
 * </p>
 *
 * @author admin
 * @since 2021-12-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class DictEeVo implements Serializable {

    @ExcelProperty(value = "id",index = 0)
    private Long id;

    @ExcelProperty(value = "上级id",index = 1)
    private Long parentId;

    @ExcelProperty(value = "名称",index = 2)
    private String name;

    @ExcelProperty(value = "值",index = 3)
    private String value;

    @ExcelProperty(value = "编码",index = 4)
    private String dictCode;



//      /**
//     * 创建时间
//     */
//      private LocalDateTime createTime;
//
//      /**
//     * 更新时间
//     */
//      private LocalDateTime updateTime;
//
//      /**
//     * 删除标记（0:不可用 1:可用）
//     */
//      private Integer isDeleted;

}
