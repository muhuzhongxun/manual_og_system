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

    // 左连接了user表
    @Select(value="SELECT\n" +
            "\tOG.og_id, \n" +
            "\tOG.user_id, \n" +
            "\tOG.details, \n" +
            "\tOG.og_title, \n" +
            "\tOG.quantity, \n" +
            "\tOG.quantit_unit, \n" +
            "\tOG.unit_Price, \n" +
            "\tOG.total_price, \n" +
            "\tOG.remark, \n" +
            "\tOG.processing_method, \n" +
            "\tOG.industry, \n" +
            "\tOG.og_date_start, \n" +
            "\tOG.og_date_end, \n" +
            "\tOG.delivery_date, \n" +
            "\tOG.city_code, \n" +
            "\tOG.province_code, \n" +
            "\tB.business_id, \n" +
            "\tB.business_name, \n" +
            "\tB.business_phone, \n" +
            "\tB.business_type, \n" +
            "\tB.business_person, \n" +
            "\tB.business_num, \n" +
            "\tB.product, \n" +
            "\tB.brand, \n" +
            "\tB.area, \n" +
            "\tB.model, \n" +
            "\tB.introduction, \n" +
            "\tB.time, \n" +
            "\tB.real_date_start, \n" +
            "\tB.picture, \n" +
            "\tB.real_date_end, \n" +
            "\tB.real_status\n" +
            "FROM\n" +
            "\tbusiness_outgoing AS OG\n" +
            "\tLEFT JOIN\n" +
            "\tsys_business AS B\n" +
            "\tON \n" +
            "\t\tOG.user_id = B.user_id" +
            "\t${ew.customSqlSegment}")
    IPage<BusinessOutgoing> selectUserOGPage(IPage<BusinessOutgoing> page, @Param(Constants.WRAPPER) Wrapper<BusinessOutgoing> queryWrapper);
}
