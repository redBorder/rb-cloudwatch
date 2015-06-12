package rb_cloudwatch.configuration;

/**
 * Created by alberto on 2/6/15.
 */
public class Configuration {

    private String zookeeper_host;
    private String zookeeper_port;
    private String kafka_consumer_group_id;
    private String zookeeper_session_timeout;
    private String zookeeper_sync_time;
    private String autocommit_interval;
    private String kafka_topic;
    private String thread_number;
    private String region;

    public String getZookeeper_host() {
        return zookeeper_host;
    }
    public void setZookeeper_host(String zookeeper_host) {
        this.zookeeper_host = zookeeper_host;
    }
    public String getZookeeper_port() {
        return zookeeper_port;
    }
    public void setZookeeper_port(String zookeeper_port) {
        this.zookeeper_port = zookeeper_port;
    }
    public String getKafka_consumer_group_id() {
        return kafka_consumer_group_id;
    }
    public void setKafka_consumer_group_id(String kafka_consumer_group_id) {
        this.kafka_consumer_group_id = kafka_consumer_group_id;
    }
    public String getZookeeper_session_timeout() {
        return zookeeper_session_timeout;
    }
    public void setZookeeper_session_timeout(String zookeeper_session_timeout) {
        this.zookeeper_session_timeout = zookeeper_session_timeout;
    }
    public String getZookeeper_sync_time() {
        return zookeeper_sync_time;
    }
    public void setZookeeper_sync_time(String zookeeper_sync_time) {
        this.zookeeper_sync_time = zookeeper_sync_time;
    }
    public String getAutocommit_interval() {
        return autocommit_interval;
    }
    public void setAutocommit_interval(String autocommit_interval) {
        this.autocommit_interval = autocommit_interval;
    }
    public String getKafka_topic() {
        return kafka_topic;
    }
    public void setKafka_topic(String kafka_topic) {
        this.kafka_topic = kafka_topic;
    }
    public String getThread_number() {
        return thread_number;
    }
    public void setThread_number(String thread_number) {
        this.thread_number = thread_number;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

}
