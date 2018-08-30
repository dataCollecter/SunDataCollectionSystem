package cn.edu.scau.DataCollectionSystem.config;

import cn.edu.scau.DataCollectionSystem.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }

}
