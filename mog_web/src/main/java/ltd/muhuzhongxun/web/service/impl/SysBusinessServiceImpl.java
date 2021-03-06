package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.web.entity.SysBusiness;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.SysBusinessMapper;
import ltd.muhuzhongxun.web.service.SysBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-01-10
 */
@Service
public class SysBusinessServiceImpl extends ServiceImpl<SysBusinessMapper, SysBusiness> implements SysBusinessService {


    @Override
    public IPage<SysBusiness> list(SysParm parm) {
        //构建分页对象
        IPage<SysBusiness> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<SysBusiness> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(SysBusiness::getBusinessName,parm.getText2());
        }
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(SysBusiness::getBusinessPerson,parm.getText1());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public SysBusiness selectById(int Id) {
        return baseMapper.selectById(Id);
    }
}
