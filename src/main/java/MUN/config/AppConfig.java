package MUN.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class AppConfig {

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */

    public @Bean
    MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("mongodb://benxin:nbx00144@ds111568.mlab.com:11568/stockopedia");
        return mongo;
    }
}