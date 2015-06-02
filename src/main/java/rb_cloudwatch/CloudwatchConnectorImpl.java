package rb_cloudwatch;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
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

    public CloudwatchConnectorImpl() {
        this.client = new AmazonCloudWatchAsyncClient();
    }
/* Constructors */


    @Override
    public boolean sendMetric(Metric metric) {
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
        logger.fine(awsmetric.toString());

        l.add(awsmetric);
        try {
            metricRequest.setMetricData(l);
            client.putMetricData(metricRequest);
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
