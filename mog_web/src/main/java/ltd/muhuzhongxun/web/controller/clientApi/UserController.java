package ltd.muhuzhongxun.web.controller.clientApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entityvo.LoginVo;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Pattern;

@Api(description = "前端登录接口")
@RestController
@RequestMapping("/client/api/user")
public class UserController {

    @Autowired
    private SysUserService userService;


    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public ResultVo login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
//        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userService.login(loginVo);
        return ResultUtils.success("登录成功",info);
    }
}
