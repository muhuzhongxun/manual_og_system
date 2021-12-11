package ltd.muhuzhongxun.web.entity;

import lombok.Data;

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
//    //用户角色类型
//    private String userType;
}
