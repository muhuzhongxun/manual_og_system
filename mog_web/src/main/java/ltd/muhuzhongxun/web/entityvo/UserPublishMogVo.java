package ltd.muhuzhongxun.web.entityvo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "IndustryDetailed")
public class UserPublishMogVo {

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "信息ID")
    private Long ogId;

    @ApiModelProperty(value = "信息标题")
    private String ogTitle;

    @ApiModelProperty(value = "下级节点")
    private List<UserPublishMogVo> children;

}
