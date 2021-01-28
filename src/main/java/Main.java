import com.github.rawls238.scientist4j.Experiment;

import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) throws Exception {
        Experiment<String> exp = new SayHelloExperiment();
        exp.run(sayHello(), newSayHello());
    }

    private static Callable<String> sayHello() {
        return () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        };
    }

    private static Callable<String> newSayHello() {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        };
    }

}
