import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nojpg on 19.09.17.
 */
public interface ProcessInfo {

    public default void dumpProcessInfo(ProcessHandle processHandle){
        System.out.println("PROCESS INFORMATION");
        System.out.println("===================");
        System.out.printf("Process id: %d%n", processHandle.pid());
        ProcessHandle.Info info = processHandle.info();
        System.out.printf("Command: %s%n", info.command().orElse(""));
        String[] args = info.arguments().orElse(new String[]{});
        System.out.println("Arguments:");
        for (String arg: args)
            System.out.printf("   %s%n", arg);
        System.out.printf("Command line: %s%n", info.commandLine().orElse(""));
        System.out.printf("Start time: %s%n",
                info.startInstant().orElse(Instant.now()).toString());
        System.out.printf("Run time duration: %sms%n",
                info.totalCpuDuration()
                        .orElse(Duration.ofMillis(0)).toMillis());
        System.out.printf("Owner: %s%n", info.user().orElse(""));
        System.out.println();
        System.out.println();
    }

    public default void allProcessInfo(){
        ProcessHandle.allProcesses()
                .filter(processHandle -> processHandle.info().command().isPresent())
                .limit(4)
                .forEach(this::dumpProcessInfo);
    }

    public default void terminatedProcessInfo() throws IOException, ExecutionException, InterruptedException {
        Process process = new ProcessBuilder("kwrite").start();
        ProcessHandle processHandle = process.toHandle();
        CompletableFuture<ProcessHandle> onExit = processHandle.onExit();
        onExit.get();
        onExit.thenAccept(processHandle_ -> System.out.printf("PID %d terminated%n",
                processHandle_.pid()));
    }

    public default void currentProcessInfo() throws IOException, InterruptedException {
        dumpProcessInfo(ProcessHandle.current());
        Process process = new ProcessBuilder("kwrite").start();
        dumpProcessInfo(process.toHandle());
        process.waitFor();
        dumpProcessInfo(process.toHandle());
    }
}
