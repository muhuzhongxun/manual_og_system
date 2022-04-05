package ltd.muhuzhongxun.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {
    private Integer userId;
    private Integer roles;
    private String name;
    private String avatar;

}
