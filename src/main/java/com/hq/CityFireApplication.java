package com.hq;


import com.hq.datasources.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})

//因为扫描不到某个dao文件，无法注入到serviceImpl中，就添加了下句。
@MapperScan(value = "com.hq.modules.*.dao")

public class CityFireApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CityFireApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.debug("phm test");
		return application.sources(CityFireApplication.class);
	}
}
