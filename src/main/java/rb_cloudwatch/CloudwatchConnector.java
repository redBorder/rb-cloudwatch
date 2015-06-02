package rb_cloudwatch;

/**
 * Created by alberto on 2/6/15.
 */
public interface CloudwatchConnector {

    /**
     * Send data stored in Metric class to AWS Cloudwatch.
     * @param metric
     * @return Return true if metric has been sended correctly, and false in other case.
     */
    public boolean sendMetric(Metric metric);
}
