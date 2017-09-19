import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nojpg on 19.09.17.
 */
public class ProcessInfoImpl implements ProcessInfo {

    Lock firstLock = new ReentrantLock();
    Lock secondLock = new ReentrantLock();

    void start() throws IOException, InterruptedException {
        firstLock.lock();
        Thread secondThread = new Thread(() -> {
            secondLock.lock();
            firstLock.lock();
        });
        secondThread.start();
        try{
            Thread.sleep(250);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        secondLock.lock();

        secondLock.unlock();
        firstLock.unlock();

    }
}
