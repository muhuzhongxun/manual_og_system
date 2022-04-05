package ltd.muhuzhongxun.web.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.muhuzhongxun.listener.DictListener;
import ltd.muhuzhongxun.web.entity.Dict;
import ltd.muhuzhongxun.web.entityvo.DictEeVo;
import ltd.muhuzhongxun.web.mapper.DictMapper;
import ltd.muhuzhongxun.web.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织架构表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-12-14
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    //根据数据id查询子数据列表
    @Cacheable(value = "dict")
    @Override
    public List<Dict> findChlidData(Long id,Boolean status) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        //查询指定父类id下的所有子数据
        List<Dict> dictList = baseMapper.selectList(wrapper);
        //如果status为true，向list集合每个dict对象中设置hasChildren，仅用于后台管理系统的树形列表展示
        if(status) {
            for (Dict dict : dictList) {
                Long dictId = dict.getId();
                boolean isChild = this.isChildren(dictId);
                dict.setHasChildren(isChild);
            }
        }
        return dictList;
    }
    //判断id下面是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        // 0>0    1>0
        return count>0;
    }

    //根据dictCode获取下级节点
    @Cacheable(value = "dict")
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        //根据dictcode获取对应id
        Dict dict = this.getDictByDictCode(dictCode);
        //根据id获取子节点
        List<Dict> chlidData = this.findChlidData(dict.getId(),false);
        return chlidData;
    }
    private Dict getDictByDictCode(String dictCode) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code",dictCode);
        Dict codeDict = baseMapper.selectOne(wrapper);
        return codeDict;
    }




    @Override
    public List<Dict> FlexibleQueryDict(Dict dict) {
        //构造查询条件
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dict.getDictCode())){
            queryWrapper.lambda().like(Dict::getDictCode,dict.getDictCode());
        }
        if(StringUtils.isNotEmpty(dict.getName())){
            queryWrapper.lambda().like(Dict::getName,dict.getName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    //导出数据字典表格
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo, DictEeVo.class);
                dictVoList.add(dictVo);
            }

            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入
     * allEntries = true: 方法调用后清空所有缓存
     * @param file
     */
    @CacheEvict(value = "dict", allEntries=true)
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
