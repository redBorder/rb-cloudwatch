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

        if(args.length < 2) {
            Configuration configuration = null;
            if (args.length == 1) {
                //Applying configuration...
                configuration = ConfigurationHandler.readConfiguration(args[0]);
            } else if (args.length == 0) {
                configuration = ConfigurationHandler.readConfiguration("/rb_cloudwatch/resources/config.json");
            }
            //Creating class to connect with AWS
            CloudwatchConnector cloudwatchConnector = new CloudwatchConnectorImpl(configuration);
            //Creating kafka consumer threads
            ConsumerGroup consumerGroup = new ConsumerGroup(configuration, cloudwatchConnector);
            consumerGroup.run();
        } else {
            System.out.println("Arguments: [ Configfile ]");
        }
    }
}
