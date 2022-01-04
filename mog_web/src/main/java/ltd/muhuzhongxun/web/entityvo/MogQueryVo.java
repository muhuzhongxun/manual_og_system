package ltd.muhuzhongxun.web.entityvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * MogQueryVo实体为用户界面的所有搜索条件综合实体
 */
@Data
@ApiModel(description = "Mog")
public class MogQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "页容量")
	private int pageSize;

	@ApiModelProperty(value = "当前页数")
	private int currentPage;

	@ApiModelProperty(value = "备用")
	private String BT;

	@ApiModelProperty(value = "搜索框搜索信息")
	private String detail;

	@ApiModelProperty(value = "行业类型")
	private String industry;

	@ApiModelProperty(value = "省code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	private String cityCode;

	/**
	 * 0：有效，		1：失效
	 */
	@ApiModelProperty(value = "状态")
	private Integer isUsed;
}

