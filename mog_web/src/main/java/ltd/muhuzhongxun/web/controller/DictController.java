package ltd.muhuzhongxun.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.Dict;
import ltd.muhuzhongxun.web.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 组织架构表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-12-14
 */
@Api(description = "数据字典接口")
@RestController
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public ResultVo findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return ResultUtils.success("查询成功",list);
    }
}
