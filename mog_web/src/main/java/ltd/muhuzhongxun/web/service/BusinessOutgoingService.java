package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.SysUserReal;
import ltd.muhuzhongxun.web.entityvo.MogQueryVo;
import ltd.muhuzhongxun.web.entity.BusinessOutgoing;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import ltd.muhuzhongxun.web.entityvo.UserPublishMogVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-01-01
 */
@Transactional
public interface BusinessOutgoingService extends IService<BusinessOutgoing> {


    //前台 外发信息列表(条件查询分页)
    IPage<BusinessOutgoing> selectMogPage(MogQueryVo mogQueryVo);

    //前台 用于搜索框提示相关内容,获取所有外发信息
    List<BusinessOutgoing> selectList();

    //前台 根据 Id 搜索 具体BusinessOutgoing信息
    BusinessOutgoing selectById(Long ogId);

    //后台查询所有信息列表
    IPage<BusinessOutgoing> list(SysParm parm);

    //前台查询发布者所有相关发布信息
    List<UserPublishMogVo> findPublicMogTree(Integer userId);

    //前台用户获取自己的外发信息
    List<BusinessOutgoing> findAllUserId(Integer userId);
}
