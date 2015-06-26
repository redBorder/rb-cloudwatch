package rb_cloudwatch.configuration;

import kafka.consumer.ConsumerConfig;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alberto on 2/6/15.
 */
public class ConfigurationHandler {

    private static final Logger logger = Logger.getLogger(ConfigurationHandler.class.getName());

    /**
     * Static method that read configuration file (in JSON) indicated in srcPath variable and generates a POJO object with
     * this configuration
     * Uses Jackson library to parse JSON
     * @param srcPath
     * @return
     */
    public static Configuration readConfiguration(String srcPath) {

        logger.info("Reading configuration file " + srcPath );
        Map<String, String> map = new HashMap<String, String>();    //Hash map with JSON values
        //Object that process JSON data
        org.codehaus.jackson.map.ObjectMapper jacksonMapper = new org.codehaus.jackson.map.ObjectMapper();

        File src; //configuration file
        Configuration config = new Configuration(); //Configuration POJO object

        if(srcPath == null || srcPath.isEmpty()) {
            src = new File("./config.json"); //Default file config
        } else {
            src = new File(srcPath);
        }
        try {
            map = jacksonMapper.readValue(src, new TypeReference<HashMap<String, String>>(){});

            //Generating POJO object with configuration
            config.setZk_connect(ConfigurationHandler.checkProperty(map, "zk_connect"));
            config.setKafka_consumer_group_id(ConfigurationHandler.checkProperty(map, "kafka_consumer_group_id"));
            config.setZookeeper_session_timeout(ConfigurationHandler.checkProperty(map, "zookeeper_session_timeout"));
            config.setZookeeper_sync_time(ConfigurationHandler.checkProperty(map, "zookeeper_sync_time"));
            config.setAutocommit_interval(ConfigurationHandler.checkProperty(map, "autocommit_interval"));
            config.setKafka_topic(ConfigurationHandler.checkProperty(map, "kafka_topic"));
            config.setThread_number(ConfigurationHandler.checkProperty(map, "thread_number"));
            config.setRegion(ConfigurationHandler.checkProperty(map, "region"));
            config.setNamespace(ConfigurationHandler.checkProperty(map, "namespace"));
            config.setAws_key(ConfigurationHandler.checkProperty(map, "accesskey"));
            config.setAws_secret(ConfigurationHandler.checkProperty(map, "secretkey"));

            try {
                config.setIs_aws(map.get("is_aws"));
            } catch (NullPointerException e) {
                logger.warning("Parameter is_aws not found in configuration file, setting to false");
            }

        //if there is an error reading configuration, program finish
        } catch (JsonParseException e) {
            logger.log(Level.SEVERE, "Error reading JSON configuration file, exiting...", e);
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Input/Output Error when try to read configuration file, exiting...", e);
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading JSON configuration file, exiting...", e);
            e.printStackTrace();
            System.exit(1);
        }
        return config;
    }

    /**
     * Static method that creates a ConsumerConfig object with the configuration contained in a Configuration object.
     * readConfiguration method must be executed before execute this method.
     * @param config
     * @return
     */
    public static ConsumerConfig createConsumerConfig(Configuration config) {
        Properties props = new Properties(); //Configuration for
        props.put("zookeeper.connect", config.getZk_connect());
        props.put("group.id", config.getKafka_consumer_group_id());
        props.put("zookeeper.session.timeout.ms", config.getZookeeper_session_timeout());
        props.put("zookeeper.sync.time.ms", config.getZookeeper_session_timeout());
        props.put("auto.commit.interval.ms", config.getAutocommit_interval());
        return new ConsumerConfig(props);
    }

    /**
     * Static method that check if exist the parameter in the JSON configuration file. If it do not exists,
     * throws an Exception
     * @param map
     * @param property
     * @return
     * @throws Exception
     */
    private static String checkProperty(Map<String, String> map, String property) throws Exception {
        String parameter = map.get(property);
        if(parameter.isEmpty()) {
            throw new Exception(property + "not especified");
        } else {
            return parameter;
        }
    }
}
