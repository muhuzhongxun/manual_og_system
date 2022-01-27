package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
@Transactional
public interface BusinessOutgoingService extends IService<BusinessOutgoing> {


    //前台 外发信息列表(条件查询分页)
    IPage<BusinessOutgoing> selectMogPage(MogQueryVo mogQueryVo);

    //前台 根据 detail 搜索列表
    List<BusinessOutgoing> selectBydetail(String detail);

    //前台 根据 Id 搜索 具体BusinessOutgoing信息
    BusinessOutgoing selectById(Integer ogId);

    //后台查询所有信息列表
    IPage<BusinessOutgoing> list(SysParm parm);
}
