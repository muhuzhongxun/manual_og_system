package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entityvo.SysUserParm;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-12-21
 */
public interface SysUserRealService extends IService<SysUserReal> {
    //查询用户列表
    IPage<SysUserReal> list(SysUserParm parm);
}
