package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    //根据 detail 搜索列表
    List<BusinessOutgoing> selectBydetail(String detail);

    //根据 Id 搜索 具体BusinessOutgoing
    BusinessOutgoing selectById(Integer ogId);
}
