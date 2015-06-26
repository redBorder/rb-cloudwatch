package rb_cloudwatch.cloudwatch;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import rb_cloudwatch.configuration.Configuration;
import rb_cloudwatch.model.Metric;

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
    UnitMapping unitMapping;
    Configuration configuration;
    //Logger for this class
    private static final Logger logger = Logger.getLogger(CloudwatchConnectorImpl.class.getName());

    /* Constructors */
    public CloudwatchConnectorImpl(Configuration configuration) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(configuration.getAws_key(), configuration.getAws_secret());
        this.client = new AmazonCloudWatchClient(awsCreds);
        client.setRegion(Region.getRegion(Regions.fromName(configuration.getRegion())));
        unitMapping = new UnitMapping();
        this.configuration = configuration;
    }

    @Override
    public synchronized boolean sendMetric(Metric metric) {
        boolean error = false; //Variable to indicate error status

        Collection<MetricDatum> l = new LinkedList<MetricDatum>();
        MetricDatum awsmetric = new MetricDatum();
        PutMetricDataRequest metricRequest = new PutMetricDataRequest();
        logger.fine("Created AWS Metric data structures");

        metricRequest.setNamespace(configuration.getNamespace());

        awsmetric.setMetricName(metric.getMonitor());
        awsmetric.setUnit(unitMapping.getMappedUnit(metric.getUnit()));
        awsmetric.setTimestamp(metric.getTimestamp());
        awsmetric.setValue(metric.getValue());
        Dimension instanceId = new Dimension();
        instanceId.setName("InstanceId");
        if(configuration.getIs_aws().equals("true")) {
            instanceId.setValue("i-" + metric.getSensor_name().substring(1));
        } else {
            instanceId.setValue(metric.getSensor_name());
        }

        awsmetric.withDimensions(instanceId);

        l.add(awsmetric);
        metricRequest.setMetricData(l);
        logger.fine(awsmetric.toString());

        try {

            client.putMetricData(metricRequest);
            logger.log(Level.FINE, "putMetricData executed");
        } catch (AmazonServiceException e) {
            logger.log(Level.SEVERE, "Amazon Server Exception", e);
            error = true;
        } catch (AmazonClientException e) {
            logger.log(Level.SEVERE, "Amazon Client Exception", e);
            error = true;
        }
        return error;
    }
}
