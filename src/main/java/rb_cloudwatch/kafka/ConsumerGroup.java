package rb_cloudwatch.kafka;


import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import rb_cloudwatch.cloudwatch.CloudwatchConnector;
import rb_cloudwatch.configuration.Configuration;
import rb_cloudwatch.configuration.ConfigurationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by alberto on 5/6/15.
 */
public class ConsumerGroup {

    private Configuration config;
    private ConsumerConnector consumerConnector;
    private CloudwatchConnector cloudwatchConnector;
    private ExecutorService executor;

    private static final Logger logger = Logger.getLogger(ConsumerGroup.class.getName());

    public ConsumerGroup(Configuration config, CloudwatchConnector cloudwatchConnector) {
        this.cloudwatchConnector = cloudwatchConnector;
        this.config = config;
        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(ConfigurationHandler.createConsumerConfig(config));
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(config.getKafka_topic(), Integer.parseInt(config.getThread_number())); //Select Topic for consume and the number of threads which will consume in this topic.
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(config.getKafka_topic());

        //Launch all the threads. Number of thread is obtained from configuration.
        executor = Executors.newFixedThreadPool(Integer.parseInt(config.getThread_number()));
        logger.info("Created Thread pool to consume kafka streams");

        //Create object to consume messages
        int threadNumber = 0;
        for (KafkaStream stream : streams) {
            executor.submit(new Consumer(cloudwatchConnector, config.getFilter(), stream, threadNumber));
            logger.info("Started thread number " + threadNumber);
            threadNumber++;
        }
    }



}
