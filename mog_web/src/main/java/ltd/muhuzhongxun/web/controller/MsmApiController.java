package ltd.muhuzhongxun.web.controller;

import ltd.muhuzhongxun.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public ResultVo sendCode(@PathVariable String phone) throws Exception {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return ResultUtils.success();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtil.getSixBitRandom();
        //调用service方法，通过整合短信服务进行发送
//        boolean isSend = msmService.send(phone,code);
        boolean isSend =false;
        if(phone.indexOf("@")==-1){
            //发送手机短信验证码事件
            // 默拟验证码为983724，并且发送成功
            code = "983724";
            isSend = true;
        }else{
            //发送邮箱验证码事件
            isSend = Mail.sendCode(code,phone);
        }
        if(isSend) {
            //生成验证码放到redis里面，设置有效时间
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
            return ResultUtils.success("验证码已发送");
        } else {
            return ResultUtils.error("发送短信失败");
        }
    }
}
