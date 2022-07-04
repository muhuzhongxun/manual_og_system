package ltd.muhuzhongxun.web.controller.clientApi;

import ltd.muhuzhongxun.utils.AuthContextHolder;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import ltd.muhuzhongxun.web.service.BusinessOutgoingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client/api/mymog")
public class UserMogController {

    @Autowired
    private BusinessOutgoingService outgoingService;

//    public ResultVo findAll(HttpServletRequest request) {
//        //获取当前登录用户id
//        Long userId = AuthContextHolder.getUserId(request);
    //获取外发信息列表
    @GetMapping("findAll/{userId}")
    public ResultVo findAll(@PathVariable("userId") Integer userId) {
        List<BusinessOutgoing> list = outgoingService.findAllUserId(userId);
        return ResultUtils.success("",list);
    }
    //添加外发信息
    @PostMapping("save")
    public ResultVo saveBusinessOutgoing(@RequestBody BusinessOutgoing mog) {
        outgoingService.save(mog);
        return ResultUtils.success();
    }
    //根据id获取外发信息信息
    @GetMapping("get/{id}")
    public ResultVo getBusinessOutgoing(@PathVariable Long id) {
        BusinessOutgoing mog = outgoingService.selectById(id);
        return ResultUtils.success("用户信息查询成功！",mog);
    }
    //修改外发信息
    @PutMapping("update")
    public ResultVo updateBusinessOutgoing(@RequestBody BusinessOutgoing mog) {
        outgoingService.updateById(mog);
        return ResultUtils.success("用户信息修改成功！");
    }
    //删除外发信息
    @DeleteMapping("remove/{id}")
    public ResultVo removeBusinessOutgoing(@PathVariable Long id) {
        outgoingService.removeById(id);
        return ResultUtils.success("用户信息删除成功！");
    }
}
