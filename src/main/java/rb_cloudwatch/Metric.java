package rb_cloudwatch;

import java.util.Date;

/**
 * Created by alberto on 11/5/15.
 */
public interface Metric {

    public Date getTimestamp();
    public String getSensor_name();
    public String getMonitor();
    public Double getValue();
    public String getType();

    public void setTimestamp(Date timestamp);
    public void setSensor_name(String sensorName);
    public void setMonitor(String monitor);
    public void setValue(Double value);
    public void setType(String type);
}
