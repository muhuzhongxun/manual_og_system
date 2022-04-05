package ltd.muhuzhongxun.web.controller.managerApi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.SysBusiness;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.SysParm;
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
 * @since 2022-01-10
 */
@Api("企业信息管理")
@RestController
@RequestMapping("/api/sysBusiness")
public class SysBusinessController {

    @Autowired
    private SysBusinessService businessService;

    // 获取企业列表
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        IPage<SysBusiness> list = businessService.list(parm);
        return ResultUtils.success("查询成功",list);
    }

    // 新增企业
    @PostMapping
    public ResultVo addUser(@RequestBody SysBusiness business){
        boolean save = businessService.save(business);
        if(save){
            return ResultUtils.success("企业信息添加成功！");
        }else {
            return ResultUtils.error("企业信息添加失败");
        }
    }

    // 编辑企业
    @PutMapping
    public ResultVo editUser(@RequestBody SysBusiness business){
        boolean info = businessService.updateById(business);
        if(info){
            return ResultUtils.success("企业信息更新成功！");
        }else {
            return ResultUtils.error("企业信息更新失败");
        }
    }

    // 删除企业 此处 parm:{BusinessId:${key}}
    @DeleteMapping("/{businessId}")
    public ResultVo deleteUser(@PathVariable("businessId") int businessId){
        boolean info = businessService.removeById(businessId);
        if(info){
            return ResultUtils.success("企业信息删除成功！");
        }else {
            return ResultUtils.error("企业信息删除失败");
        }
    }

}

