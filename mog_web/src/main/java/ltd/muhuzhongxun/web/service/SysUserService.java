package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.LoginVo;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */
@Transactional
public interface SysUserService extends IService<SysUser> {
    //分页查询用户列表
    IPage<SysUser> list(SysParm parm);

    //一次查询所有用户
    List<SysUser> list();

    SysUser selectById(Integer Id);

    //会员登录
    Map<String, Object> login(LoginVo loginVo, HttpServletResponse response);
}
