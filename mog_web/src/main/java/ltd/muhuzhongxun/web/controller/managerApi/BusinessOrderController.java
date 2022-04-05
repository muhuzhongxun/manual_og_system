package ltd.muhuzhongxun.web.controller.managerApi;


import io.swagger.annotations.Api;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.service.BusinessOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 厂商与加工户交易时生成的订单 前端控制器
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-05
 */
@Api("订单信息管理")
@RestController
@RequestMapping("/api/businessOrder")
public class BusinessOrderController {

    @Autowired
    private BusinessOrderService businessOrderService;

    /**
     *  parm:具体某年
     * 获取Echarts图表数据
     */
    @GetMapping("/getChartDataByYear")
    public ResultVo list(String parm){
        List<Integer> list = businessOrderService.getChartDataByYear(parm);
        return ResultUtils.success("查询成功",list);
    }
}

