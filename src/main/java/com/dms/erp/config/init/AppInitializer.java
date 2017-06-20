package com.dms.erp.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dms.erp.config.JPAConfig;
import com.dms.erp.config.SecurityConfig;
import com.dms.erp.config.ServiceConfig;
import com.dms.erp.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// para localizar os controllers
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// no web.xml - urlMappings
		return new String[] { "/" };
	}

	/**
	 * O Spring por default bloqueia o recebimento de parâmetro via PUT, para
	 * isso basta adicionar um {@code HttpPutFormContentFilter} {@inheritDoc}
	 */
	@Override
	protected Filter[] getServletFilters() {
		HttpPutFormContentFilter contentFilter = new HttpPutFormContentFilter();
		return new Filter[] { contentFilter };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
