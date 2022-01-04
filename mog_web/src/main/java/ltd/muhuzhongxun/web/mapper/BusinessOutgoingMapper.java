package ltd.muhuzhongxun.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
public interface BusinessOutgoingMapper extends BaseMapper<BusinessOutgoing> {

    //将og的quantit_unit属性值换成dict字典里的值
    @Select(value="SELECT\n" +
            "\tog.og_id, \n" +
            "\tog.user_id, \n" +
            "\tog.og_title, \n" +
            "\tog.details, \n" +
            "\tog.quantity, \n" +
            "\tog.unit_Price, \n" +
            "\tog.total_price, \n" +
            "\tog.remark, \n" +
            "\tog.industry, \n" +
            "\tog.processing_method, \n" +
            "\tog.og_date_start, \n" +
            "\tog.og_date_end, \n" +
            "\tog.delivery_date, \n" +
            "\tog.province_code, \n" +
            "\tog.city_code, \n" +
            "\tdict.`name` AS quantit_unit\n" +
            "FROM\n" +
            "\tbusiness_outgoing og,\n" +
            "\tdict\n" +
            "WHERE\n" +
            "\tog.quantit_unit = dict.id")
    IPage<BusinessOutgoing> selectMogPage(IPage<BusinessOutgoing> page, @Param(Constants.WRAPPER) Wrapper<BusinessOutgoing> queryWrapper);
}
