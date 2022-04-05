package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.mapper.SysUserRealMapper;
import ltd.muhuzhongxun.web.service.SysUserRealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-12-21
 */
@Service
public class SysUserRealServiceImpl extends ServiceImpl<SysUserRealMapper, SysUserReal> implements SysUserRealService {


    @Override
    public IPage<SysUserReal> list(SysParm parm) {
        //构建分页对象
        IPage<SysUserReal> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<SysUserReal> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(SysUserReal::getRealName,parm.getText1());
        }
        //模糊查询符合身份证后4位的信息，待优化
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(SysUserReal::getCardId,parm.getText2());
        }
        //只查询未审核的信息{RealStatus=3}
        if(StringUtils.isNotEmpty(parm.getText3())){
            queryWrapper.lambda().like(SysUserReal::getRealStatus,parm.getText3());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }
}
