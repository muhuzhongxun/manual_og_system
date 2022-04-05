package ltd.muhuzhongxun.web.controller.managerApi;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.Dict;
import ltd.muhuzhongxun.web.entity.Dict;
import ltd.muhuzhongxun.web.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    /**
     * 根据数据id查询子数据列表
     * @param dict 包装着id
     * @param status true:给返回列表添加HasChildren属性，false可以减少过多无用的查询
     * @return
     */
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData")
    public ResultVo findChildData(Dict dict,@RequestParam("status") Boolean status) {
        List<Dict> list = dictService.findChlidData(dict.getId(),status);
        return ResultUtils.success("查询成功",list);
    }

    //根据dictCode获取下级节点
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public ResultVo findByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return ResultUtils.success("查询成功",list);
    }


    @ApiOperation(value = "模糊查询数据字典列表")
    @GetMapping("FlexibleQueryDict")
    public ResultVo FlexibleQueryDict(Dict dict){
        List<Dict> list = dictService.FlexibleQueryDict(dict);
        return ResultUtils.success("查询成功",list);
    }

//    //根据dictcode和value查询
//    @GetMapping("getName/{dictCode}/{value}")
//    public String getName(@PathVariable String dictCode,
//                          @PathVariable String value) {
//        String dictName = dictService.getDictName(dictCode,value);
//        return dictName;
//    }
//
//    //根据value查询
//    @GetMapping("getName/{value}")
//    public String getName(@PathVariable String value) {
//        String dictName = dictService.getDictName("",value);
//        return dictName;
//    }

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




    /**
     * 注册或者新增数据字典信息
     * @param dict
     * @return
     */
    @CacheEvict(cacheNames = "dict",allEntries = true)
    @PostMapping
    public ResultVo addDict(@RequestBody Dict dict){
        boolean save = dictService.save(dict);
        if(save){
            return ResultUtils.success("数据字典信息注册成功！");
        }else {
            return ResultUtils.error("数据字典信息注册失败");
        }
    }

    /**
     * 编辑数据字典信息
     * @param dict
     * @return
     */
    @CacheEvict(cacheNames = "dict",allEntries = true)
    @PutMapping
    public ResultVo editDict(@RequestBody Dict dict){
        boolean info = dictService.updateById(dict);
        if(info){
            return ResultUtils.success("数据字典信息更新成功！");
        }else {
            return ResultUtils.error("数据字典信息更新失败");
        }
    }

    /**
     * 删除数据字典信息
     * @param id
     * @return
     */
    @CacheEvict(cacheNames = "dict",allEntries = true)
    @DeleteMapping("/{id}")
    public ResultVo deleteDict(@PathVariable("id") int id){
        boolean info = dictService.removeById(id);
        if(info){
            return ResultUtils.success("数据字典信息删除成功！");
        }else {
            return ResultUtils.error("数据字典信息删除失败");
        }
    }
}
