package dev.fleet.workload;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(DriverWorkloadProperties.class)
@ConditionalOnProperty(prefix = "fleet.driver-workload", name = "enabled", havingValue = "true")
public class DriverWorkloadAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DriverWorkloadService driverWorkloadService(DriverWorkloadProperties properties) {
        return new DriverWorkloadService(properties);
    }
}
