package top.huhuiyu.springboot.template.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置
 * 
 * @author DarkKnight
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30).pathMapping("/").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/error").negate()).build()
        .globalRequestParameters(getGlobalRequestParameters());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().contact(new Contact("胡辉煜", "https://huhuiyu.top", "1069306849@qq.com")).title("springboot模板项目").description("springboot模板项目").version("1.0.0").build();
  }

  private List<RequestParameter> getGlobalRequestParameters() {
    List<RequestParameter> parameters = new ArrayList<>();
    parameters.add(
        new RequestParameterBuilder().name("token").description("token令牌").required(false).in(ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false).build());
    return parameters;
  }

}