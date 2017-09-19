import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nojpg on 18.09.17.
 */
public class App implements ProcessInfo{
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
//
//        Process p = new ProcessBuilder("kwrite").start();
//        ProcessHandle ph = p.toHandle();
//        System.out.println(ph.isAlive());
////        ph.destroy();
//        System.out.println(ph.pid());
//        ph.supportsNormalTermination();
//        System.out.println(ph.supportsNormalTermination());

        ProcessInfoImpl processInfo = new ProcessInfoImpl();
//        processInfo.allProcessInfo();
//        processInfo.start();
        processInfo.currentProcessInfo();



    }
}
