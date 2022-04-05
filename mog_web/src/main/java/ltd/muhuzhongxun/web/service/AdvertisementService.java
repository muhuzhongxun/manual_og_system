package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.Advertisement;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-22
 */
@Transactional
public interface AdvertisementService extends IService<Advertisement> {
    //查询广告列表
    IPage<Advertisement> list(SysParm parm);
}
