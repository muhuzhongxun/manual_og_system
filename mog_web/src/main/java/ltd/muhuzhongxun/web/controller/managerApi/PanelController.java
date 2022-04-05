package ltd.muhuzhongxun.web.controller.managerApi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.Panel;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.PanelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-06
 */
@Api("卡片信息管理")
@RestController
@RequestMapping("/api/panel")
public class PanelController {

    @Autowired
    private PanelService panelService;


    /**
     * 注册或者新增卡片信息
     * @param panel
     * @return
     */
    @PostMapping
    public ResultVo addPanel(@RequestBody Panel panel){
        boolean save = panelService.save(panel);
        if(save){
            return ResultUtils.success("卡片信息注册成功！");
        }else {
            return ResultUtils.error("卡片信息注册失败");
        }
    }

    /**
     * 编辑卡片信息
     * @param panel
     * @return
     */
    @PutMapping
    public ResultVo editPanel(@RequestBody Panel panel){
        boolean info = panelService.updateById(panel);
        if(info){
            return ResultUtils.success("卡片信息更新成功！");
        }else {
            return ResultUtils.error("卡片信息更新失败");
        }
    }

    /**
     * 删除卡片信息
     * @param panelId
     * @return
     */
    @DeleteMapping("/{panelId}")
    public ResultVo deletePanel(@PathVariable("panelId") int panelId){
        boolean info = panelService.removeById(panelId);
        if(info){
            return ResultUtils.success("卡片信息删除成功！");
        }else {
            return ResultUtils.error("卡片信息删除失败");
        }
    }

    /**
     * 查询卡片信息列表
     * @param parm {panelId: '',panelTableName: '',curentPage: 1,pageSize: 10,total: 0}
     * @return
     */
    @ApiOperation(value="获取所有卡片信息信息")
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        System.out.println(parm);
        IPage<Panel> list = panelService.list(parm);
        return ResultUtils.success("查询成功",list);
    }

    /**
     * 根据卡片放置位置查询卡片信息
     * @param Position：position
     * @return
     */
    @ApiOperation(value="根据卡片放置位置查询卡片信息")
    @GetMapping("/listByPosition/{position}")
    public ResultVo list(@PathVariable("position") String position){
        List<Panel> list = panelService.listByPosition(position);
        return ResultUtils.success("查询成功",list);
    }
}

