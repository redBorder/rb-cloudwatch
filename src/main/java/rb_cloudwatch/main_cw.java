package rb_cloudwatch;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by alberto on 7/5/15.
 */
public class main_cw {
    /**
     *
     */

    public static void main (String[] args) {

        AmazonCloudWatch service = new AmazonCloudWatchClient();

        PutMetricDataRequest cw_data = new PutMetricDataRequest();
        cw_data.setNamespace("PruebaJavaSDK");


        Collection<MetricDatum> l = new LinkedList<MetricDatum>();


        while(true) {
            MetricDatum metricdatum = new MetricDatum();
            metricdatum.setMetricName("metricaDePruebaJava");
            metricdatum.setUnit("Count");
            metricdatum.setTimestamp(new Date());
            metricdatum.setValue(Double.valueOf("5"));

            l.add(metricdatum);

            cw_data.setMetricData(l);
            service.putMetricData(cw_data);

            //Logger.getLogger("aloh").log(Level.INFO, "prueba");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {}
        }

    }
}
