package com.payroll.usermanagement;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig {

	@Inject
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin configureSwagger() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
		
		//disable default response messages configured within swagger
		swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
	
		ApiInfo apiInfob= new ApiInfoBuilder()
				.title("AmaliTech Payroll Management System")
				.description("Payroll Management system for managing"
						+ "employees")
				.termsOfServiceUrl("will be added soon")
				.contact("https://www.linkedin.com/in/nathaniel-cobbinah-31611583/")
				.license("http://opensource.org/licenses/MIT")
				.build();
		
		swaggerSpringMvcPlugin.apiInfo(apiInfob)
			.apiVersion("1.0")	
			.includePatterns("/admin/*.*","/userLogin/*.*");
		return swaggerSpringMvcPlugin;
	}
}
