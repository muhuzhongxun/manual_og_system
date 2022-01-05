package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.val;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import ltd.muhuzhongxun.web.mapper.BusinessOutgoingMapper;
import ltd.muhuzhongxun.web.mapper.DictMapper;
import ltd.muhuzhongxun.web.service.BusinessOutgoingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
@Service
public class BusinessOutgoingServiceImpl extends ServiceImpl<BusinessOutgoingMapper, BusinessOutgoing> implements BusinessOutgoingService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public IPage<BusinessOutgoing> selectMogPage(MogQueryVo mogQueryVo) {
        //构建分页对象
        IPage<BusinessOutgoing> page = new Page<>();
        page.setSize(mogQueryVo.getPageSize());
        page.setPages(mogQueryVo.getCurrentPage());

        //构造查询条件
        QueryWrapper<BusinessOutgoing> queryWrapper = new QueryWrapper<>();
        //检索外发信息标题包含detail的内容
        if(StringUtils.isNotEmpty(mogQueryVo.getDetail())){
            queryWrapper.lambda().like(BusinessOutgoing::getOgTitle,mogQueryVo.getDetail());
        }
        //检索省code对应的内容
        if(StringUtils.isNotEmpty(mogQueryVo.getProvinceCode())){
            queryWrapper.lambda().eq(BusinessOutgoing::getProvinceCode,mogQueryVo.getProvinceCode());
        }
        //检索市code对应的内容
        if(StringUtils.isNotEmpty(mogQueryVo.getCityCode())){
            queryWrapper.lambda().eq(BusinessOutgoing::getCityCode,mogQueryVo.getCityCode());
        }
        //检索行业对应的内容
        if(StringUtils.isNotEmpty(mogQueryVo.getIndustry())){
            queryWrapper.lambda().eq(BusinessOutgoing::getIndustry,mogQueryVo.getIndustry());
        }
        //检索未失效的内容{isUesd: 0}
        queryWrapper.lambda().eq(BusinessOutgoing::getIsUsed,mogQueryVo.getIsUsed());

        //方法一：自定义查询语句（将表二（数据字典）的属性名换成对应表一（BusinessOutgoing）的QuantitUnit属性名）
        IPage<BusinessOutgoing> Result = baseMapper.selectMogPage(page,queryWrapper);
//        //方法二：查询表一后,遍历查询表二并更改表一的内容
//        IPage<BusinessOutgoing> Result = baseMapper.selectPage(page,queryWrapper);
//        //System.out.println(Result.getRecords());
//        Result.getRecords().stream().forEach(item->{
//            item.setQuantitUnit(dictMapper.selectById(item.getQuantitUnit()).getName());
//        });

        return Result;

    }

    @Override
    public List<BusinessOutgoing> selectBydetail(String detail) {
        QueryWrapper<BusinessOutgoing> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(detail)){
            queryWrapper.lambda().like(BusinessOutgoing::getOgTitle,detail);
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public BusinessOutgoing selectById(Integer ogId) {
        return baseMapper.selectById(ogId);
    }
}
