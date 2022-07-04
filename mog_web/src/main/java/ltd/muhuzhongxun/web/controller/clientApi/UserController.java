package ltd.muhuzhongxun.web.controller.clientApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.AuthContextHolder;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.entityvo.LoginVo;
import ltd.muhuzhongxun.web.service.SysUserRealService;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(description = "前端登录接口")
@RestController
@RequestMapping("/client/api/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRealService userRealService;


    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public ResultVo login(@RequestBody LoginVo loginVo, HttpServletResponse response) {
//        loginVo.setIp(IpUtil.getIpAddr(request));
        try {
            Map<String, Object> info = userService.login(loginVo, response);
            return ResultUtils.success("登录成功",info);
        }catch (Exception e){
            return ResultUtils.error("验证码不正确，请重新输入！",500);
        }
    }


    //通过token获取用户id信息来查询用户基本信息
    @GetMapping("auth/getUserInfo/{token}")
//public ResultVo getUserInfo(HttpServletRequest request) {
    public ResultVo getUserInfo(@PathVariable("token") String token) {
        Integer userId = AuthContextHolder.getUserId(token);
        SysUser userInfo = userService.getById(userId);
        return ResultUtils.success("用户的信息获取成功！",userInfo);
    }

    //通过id获取用户id信息来查询用户基本信息
    @GetMapping("/getOneInfo/{id}")
//public ResultVo getUserInfo(HttpServletRequest request) {
    public ResultVo getUserInfo(@PathVariable("id") Integer id) {
        SysUser userInfo = userService.getById(id);
        return ResultUtils.success("用户的信息获取成功！",userInfo);
    }

    // 根据用户id找到其认证信息
    @GetMapping("auth/getUserRealInfoByUserId/{userId}")
    public ResultVo getUserRealInfo(@PathVariable("userId") Integer userId) {
        SysUserReal userRealInfo = userRealService.getOneByuserId(userId);
//        System.out.println("用户的认证信息获取成功"+userRealInfo);
        return ResultUtils.success("用户的认证信息获取成功！",userRealInfo);
    }
}
