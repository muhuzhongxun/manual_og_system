package ltd.muhuzhongxun.web.mapper;

import ltd.muhuzhongxun.web.entity.BusinessOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 厂商与加工户交易时生成的订单 Mapper 接口
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-05
 */
public interface BusinessOrderMapper extends BaseMapper<BusinessOrder> {

    @Select(value="SELECT" +
            "\tCOUNT(*)," +
            "\tMONTH(order_date_start) as 月份" +
            "\tFROM" +
            " `business_order` " +
            "WHERE" +
            " YEAR(order_date_start)= #{parm} " +
            "GROUP BY MONTH(order_date_start)")
    List<Integer> getChartDataByYear(String parm);
}
