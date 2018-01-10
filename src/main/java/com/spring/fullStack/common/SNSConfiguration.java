package com.spring.fullStack.common;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import java.util.List;
import java.util.Optional;
 
@Configuration
public class SNSConfiguration {
 
    @Value("${aws.sns.accessKey}")
    private String accessKey;
    @Value("${aws.sns.secretKey}")
    private String secretKey;
    @Value("${aws.sns.region}")
    private String region;
    @Value("${aws.sns.topicArn}")
    private String topicArn;
    @Value("${aws.sns.topicName}")
    private String topicName;
 
    private static final Logger LOGGER = LoggerFactory.getLogger(SNSConfiguration.class);
 
    @Bean
    public AmazonSNS ssnClient() {
        // Create Amazon SNS Client
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
 
        // OPTIONAL: Check the topic already created or not
        ListTopicsResult listTopicsResult = snsClient.listTopics();
        List<Topic> topics = listTopicsResult.getTopics();
 
        Optional<Topic> result = topics.stream()
             .filter(t -> topicArn.equalsIgnoreCase(t.getTopicArn())).findAny();
 
        // Create a new topic if it doesn't exist
        if(!result.isPresent()) {
            createSNSTopic(snsClient);
        }
        return snsClient;
    }
 
    private CreateTopicResult createSNSTopic(AmazonSNS snsClient) {
        CreateTopicRequest createTopic = new CreateTopicRequest(topicName);
        CreateTopicResult result = snsClient.createTopic(createTopic);
        LOGGER.info("Created topic request: " +
                snsClient.getCachedResponseMetadata(createTopic));
        return  result;
    }
    
   
    //LOGGER.debug("The message ID {}", result);
    
}
