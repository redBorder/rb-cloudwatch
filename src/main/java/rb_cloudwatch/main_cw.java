package rb_cloudwatch;

import rb_cloudwatch.cloudwatch.CloudwatchConnector;
import rb_cloudwatch.cloudwatch.CloudwatchConnectorImpl;
import rb_cloudwatch.configuration.Configuration;
import rb_cloudwatch.configuration.ConfigurationHandler;
import rb_cloudwatch.kafka.ConsumerGroup;


/**
 * Created by alberto on 7/5/15.
 */
public class main_cw {
    /**
     *
     */
    public static void main (String[] args) {

        //Applying configuration...
        Configuration configuration = ConfigurationHandler.readConfiguration(args[0]);
        //Creating class to connect with AWS
        CloudwatchConnector cloudwatchConnector = new CloudwatchConnectorImpl(configuration);
        //Creating kafka consumer threads
        ConsumerGroup consumerGroup = new ConsumerGroup(configuration, cloudwatchConnector);
        consumerGroup.run();
        while(true);
    }
}
