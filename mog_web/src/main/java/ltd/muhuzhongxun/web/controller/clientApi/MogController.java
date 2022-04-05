package ltd.muhuzhongxun.web.controller.clientApi;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import ltd.muhuzhongxun.web.entityvo.UserPublishMogVo;
import ltd.muhuzhongxun.web.service.BusinessOutgoingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "前端接口")
@RestController
@RequestMapping("/client/api/business")
public class MogController {
    @Autowired
    private BusinessOutgoingService ogService;

    /**
     *
     * @param mogQueryVo(pageSize,currentPage,detail)
     * @return
     */
    @ApiOperation(value="分页查询外发信息列表")
    @GetMapping("findMogList")
    public ResultVo findMogList(MogQueryVo mogQueryVo){
        //显示上线的医院   0：有效，	1：失效
        mogQueryVo.setIsUsed(0);
        IPage<BusinessOutgoing> businessOutgoings=ogService.selectMogPage(mogQueryVo);
        return ResultUtils.success("查询成功",businessOutgoings);
    }

    /**
     * 用于搜索框提示相关内容
     * 后端输出所有内容，前端过滤无关内容
     * @return
     */
    @ApiOperation(value="查询所有外发信息")
    @GetMapping("findAllMogList")
    public ResultVo findAllMogList(){
        List<BusinessOutgoing> list = ogService.selectList();
        return ResultUtils.success("查询成功",list);
    }


    /*
     * 查询外发的详细信息
     */
    @ApiOperation(value="mog详细页面")
    @GetMapping("findMogDetail/{ogId}")
    public ResultVo findMogDetail(@PathVariable Integer ogId){
        //查询详细对象
        BusinessOutgoing one = ogService.selectById(ogId);
        return ResultUtils.success("查询成功",one);
    }

    /*
     * 查询外发者的相关发布信息标题
     */
    @ApiOperation(value="相关发布信息标题")
    @GetMapping("findAllPublishMog/{userId}")
    public ResultVo findAllPublishMog(@PathVariable Integer userId){
        List<UserPublishMogVo> list = ogService.findPublicMogTree(userId);
        return ResultUtils.success("查询成功",list);
    }

}
