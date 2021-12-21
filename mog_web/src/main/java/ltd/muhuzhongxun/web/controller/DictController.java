package ltd.muhuzhongxun.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.Dict;
import ltd.muhuzhongxun.web.entity.DictEeVo;
import ltd.muhuzhongxun.web.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @GetMapping("findChildData")
    public ResultVo findChildData(Dict dict) {
        List<Dict> list = dictService.findChlidData(dict.getId());
        return ResultUtils.success("查询成功",list);
    }


    @ApiOperation(value = "模糊查询数据字典列表")
    @GetMapping("FlexibleQueryDict")
    public ResultVo FlexibleQueryDict(Dict dict){
        List<Dict> list = dictService.FlexibleQueryDict(dict);
        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public ResultVo importData(MultipartFile file) {
        dictService.importData(file);
        return ResultUtils.success();
    }
}
