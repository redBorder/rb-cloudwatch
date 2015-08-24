package rb_cloudwatch.configuration;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import rb_cloudwatch.model.Metric;

import java.util.List;

/**
 * Created by alberto on 21/8/15.
 */
@JsonDeserialize(as=MetricFilterImpl.class)
public interface MetricFilter {
    public List<String> getAllowedInstanceIds();
    public void setAllowedInstanceIds(List<String> allowedInstanceIds);
    public List<String> getAllowedMetricNames();
    public void setAllowedMetricNames(List<String> allowedMetricNames);
    public boolean checkMetric(Metric metric);
    public void createAllowedInstanceIdsHashSet();
    public void createAllowedMetricNamesHashSet();
    public String toString();
}
