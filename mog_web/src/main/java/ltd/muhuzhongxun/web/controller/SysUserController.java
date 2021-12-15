package ltd.muhuzhongxun.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entity.SysUserParm;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;

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
//
//    @PostMapping("/login")
//    public boolean login(@RequestBody SysUser user) {
//        System.out.println(user.toString());
//        String loginName= user.getLoginName();
//        String password= user.getPassword();
//        if(loginName!=""&&password!=""){
//            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
//            queryWrapper.eq("loginName", loginName);
//            SysUser one = sysUserService.getOne(queryWrapper);
//            if (one.getPassword().equals(password)) {
//                System.out.println("密码匹配成功");
//                return true;
//            } else {
//                return false;
//            }
//        }return false;
//    }

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
     * @param user
     * @return
     */
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser user){
        // 判断登陆名的唯一性
        QueryWrapper<SysUser> query= new QueryWrapper<>();
        query.lambda().eq(SysUser::getLoginName,user.getLoginName());
        SysUser one = sysUserService.getOne(query);
        //判断其他用户登陆账号与编辑的账号相同
        if(one != null && one.getUserId() != user.getUserId()){
            return ResultUtils.error("登录账号已经被占用！",500);
        }
        // 如果密码存在，用MD加密
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean info = sysUserService.updateById(user);
        if(info){
            return ResultUtils.success("用户信息更新成功！");
        }else {
            SysUser a=new SysUser();
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
     * @param parm
     * @return
     */
    @ApiOperation(value="获取所有用户信息")
    @GetMapping("/list")
    public ResultVo list(SysUserParm parm){
         IPage<SysUser> list = sysUserService.list(parm);
        return ResultUtils.success("查询成功",list);
    }

}

