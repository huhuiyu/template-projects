package top.huhuiyu.springboot.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * mybatis-plus插件配置
 * 
 * @author DarkKnight
 *
 */
@Configuration
public class MybatisPlusConfig {
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 分页插件(mysql数据库方言)
    PaginationInnerInterceptor pageInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
    // 最大记录限制
    pageInnerInterceptor.setMaxLimit(SystemConstants.MAX_PAGE_SIZE);
    // 分页超出是否处理
    pageInnerInterceptor.setOverflow(false);
    // 是否优化join
    pageInnerInterceptor.setOptimizeJoin(true);
    interceptor.addInnerInterceptor(pageInnerInterceptor);

    return interceptor;
  }
}
