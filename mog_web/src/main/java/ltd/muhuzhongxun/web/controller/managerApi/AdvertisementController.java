package ltd.muhuzhongxun.web.controller.managerApi;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.Advertisement;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-22
 */
@Api("公告认证信息管理")
@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService service;


    /**
     * 注册或者新增广告
     * @param av
     * @return
     */
    @PostMapping
    public ResultVo addAV(@RequestBody Advertisement av){

        boolean save = service.save(av);
        if(save){
            return ResultUtils.success("广告信息添加成功！");
        }else {
            return ResultUtils.error("广告信息添加失败！");
        }
    }

    /**
     * 编辑广告
     * @param av
     * @return
     */
    @PutMapping
    public ResultVo editAV(@RequestBody Advertisement av){
        boolean info = service.updateById(av);
        if(info){
            return ResultUtils.success("信息状态已更改！");
        }else {
            return ResultUtils.error("信息状态更改失败！");
        }
    }


    /**
     * 删除广告信息
     * @param avId
     * @return
     */
    @DeleteMapping("/{avId}")
    public ResultVo deleteAV(@PathVariable("avId") int avId){
        boolean info = service.removeById(avId);
        if(info){
            return ResultUtils.success("广告信息删除成功！");
        }else {
            return ResultUtils.error("广告信息删除失败");
        }
    }

    /**
     * 查询广告列表
     * @param parm {avTitle: '',avPerson: '',curentPage: 1,pageSize: 10,total: 0}
     * @return
     */
    @ApiOperation(value="分页获取所有广告信息")
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        IPage<Advertisement> list = service.list(parm);
        return ResultUtils.success("查询成功",list);
    }
}


