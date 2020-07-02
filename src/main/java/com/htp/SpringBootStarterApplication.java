package com.htp;

import com.htp.config.ApplicationBeanConfiguration;
import com.htp.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.htp"},
        exclude = {
//                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Import({
        ApplicationBeanConfiguration.class,
        //DatasourceConfiguration.class,
        SwaggerConfig.class
})
public class SpringBootStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterApplication.class, args);
    }
}
