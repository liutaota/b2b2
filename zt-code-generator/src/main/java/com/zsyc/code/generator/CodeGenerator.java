package com.zsyc.code.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ResourceBundle;


/**
 * Created by lcs on 2018-12-25.
 */
public class CodeGenerator {


	public static void main(String[] args) {
		// 代码生成器


		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = getString("out.path");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("MP");
		gc.setOpen(false);
		gc.setFileOverride(true);
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(getString("jdbc.url"));
		dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
		dsc.setUsername(getString("jdbc.username"));
		dsc.setPassword(getString("jdbc.password"));
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(getString("module"));
		pc.setParent(getString("package"));
		mpg.setPackageInfo(pc);

		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// tog do nothing
			}
		};

		mpg.setCfg(cfg);

		// 配置模板+-
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setEntity("templates/entity_oracle.java");
		templateConfig.setController(null);
//		templateConfig.setService(null);
//		templateConfig.setServiceImpl(null);

//		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);

		if(StringUtils.isNoneBlank(getString("tablePrefix"))){
			strategy.setTablePrefix(getStringArray("tablePrefix"));
		}

		strategy.setSuperEntityClass(getString("entity.supper-class"));
//		strategy.setSuperEntityColumns(getStringArray("entity.supper-class.fields"));
		strategy.setEntityLombokModel(true);
//		strategy.setRestControllerStyle(true);
//		strategy.setSuperControllerClass(rb.getString("entity.supper-class"));
		strategy.setInclude(new String[]{"API_USER_INFO","API_ORDER_LIST"});
//BMS_ST_RE_DTL_V
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

	static ResourceBundle rb =  ResourceBundle.getBundle("applications");
	private static String getString(String key){
		return System.getProperty(key, rb.getString(key));
	}


	private static String[] getStringArray(String key){
		String val =  System.getProperty(key, rb.getString(key));
			if(val == null)return null;
		return val.split(",");
	}

}
