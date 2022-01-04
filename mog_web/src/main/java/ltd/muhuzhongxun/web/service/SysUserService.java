package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.SysUserParm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */
public interface SysUserService extends IService<SysUser> {
    //查询用户列表
    IPage<SysUser> list(SysUserParm parm);

    SysUser selectById(int Id);
}
