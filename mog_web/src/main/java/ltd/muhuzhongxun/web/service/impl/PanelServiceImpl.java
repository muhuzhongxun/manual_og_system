package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.web.entity.Panel;
import ltd.muhuzhongxun.web.entity.Panel;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.PanelMapper;
import ltd.muhuzhongxun.web.service.PanelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-06
 */
@Service
public class PanelServiceImpl extends ServiceImpl<PanelMapper, Panel> implements PanelService {

    @Override
    public IPage<Panel> list(SysParm parm) {
        //构建分页对象
        IPage<Panel> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<Panel> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(Panel::getPanelTitle,parm.getText1());
        }
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(Panel::getPanelTableName,parm.getText2());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Panel selectById(int Id) {
        return baseMapper.selectById(Id);
    }

    @Override
    public List<Panel> listByPosition(String position) {
        // 构造查询条件
        QueryWrapper<Panel> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(position)){
            queryWrapper.lambda().like(Panel::getPanelPosition,position);
        }
        // 排序条件{根据权重降序排序}
        queryWrapper.orderByDesc("panel_weight");
        return this.baseMapper.selectList(queryWrapper);
    }
}
