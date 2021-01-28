import com.github.rawls238.scientist4j.Experiment;
import com.github.rawls238.scientist4j.Result;
import com.github.rawls238.scientist4j.metrics.MicrometerMetricsProvider;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.TimeUnit;

public class SayHelloExperiment extends Experiment<String> {

    private static final String EXPERIMENT_NAME = "say_hello";
    private static final String CONTROL_METRICS_LABEL = "scientist." + EXPERIMENT_NAME + ".control";
    private static final String CANDIDATE_METRICS_LABEL = "scientist." + EXPERIMENT_NAME + ".candidate";

    public SayHelloExperiment() {
        super(EXPERIMENT_NAME, new MicrometerMetricsProvider());
    }

    @Override
    protected void publish(Result<String> r) {
        System.out.println("Outputs match: " + r.getMatch().orElseThrow());

        var registry = (MeterRegistry) getMetricsProvider().getRegistry();
        System.out.println("Control execution took " + registry.timer(CONTROL_METRICS_LABEL).totalTime(TimeUnit.SECONDS) + " seconds");
        System.out.println("Candidate execution took " + registry.timer(CANDIDATE_METRICS_LABEL).totalTime(TimeUnit.SECONDS) + " seconds");
    }

}
