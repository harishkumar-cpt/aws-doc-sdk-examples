/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package cloudwatch.src.main.java.aws.example.cloudwatch;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

/**
 * Puts a sample metric data point
 */
public class PutMetricData {

    public static void main(String[] args) {

        final String USAGE =
            "To run this example, supply a data point value\n" +
            "Ex: PutMetricData <data-point-value>\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        Double dataPointValue = Double.parseDouble(args[0]);

        final AmazonCloudWatch cloudWatch = AmazonCloudWatchClientBuilder.defaultClient();

        Dimension dimension = new Dimension()
            .withName("UNIQUE_PAGES")
            .withValue("URLS");

        MetricDatum metricDatum = new MetricDatum()
            .withMetricName("PAGES_VISITED")
            .withUnit(StandardUnit.None)
            .withValue(dataPointValue)
            .withDimensions(dimension);

        PutMetricDataRequest request = new PutMetricDataRequest()
            .withNamespace("SITE/TRAFFIC")
            .withMetricData(metricDatum);

        PutMetricDataResult response = cloudWatch.putMetricData(request);

        System.out.printf("Successfully put data point %f", dataPointValue);
    }
}
