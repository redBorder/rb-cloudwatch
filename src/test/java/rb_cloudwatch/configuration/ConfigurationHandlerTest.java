package rb_cloudwatch.configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alberto on 2/6/15.
 */
public class ConfigurationHandlerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testReadConfiguration() throws Exception {
        Configuration config = ConfigurationHandler.readConfiguration("src/test/testResources/config.json");
        assertEquals(config.getZk_connect(), "localhost:2181");
        assertEquals(config.getKafka_consumer_group_id(), "rb-cloudwatch");
        assertEquals(config.getZookeeper_session_timeout(), "400");
        assertEquals(config.getZookeeper_sync_time(), "200");
        assertEquals(config.getAutocommit_interval(), "1000");
        assertEquals(config.getKafka_topic(), "rb_monitor");
    }
}