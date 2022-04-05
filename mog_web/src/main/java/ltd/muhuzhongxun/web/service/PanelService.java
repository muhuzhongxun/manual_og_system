package ltd.muhuzhongxun.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ltd.muhuzhongxun.web.entity.Panel;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.web.entity.Panel;
import ltd.muhuzhongxun.web.entityvo.SysParm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-02-06
 */
@Transactional
public interface PanelService extends IService<Panel> {
    //查询卡片列表
    IPage<Panel> list(SysParm parm);

    Panel selectById(int Id);

    List<Panel> listByPosition(String position);
}
