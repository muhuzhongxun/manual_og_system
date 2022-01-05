package ltd.muhuzhongxun.web.controller.clientApi;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
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
     * 用于搜索框提示
     * @param detail
     * @return
     */
    @ApiOperation(value="根据名称查询信息")
    @GetMapping("FlexibleQueryMog/{detail}")
    public ResultVo findMogListByDetail(@PathVariable String detail){
        // 多方向查询未写
        List<BusinessOutgoing> list = ogService.selectBydetail(detail);
        return ResultUtils.success("查询成功",list);
    }

    @GetMapping("ToMogDetail/{ogId}")
    public String ToMogDetail(@PathVariable Integer ogId){
        return "MogDetail/"+ogId;
    }

    @ApiOperation(value="mog详细页面")
    @GetMapping("findMogDetail/{ogId}")
    public ResultVo findMogDetail(@PathVariable Integer ogId){
        //查询详细对象
        BusinessOutgoing one = ogService.selectById(ogId);
        return ResultUtils.success("查询成功",one);
    }
}
