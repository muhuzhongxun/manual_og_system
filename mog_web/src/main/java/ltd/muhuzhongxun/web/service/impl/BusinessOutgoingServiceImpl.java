package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.val;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.entityvo.UserPublishMogVo;
import ltd.muhuzhongxun.web.mapper.BusinessOutgoingMapper;
import ltd.muhuzhongxun.web.mapper.DictMapper;
import ltd.muhuzhongxun.web.service.BusinessOutgoingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private BusinessOutgoingMapper businessOutgoingMapper;

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
        IPage<BusinessOutgoing> Result = baseMapper.selectPage(page,queryWrapper);
//        //方法二：查询表一后,遍历查询表二并更改表一的内容
//        IPage<BusinessOutgoing> Result = baseMapper.selectPage(page,queryWrapper);
//        //System.out.println(Result.getRecords());
//        Result.getRecords().stream().forEach(item->{
//            item.setQuantitUnit(dictMapper.selectById(item.getQuantitUnit()).getName());
//        });

        return Result;

    }

    @Override
    public List<BusinessOutgoing> selectList() {
        QueryWrapper<BusinessOutgoing> queryWrapper = new QueryWrapper<>();
        // 过滤掉过期的信息
        queryWrapper.lambda().eq(BusinessOutgoing::getIsUsed,0);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public BusinessOutgoing selectById(Integer ogId) {
        return baseMapper.selectById(ogId);
    }

    @Override
    public IPage<BusinessOutgoing> list(SysParm parm) {
        //构建分页对象
        IPage<BusinessOutgoing> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<BusinessOutgoing> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(BusinessOutgoing::getOgTitle,parm.getText1());
        }
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(BusinessOutgoing::getUserId,parm.getText2());
        }
        if(StringUtils.isNotEmpty(parm.getText3())){
            // 分割时间区间,如把"2022-01-13 00:00:00,2022-02-23 00:00:00"分成两个时间
            String STime=parm.getText3().substring(0,19);
            String DTime=parm.getText3().substring(20,39);
            System.out.println("把parm.text3的时间区间("+parm.getText3()+")分割成STime("+STime+")和DTime("+DTime+")两个时间点");
            //gt：大于，ge：大于等于，lt：小于，le：小于等于
            queryWrapper.lambda().ge(BusinessOutgoing::getOgDateStart,STime);
            queryWrapper.lambda().le(BusinessOutgoing::getOgDateStart,DTime);
        }
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<UserPublishMogVo> findPublicMogTree(Integer userId) {
        //创建list集合，MogVoresult用于存储UserPublicMogVo实体集《-其为数据库取得的BusinessOutgoing实体集的删减版
        List<UserPublishMogVo> MogVoresult = new ArrayList<>();
        //创建list集合，用于最终数据封装.
        List<UserPublishMogVo> result = new ArrayList<>();

        //根据发布者编号，查询其所有的外发信息
        // select og_id,og_title,industry from business_outgoing where user_id = 1  =》 List
        QueryWrapper<BusinessOutgoing> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BusinessOutgoing::getUserId,userId);
        List<BusinessOutgoing> List = baseMapper.selectList(wrapper);

        //将List<BusinessOutgoing>转成List<UserPublishMogVo> =》result
        Iterator<BusinessOutgoing> iterator = List.iterator();
        while (iterator.hasNext()) {
            BusinessOutgoing businessOutgoing = iterator.next();
            UserPublishMogVo item = new UserPublishMogVo();
            item.setOgId(businessOutgoing.getOgId());
            item.setOgTitle(businessOutgoing.getOgTitle());
            item.setIndustry(businessOutgoing.getIndustry());
            MogVoresult.add(item);
        }

        //根据行业  industry 分组，获取每个行业里面的下级数据
        Map<String, List<UserPublishMogVo>> IndustryMap = MogVoresult.stream().collect(Collectors.groupingBy(UserPublishMogVo::getIndustry));

        //遍历map集合 IndustryMap
        for(Map.Entry<String,List<UserPublishMogVo>> entry : IndustryMap.entrySet()) {
            //行业名称
            String industry = entry.getKey();
            //行业对应的全局数据
            List<UserPublishMogVo> UserPublishMogVoList = entry.getValue();
            //封装大行业（其为result下的第一层数据userPublishMogVo1)
            UserPublishMogVo userPublishMogVo1 = new UserPublishMogVo();
            userPublishMogVo1.setIndustry(industry);

            //封装同行业下的外发信息集 userPublishMogVo2{ogid,ogtitle}（其为userPublishMogVo1下的下级数据userPublishMogVo2）
            List<UserPublishMogVo> children = new ArrayList<>();
            for(UserPublishMogVo item: UserPublishMogVoList) {
                UserPublishMogVo userPublishMogVo2 =  new UserPublishMogVo();
                userPublishMogVo2.setOgId(item.getOgId());
                userPublishMogVo2.setOgTitle(item.getOgTitle());
                //封装到list集合
                children.add(userPublishMogVo2);
            }
            //把同行业下的外发信息集list集合放到行业集的children里面
            userPublishMogVo1.setChildren(children);
            //放到最终result里面
            result.add(userPublishMogVo1);
        }
        //返回result:{userPublishMogVo1:{industry，childrent:{userPublishMogVo2:{ogid,ogtitle}}}}）
        return result;
    }
}
