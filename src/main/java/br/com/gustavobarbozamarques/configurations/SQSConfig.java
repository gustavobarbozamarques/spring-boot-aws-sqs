package br.com.gustavobarbozamarques.configurations;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class SQSConfig {
    @Value("${sqs.accessKey}")
    private String accessKey;

    @Value("${sqs.secretKey}")
    private String secretKey;

    @Value("${sqs.region}")
    private String region;

    @Bean
    public ConnectionFactory connectionFactory() {
        var amazonSQS = AmazonSQSClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
    }
}
