package rb_cloudwatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

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
        Metric metric = new MetricImpl(new Date(), "JunitSensorTest", "JunitTest", Double.valueOf(2), "Count");
        CloudwatchConnector connector = new CloudwatchConnectorImpl();
        connector.sendMetric(metric);

    }
}