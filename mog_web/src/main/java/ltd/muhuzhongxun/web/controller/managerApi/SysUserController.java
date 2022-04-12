package ltd.muhuzhongxun.web.controller.managerApi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.utils.TokenVo;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */

@Api("用户信息管理")
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @param user
     * @return 返回状态码一定要是20000才会跳转页面
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody SysUser user){
        String loginName= user.getLoginName();
        String password= DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        // 输出加密后的密码，方便检查
        System.out.println(password);
        if(loginName!=""&&password!="") {
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
            queryWrapper.eq("login_name", loginName);
            SysUser one = sysUserService.getOne(queryWrapper);
            if (one.getPassword().equals(password)) {
                System.out.println("密码匹配成功");
                HashMap<String, Object> Map = new HashMap<String, Object>();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    // 添加 Map { "token": {"loginName": $loginName,"roles": $userType} }返回前端
                    map.put("userId",one.getUserId());
                    map.put("name", loginName);
                    map.put("roles",one.getUserType());
                    map.put("status",true);
//                    map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                    map.put("avatar",one.getAvatar());
//                String token = JwtHelper.createToken(one.getUserId(), loginName,one.getUserType(),true,one.getAvatar());
                Map.put("token",map);
                return ResultUtils.success("登录成功！", 20000, Map);
            } else {
                return ResultUtils.error("登录密码有误！",50001);
            }
        }
        return ResultUtils.error("登录名或密码不能为空！",50002);
    }


     /**
     *获取登录后台用户的基础信息
     * @param token{name:'',roles:'',avatar:''}
     * @return
     */
    @PostMapping("/info")
    public ResultVo info(@RequestBody TokenVo token){
        System.out.println(token);
        if(StringUtils.isNotEmpty(token.getName())) {
            return ResultUtils.success("用户加载成功！", 20000, token);
        }else{
            return ResultUtils.success("登录信息已过期！请重新登录！", 50000, null);
        }
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public ResultVo logout(){
        // 添加登出功能
        return ResultUtils.success("登出成功",20000,null);
    }


    /**
     * 注册或者新增用户
     * @param user
     * @return
     */
    @PostMapping
    public ResultVo addUser(@RequestBody SysUser user){
        // 判断登陆名的唯一性
        if(StringUtils.isNotEmpty(user.getLoginName())) {
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.lambda().eq(SysUser::getLoginName, user.getLoginName());
            SysUser one = sysUserService.getOne(query);
            if (one != null) {
                return ResultUtils.error("登录账号已经被占用！", 500);
            }
        }
        // 如果密码存在，用MD加密
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean save = sysUserService.save(user);
        if(save){
            return ResultUtils.success("用户注册成功！");
        }else {
            return ResultUtils.error("用户注册失败");
        }
    }

    /**
     * 编辑用户
     * @param user{UserId,loginName,...}
     * @return
     */
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser user){
        // 判断登陆名的唯一性
        QueryWrapper<SysUser> query= new QueryWrapper<>();
        if(StringUtils.isNotEmpty(user.getLoginName())){return ResultUtils.error("缺少参数loginName",500);}
        query.lambda().eq(SysUser::getLoginName,user.getLoginName());
        SysUser one = sysUserService.getOne(query);
        //判断其他用户登陆账号与编辑的账号相同
        if(one != null && one.getUserId() != user.getUserId()){
            return ResultUtils.error("登录账号已经被占用！",500);
        }
        // 如果前端传来的新密码和数据库中加密过的密码不一致,
        if(one != null && !one.getPassword().equals(user.getPassword())){
            // 将新密码加密后更改数据库
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean info = sysUserService.updateById(user);
        if(info){
            return ResultUtils.success("用户信息更新成功！");
        }else {
            return ResultUtils.error("用户信息更新失败");
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable("userId") int userId){
        boolean info = sysUserService.removeById(userId);
        if(info){
            return ResultUtils.success("用户信息删除成功！");
        }else {
            return ResultUtils.error("用户信息删除失败");
        }
    }

    /**
     * 查询用户列表
     * @param parm {userName: '',phone: '',curentPage: 1,pageSize: 10,total: 0}
     * @return
     */
    @ApiOperation(value="分页获取用户信息")
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        IPage<SysUser> list = sysUserService.list(parm);

        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation(value="获取所有用户信息")
    @GetMapping("/AllList")
    public ResultVo list(){
        List<SysUser>  list=sysUserService.list();
        return ResultUtils.success("查询成功",list);
    }
}

