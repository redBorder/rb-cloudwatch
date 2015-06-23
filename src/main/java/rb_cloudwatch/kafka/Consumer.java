package rb_cloudwatch.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import rb_cloudwatch.cloudwatch.CloudwatchConnector;
import rb_cloudwatch.model.Metric;

import java.util.logging.Logger;

/**
 * Created by alberto on 2/6/15.
 */
class Consumer implements Runnable {

    private KafkaStream kafkaStream;
    private JSONHandler jsonHandler;
    private CloudwatchConnector cloudwatchConnector;
    private Integer threadNumber;

    private static final Logger logger = Logger.getLogger(Consumer.class.getName());

    public Consumer(CloudwatchConnector cloudwatchConnector, KafkaStream kafkaStream, int threadNumber) {
        jsonHandler = new JSONHandler();
        this.cloudwatchConnector = cloudwatchConnector;
        this.kafkaStream = kafkaStream;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();

        while(iterator.hasNext()) {

            try {
                logger.fine("New message detected in thread " + threadNumber.toString());
                //First, consume a message and generate a Metric object with information consumed
                String message = new String(iterator.next().message());
                Metric metric = jsonHandler.processJSON(message);
                logger.fine("Thread " + threadNumber.toString() + " consumed a message. Metric object generated\n" + metric.toString());
                //Then, send this metric to AWS Cloudwatch service
                cloudwatchConnector.sendMetric(metric);
            } catch (Exception e) {
                logger.warning("Message parsing error");
                e.printStackTrace();

            }
        }
    }
}
