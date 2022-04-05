package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entity.SysBusiness;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
//
///**
// * IService 实现类（ 泛型：M 是 mapper 对象，T 是实体 ）
// * */
//public class BaseServicelmpl<M extends BaseMapper<T>, T> {
//    @Autowired
//    protected M baseMapper;
//
//    protected IPage<M> findListByText(SysParm parm, List<String> GetAttributeMethodSet){
//        //构建分页对象
//        IPage<M> page = new Page<>();
//        page.setSize(parm.getPageSize());
//        page.setCurrent(parm.getCurentPage());
//        //构造查询条件
//        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
//        GetAttributeMethodSet.forEach(Method->{
//            if(StringUtils.isNotEmpty(parm.getText1())){
//                queryWrapper.lambda().like(M::Method,parm.getText1());
//            }
//        });
//        return this.baseMapper.selectPage(page,queryWrapper);
//    }
//
//}
