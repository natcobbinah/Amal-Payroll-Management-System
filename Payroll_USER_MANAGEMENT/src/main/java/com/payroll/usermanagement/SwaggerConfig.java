package com.payroll.usermanagement;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
@CrossOrigin(maxAge = 3600)
public class SwaggerConfig {

	@Inject
	private SpringSwaggerConfig springSwaggerConfig;

	public ApiInfo getApiInfo() {
		ApiInfo apiInfob = new ApiInfoBuilder().title("AmaliTech Payroll Management System")
				.description("Payroll Management system for managing" + "employees")
				.termsOfServiceUrl("will be added soon")
				.contact("https://www.linkedin.com/in/nathaniel-cobbinah-31611583/")
				.license("http://opensource.org/licenses/MIT").build();
		return apiInfob;
	}

	@Bean
	public SwaggerSpringMvcPlugin v1APIConfiguration() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
		swaggerSpringMvcPlugin.apiInfo(getApiInfo()).includePatterns("/v1/*.*").swaggerGroup("v1");
		swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
		return swaggerSpringMvcPlugin;
	}
	
	//other api versions can also be done here as has been done for version1
}
