package ltd.muhuzhongxun.web.controller.managerApi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.SysUserRealService;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-12-21
 */
@Api("用户认证信息管理")
@RestController
@RequestMapping("/api/sysUserReal")
public class SysUserRealController {

    @Autowired
    private SysUserRealService service;

    @Autowired
    private SysUserService userservice;

    /**
     * 注册或者新增用户
     * @param user
     * @return
     */
    @PostMapping
    public ResultVo addUserReal(@RequestBody SysUserReal user){
        // 判断用户编号UserId是否有效，无效则无法添加信息
        if (StringUtils.isNotEmpty(user.getUserId().toString())){
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.lambda().eq(SysUser::getUserId, user.getUserId());
            // 从<用户表>中查询用户信息
            SysUser one = userservice.getOne(query);
            if(one == null){
                return ResultUtils.error("无效用户ID！", 501);
            }else //若该用户已被禁用
                if(userservice.selectById(user.getUserId()).getIsUsed() == "1"){
                    return ResultUtils.error("该用户暂停使用！", 502);
            }
        }else return ResultUtils.error("空用户ID", 500);
        // 从<用户认证表>中判断身份证ID的唯一性
        if(StringUtils.isNotEmpty(user.getCardId())) {
            QueryWrapper<SysUserReal> query = new QueryWrapper<>();
            query.lambda().eq(SysUserReal::getCardId, user.getCardId());
            // RealStatus=1代表 认证成功的
            query.lambda().eq(SysUserReal::getRealStatus, "1");
            int num = service.count(query);
            // 若认证信息中已有三条相同身份证认证成功信息，则不能再认证新账号
            if (num > 3) {
                return ResultUtils.error("该身份证件认证账号数已达上限！", 511);
            }
        }else return ResultUtils.error("空身份证ID", 510);

        boolean save = service.save(user);
        if(save){
            return ResultUtils.success("用户认证信息添加成功！");
        }else {
            return ResultUtils.error("用户认证信息添加失败！");
        }
    }

    /**
     * 编辑用户
     * @param user
     * @return
     */
    @PutMapping
    public ResultVo editUser(@RequestBody SysUserReal user){
        boolean info = service.updateById(user);
        if(info){
            return ResultUtils.success("认证信息状态已更改！");
        }else {
            return ResultUtils.error("认证信息状态更改失败！");
        }
    }


    /**
     * 删除用户认证信息
     * @param realId
     * @return
     */
    @DeleteMapping("/{realId}")
    public ResultVo deleteUser(@PathVariable("realId") int realId){
        boolean info = service.removeById(realId);
        if(info){
            return ResultUtils.success("用户认证信息删除成功！");
        }else {
            return ResultUtils.error("用户认证信息删除失败");
        }
    }

    /**
     * 查询用户列表
     * @param parm {realName: '',cardId: '',curentPage: 1,pageSize: 10,total: 0}
     * @return
     */
    @ApiOperation(value="获取所有用户认证信息")
    @GetMapping("/list")
    public ResultVo list(SysParm parm){
        System.out.println(parm);
        IPage<SysUserReal> list = service.list(parm);
        return ResultUtils.success("查询成功",list);
    }
}

