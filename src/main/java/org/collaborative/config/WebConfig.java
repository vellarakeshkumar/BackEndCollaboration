package org.collaborative.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@Configuration
@EnableWebMvc //<mvc:annotation-driven>
@ComponentScan(basePackages="org.collaborative") //<context:component-scan>
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter{
	

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");
    }
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576);	// 1MB
		return multipartResolver;
	}
	
	@Bean
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("/");
        return velocityConfigurer;
    }
	
	@Bean(name = "velocityEngine")
	public VelocityEngineFactoryBean velocityEngineFactoryBean() {
	    VelocityEngineFactoryBean vefb = new VelocityEngineFactoryBean();
	    Properties p = new Properties();
	    p.put("resource.loader", "class");
	    p.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	    vefb.setVelocityProperties(p);
	    return vefb;
	}
}
