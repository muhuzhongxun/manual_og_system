package ltd.muhuzhongxun.web.service.impl;

import ltd.muhuzhongxun.web.entity.BusinessOrder;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.BusinessOrderMapper;
import ltd.muhuzhongxun.web.service.BusinessOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 厂商与加工户交易时生成的订单 服务实现类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-05
 */
@Service
public class BusinessOrderServiceImpl extends ServiceImpl<BusinessOrderMapper, BusinessOrder> implements BusinessOrderService {

    @Override
    public List<Integer> getChartDataByYear(String parm) {
        return this.baseMapper.getChartDataByYear(parm);
    }
}
