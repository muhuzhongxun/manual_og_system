package ltd.muhuzhongxun.web.service;

import ltd.muhuzhongxun.web.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织架构表 服务类
 * </p>
 *
 * @author admin
 * @since 2021-12-14
 */
public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);
}
