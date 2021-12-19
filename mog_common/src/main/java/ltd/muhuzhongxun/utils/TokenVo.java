package ltd.muhuzhongxun.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {
    private String roles;
    private String name;
    private String avatar;
    
}
