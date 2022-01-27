package ltd.muhuzhongxun.web.controller.managerApi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import ltd.muhuzhongxun.web.entity.SysBusiness;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.BusinessOutgoingService;
import ltd.muhuzhongxun.web.service.SysBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
@Api("用户外发信息管理")
@RestController
@RequestMapping("/api/sysOG")
public class BusinessOutgoingController {
    @Autowired
    private BusinessOutgoingService OGService;

    // 获取企业列表
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        IPage<BusinessOutgoing> list = OGService.list(parm);
        return ResultUtils.success("查询成功",list);
    }

    // 新增企业
    @PostMapping
    public ResultVo addUser(@RequestBody BusinessOutgoing OG){
        boolean save = OGService.save(OG);
        if(save){
            return ResultUtils.success("企业信息添加成功！");
        }else {
            return ResultUtils.error("企业信息添加失败");
        }
    }

    // 编辑企业
    @PutMapping
    public ResultVo editUser(@RequestBody BusinessOutgoing OG){
        boolean info = OGService.updateById(OG);
        if(info){
            return ResultUtils.success("企业信息更新成功！");
        }else {
            return ResultUtils.error("企业信息更新失败");
        }
    }

    // 删除企业 此处 parm:{BusinessId:${key}}
    @DeleteMapping("/{ogId}")
    public ResultVo deleteUser(@PathVariable("ogId") int ogId){
        boolean info = OGService.removeById(ogId);
        if(info){
            return ResultUtils.success("企业信息删除成功！");
        }else {
            return ResultUtils.error("企业信息删除失败");
        }
    }
}

