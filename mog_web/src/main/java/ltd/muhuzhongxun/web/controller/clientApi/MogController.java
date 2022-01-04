package ltd.muhuzhongxun.web.controller.clientApi;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * @param mogQueryVo
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
     *
     * @param mogQueryVo(pageSize,currentPage,detail)
     * @return
     */
    @ApiOperation(value="根据名称查询信息")
    @GetMapping("findMogListByDetail")
    public ResultVo findMogListByDetail(MogQueryVo mogQueryVo){
        //显示上线的医院   0：有效，	1：失效
        mogQueryVo.setIsUsed(0);
        IPage<BusinessOutgoing> businessOutgoings=ogService.selectMogPage(mogQueryVo);
        return ResultUtils.success("查询成功",businessOutgoings);
    }
}
