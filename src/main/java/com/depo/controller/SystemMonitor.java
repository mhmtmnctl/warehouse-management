package com.depo.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemMonitor {

	@Autowired
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	@GetMapping("/health")
	public Health checkHealth() {

		Status status = Status.UP;
		String message = "Uygulama sağlıklı.";

		return Health.status(status).withDetail("message", message).build();

		// burada isApplicationHealthy() metodu yapılabilir. bu metod içerisinde basit
		// bir db sorgusu vs yazılabilir.
		// eğer sonuç dönüyorsa sağlıklı çalışıyor vs gibi.
		// varsayılan olarak sağlıklı olduğunu dönüyorum
	}

	@GetMapping("/info")
	public Map<String, String> getAppInfo() {
		Map<String, String> appInfo = new HashMap<>();
		appInfo.put("App Name:", "Warehouse Backend App");
		appInfo.put("version", "1.0.0");
		appInfo.put("database", "PostgreSQL");
		appInfo.put("jwtExpirationMs", "86400000");
		return appInfo;
	}

	@GetMapping("/metrics")
	public Map<String, Integer> getMetrics() {
		Map<String, Integer> metrics = new HashMap<>();
		metrics.put("totalUsers", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_user", Integer.class));
		metrics.put("totalWarehouse", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_depo", Integer.class));
		metrics.put("totalCategory", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_category", Integer.class));
		metrics.put("totalProducts", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_product", Integer.class));

		return metrics;
	}

//	 @GetMapping("/env")
//	    public Map<String, String> getEnvironment() {
//	        Map<String, String> env = new HashMap<>();
//	        env.put("activeProfiles", Arrays.toString(environment.getActiveProfiles()));
//	        // Diğer ortam değişkenlerini buraya ekleyebiliriz
//	        return env;
//	    }

	@GetMapping("/beans")
	@ResponseBody
	public String[] getBeans() {
		return applicationContext.getBeanDefinitionNames();
	}

	@GetMapping("/runtime-info")
	@ResponseBody
	public Map<String, Object> getRuntimeInfo() {
		Map<String, Object> runtimeInfo = new HashMap<>();

		// JVM çalışma zamanı bilgisi
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

		// JVM argümanları
		List<String> jvmArguments = runtimeMxBean.getInputArguments();

		// Sistem özellikleri
		Properties systemProperties = System.getProperties();
		Map<String, String> systemPropertiesMap = new HashMap<>();
		for (String propertyName : systemProperties.stringPropertyNames()) {
			systemPropertiesMap.put(propertyName, systemProperties.getProperty(propertyName));
		}

		// Çevre değişkenleri
		Map<String, String> environmentVariables = System.getenv();

		runtimeInfo.put("jvmArguments", jvmArguments);
		runtimeInfo.put("systemProperties", systemPropertiesMap);
		runtimeInfo.put("environmentVariables", environmentVariables);

		return runtimeInfo;
	}

}
