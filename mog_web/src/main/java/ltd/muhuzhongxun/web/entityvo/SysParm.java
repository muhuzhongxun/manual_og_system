package ltd.muhuzhongxun.web.entityvo;

import lombok.Data;

/**
 * SysParom实体为后台管理界面的所有搜索条件综合实体
 * 用于信息列表分页筛选属性类
 */
@Data
public class SysParm {
    //页容量
    private int pageSize;
    //当前页
    private int curentPage;
    //检索项1
    private String text1;
    //检索项2
    private String text2;
    //备用
    private String text3;
}
