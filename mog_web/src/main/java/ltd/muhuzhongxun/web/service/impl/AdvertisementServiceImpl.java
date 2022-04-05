package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.web.entity.Advertisement;
import ltd.muhuzhongxun.web.entity.Advertisement;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.AdvertisementMapper;
import ltd.muhuzhongxun.web.service.AdvertisementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-22
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    @Override
    public IPage<Advertisement> list(SysParm parm) {
        //构建分页对象
        IPage<Advertisement> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<Advertisement> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(Advertisement::getAvTitle,parm.getText1());
        }
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(Advertisement::getAvPhone,parm.getText2());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }
}
