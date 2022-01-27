package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.SysUserMapper;
import ltd.muhuzhongxun.web.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public IPage<SysUser> list(SysParm parm){
        //构建分页对象
        IPage<SysUser> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(SysUser::getPhone,parm.getText2());
        }
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(SysUser::getUserName,parm.getText1());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public SysUser selectById(int Id) {
        return baseMapper.selectById(Id);
    }
}
