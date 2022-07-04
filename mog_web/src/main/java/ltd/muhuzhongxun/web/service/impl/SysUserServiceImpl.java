package ltd.muhuzhongxun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.utils.JwtHelper;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.entityvo.LoginVo;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.mapper.SysUserMapper;
import ltd.muhuzhongxun.web.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-12-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SysUserService userService;

    @Override
    public IPage<SysUser> list(SysParm parm){
        //构建分页对象
        IPage<SysUser> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurentPage());
        //构造查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getText2())){
            queryWrapper.lambda().like(SysUser::getPhone,parm.getText2());
        }
        if(StringUtils.isNotEmpty(parm.getText1())){
            queryWrapper.lambda().like(SysUser::getUserName,parm.getText1());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysUser> list() {
        return baseMapper.selectList(null);
    }

    @Override
    public SysUser selectById(Integer Id) {
        return baseMapper.selectById(Id);
    }

    @Override
    public Map<String, Object> login(LoginVo loginVo, HttpServletResponse response) {
        // 判断phone是否是邮箱
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern r = Pattern.compile(pattern);
        Matcher m =r.matcher(loginVo.getPhone());
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //校验参数
        if(StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(code)) {
            throw new RuntimeException("参数不正确");
        }

        //TODO 校验校验验证码
        if(!redisTemplate.opsForValue().get(phone).isEmpty()&&redisTemplate.opsForValue().get(phone).equals(code)){
            System.out.println("验证码输出正确！");
        }else{
            // 验证码不正确
            throw new RuntimeException("验证码不正确，请重新输入！");
        }

        //手机号/邮箱已被使用
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(m.matches()){
            //true 为邮箱
            queryWrapper.eq("email", phone);
        }else{
            //false 为phone
            queryWrapper.eq("phone", phone);
        }
        //获取会员
        SysUser userInfo = baseMapper.selectOne(queryWrapper);
        if(null == userInfo) {
            userInfo = new SysUser();
            userInfo.setUserName("");
            userInfo.setLoginName("");
            // 赋值初始密码8个8并加密
            userInfo.setPassword(DigestUtils.md5DigestAsHex("88888888".getBytes()));
            if(m.matches()){
                //true 为邮箱
                userInfo.setEmail(phone);
            }else{
                //false 为phone
                userInfo.setPhone(phone);
            }
            userInfo.setStatus(false);
            this.save(userInfo);
        }
//        //校验是否被禁用
//        if(userInfo.getStatus() == 0) {
//            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
//        }

        //TODO 记录登录

        //返回页面显示名称
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getLoginName();
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getLoginName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }
        map.put("name", name);
        //jwt生成token字符串
        String token = JwtHelper.createToken(userInfo.getUserId(),name,userInfo.getUserType(),true,userInfo.getAvatar());
        // 返回token
        map.put("token",token);
        // 此处为了方便加多个userId，本来该值应该是通过requests的请求头里的token信息用JwtHelper工具解析出来的
        SysUser Info = baseMapper.selectOne(queryWrapper);
        map.put("userId",Info.getUserId());
        return map;
    }
}
