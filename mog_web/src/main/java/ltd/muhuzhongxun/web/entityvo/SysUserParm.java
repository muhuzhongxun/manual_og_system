package ltd.muhuzhongxun.web.entityvo;

import lombok.Data;

/**
 * 用户列表分页筛选属性类
 */
@Data
public class SysUserParm {
    //页容量
    private int pageSize;
    //当前页
    private int curentPage;
    //电话号
    private String phone;
    //姓名
    private String userName;

    //追加用户认证属性(身份证后4位)
    private String cardId;
}
