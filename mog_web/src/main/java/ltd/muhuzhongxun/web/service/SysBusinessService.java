package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.SysBusiness;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-01-10
 */
@Transactional
public interface SysBusinessService extends IService<SysBusiness> {

    //查询企业列表
    IPage<SysBusiness> list(SysParm parm);

    SysBusiness selectById(int Id);
}
