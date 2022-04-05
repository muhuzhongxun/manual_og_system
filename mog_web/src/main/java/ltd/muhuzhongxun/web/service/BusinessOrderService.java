package ltd.muhuzhongxun.web.service;

import ltd.muhuzhongxun.web.entity.BusinessOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 厂商与加工户交易时生成的订单 服务类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-05
 */
@Transactional
public interface BusinessOrderService extends IService<BusinessOrder> {

    List<Integer> getChartDataByYear(String parm);
}
