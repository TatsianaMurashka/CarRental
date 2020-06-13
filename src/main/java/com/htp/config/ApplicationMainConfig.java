package com.htp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.htp")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({DatasourceConfiguration.class, ApplicationBeanConfiguration.class})
public class ApplicationMainConfig {

}