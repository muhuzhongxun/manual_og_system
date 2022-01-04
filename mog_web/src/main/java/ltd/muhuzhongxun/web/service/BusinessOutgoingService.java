package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
public interface BusinessOutgoingService extends IService<BusinessOutgoing> {


    //外发信息列表(条件查询分页)
    IPage<BusinessOutgoing> selectMogPage(MogQueryVo mogQueryVo);


}
