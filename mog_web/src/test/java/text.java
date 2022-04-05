import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class text {

    @Autowired
    private SysUserService userService;

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //1.根据id查询用户信息
        SysUser user = userService.selectById(1);
        //2.修改数据，调用方法实现修改
        user.setSex("男");
        boolean i = userService.updateById(user);
        System.out.println(i);
    }
}
