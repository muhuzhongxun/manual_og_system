package ltd.muhuzhongxun.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfiguration implements WebMvcConfigurer {
    //跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") //允许跨域的域名，可以用*表示允许任何域名使用
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") //允许任何方法（post、get等）
                .allowCredentials(true) //带上cookie信息
                .maxAge(3600) //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
                .allowedHeaders("*"); //允许任何请求头
    }

}
