package vttp.nusiss.arian.MiniProject2.mongoDB;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;




@Configuration
public class MongoConfig {

    
    @Value("${mongo.url}")
    private String connectionStringMongo;

    @Value("${mongo.connection.user}")
    private String connectionUser;

    @Value("${mongo.connection.password}")
    private String connectionPassword;
    
   
    @Bean
    public MongoClient mongoClient() {
       

        ConnectionString connectionString = new ConnectionString(
            connectionStringMongo);

    
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
            MongoClient client = MongoClients.create(settings);
      
            MongoDatabase database = client.getDatabase("MiniProject2");

            

            MongoCollection < Document > collection = database.getCollection("packagesHistory");

            if (collection == null) {
                client.getDatabase("MiniProject2").createCollection("packagesHistory");
            }

            if (collection.getNamespace().getCollectionName()=="packagesHistory"){
             {
                // collection.drop();
                // client.getDatabase("MiniProject2").createCollection("packagesHistory");
            }
             }

        return client;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "MiniProject2");
    }


   
}
