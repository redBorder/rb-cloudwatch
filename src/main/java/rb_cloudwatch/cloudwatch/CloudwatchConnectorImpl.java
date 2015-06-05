package rb_cloudwatch.cloudwatch;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alberto on 2/6/15.
 */
public class CloudwatchConnectorImpl implements CloudwatchConnector {

    /* Attributes */
    AmazonCloudWatch client;
    //Logger for this class
    private static final Logger logger = Logger.getLogger(CloudwatchConnectorImpl.class.getName());

    /* Constructors */
    public CloudwatchConnectorImpl() {
        this.client = new AmazonCloudWatchClient();
    }

    @Override
    public boolean sendMetric(rb_cloudwatch.model.Metric metric) {
        boolean error = false; //Variable to indicate error status

        Collection<MetricDatum> l = new LinkedList<MetricDatum>();
        MetricDatum awsmetric = new MetricDatum();
        PutMetricDataRequest metricRequest = new PutMetricDataRequest();
        logger.fine("Created AWS Metric data structures");

        metricRequest.setNamespace("RB/" + metric.getType());

        awsmetric.setMetricName(metric.getMonitor());
        awsmetric.setUnit(StandardUnit.Count); //TODO
        awsmetric.setTimestamp(metric.getTimestamp());
        awsmetric.setValue(metric.getValue());
        metricRequest.setMetricData(l);
        logger.fine(awsmetric.toString());

        l.add(awsmetric);
        try {

            client.putMetricData(metricRequest);
            logger.log(Level.INFO, "putMetricData executed");
        } catch (AmazonServiceException e) {
            logger.log(Level.SEVERE, "Amazon Server Exception");
            logger.log(Level.FINE, e.getStackTrace().toString());
            error = true;
        } catch (AmazonClientException e) {
            logger.log(Level.SEVERE, "Amazon Client Exception");
            logger.log(Level.FINE, e.getStackTrace().toString());
            error = true;
        }
        return error;
    }
}
