package ltd.muhuzhongxun.web.service;

import ltd.muhuzhongxun.web.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    List<Dict> findChlidData(Long id,Boolean status);
    //根据数据code查询子数据列表
    List<Dict> findByDictCode(String dictCode);

    /**
     * 导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

    /**
     * 模糊查询
     * @param dict(name,code)
     * @return List
     */
    List<Dict> FlexibleQueryDict(Dict dict);
}
