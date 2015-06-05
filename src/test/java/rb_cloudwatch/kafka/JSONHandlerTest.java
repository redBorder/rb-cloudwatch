package rb_cloudwatch.kafka;

import org.junit.Test;
import rb_cloudwatch.model.Metric;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by alberto on 4/6/15.
 */
public class JSONHandlerTest {

    @Test
    public void testProcessJSON() throws Exception {
        JSONHandler jsonHandler = new JSONHandler();
        String jsonTest = "{\"timestamp\":1430836987,\"sensor_name\":\"i579986fc\",\"monitor\":\"latency\",\"value\":\"0.130000\",\"type\":\"system\",\"unit\":\"ms\"}";
        Metric metric;
        try {
            metric = jsonHandler.processJSON(jsonTest);
            assertEquals(metric.getTimestamp().getTime()/1000, 1430836987);
            assertEquals(metric.getSensor_name(), "i579986fc");
            assertEquals(metric.getMonitor(), "latency");
            assertEquals(metric.getValue(), 0.130000, 0);
            assertEquals(metric.getType(), "system");
            assertEquals(metric.getUnit(), "ms");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            metric = jsonHandler.processJSON("jsonmalformed");
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}