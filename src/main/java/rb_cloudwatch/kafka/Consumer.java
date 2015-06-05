package rb_cloudwatch.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerConnector;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import rb_cloudwatch.model.Metric;
import java.util.logging.Logger;

/**
 * Created by alberto on 2/6/15.
 */
public class Consumer implements Runnable {

    private ConsumerConnector consumer;
    private KafkaStream kafkaStream;
    private JSONHandler jsonHandler;
    private Metric metric;

    private static final Logger logger = Logger.getLogger(Consumer.class.getName());

    public Consumer(ConsumerConfig consumerConfig) {    //TODO consumerConfig
        //consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        while(iterator.hasNext()) {
            try {
                metric = jsonHandler.processJSON(iterator.next().message().toString());
            } catch (Exception e){
                logger.warning("Message parsing error");
            }
        }
    }
}
