package ltd.muhuzhongxun.config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi // 3.0版本,开启swagger3 || @EnableSwagger2  开启swagger2
//http://localhost:8080/swagger-ui/ 访问swagger-ui
public class SwaggerConfig {



    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示Swagger的环境：dev/test...
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles判断s是否处在自己设定的环境中profiles
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //配置API文档的分组，若要设置多个组就要有多个Docket实例
                .groupName("牧户组")
                //enable,是否启用swagger:true/false
                .enable(flag)
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackge,默认扫描的包
                //any，扫描全部
                //none，全部不扫描
                //withClassAnnotation,只扫描类上有（指定注解）的类，参数是一个注解的反射对象：RestController.class
                //withMethodAnnotation,只扫描方法上有（指定注解）的方法，参数是一个注解的反射对象：Get/PutMapper.class
                .apis(RequestHandlerSelectors.basePackage("ltd.muhuzhongxun.web.controller"))
                //path,过滤路径
                //.paths(PathSelectors.ant("/com/xu/controller/**"))
                .build();
    }


    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo() {

        //作者信息
        Contact contact = new Contact("牧户仲寻","http://localhost:8089/","983724494@qq.com");

        return new ApiInfo(
                "牧户仲寻的SwaggerAPI文档",
                "啊这啊这",
                "1.0",
                "http://localhost:8089/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                    new ArrayList());

    }


}
