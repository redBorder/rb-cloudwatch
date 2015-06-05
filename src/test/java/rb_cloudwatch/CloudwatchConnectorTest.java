package rb_cloudwatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rb_cloudwatch.cloudwatch.CloudwatchConnector;
import rb_cloudwatch.cloudwatch.CloudwatchConnectorImpl;
import rb_cloudwatch.model.Metric;
import rb_cloudwatch.model.MetricImpl;

import java.util.Date;

/**
 * Created by alberto on 2/6/15.
 */
public class CloudwatchConnectorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSendMetric() throws Exception {
        Metric metric = new MetricImpl(new Date().toString(), "JunitSensorTest", "JunitTest", "2", "test", "Count");
        CloudwatchConnector connector = new CloudwatchConnectorImpl();
        connector.sendMetric(metric);

    }
}