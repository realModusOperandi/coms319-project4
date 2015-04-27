package web.ejb;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class MyAsyncBean {

    @Asynchronous
    public Future<String> putDataAsync() {
        lookBusy(5000);
        return new AsyncResult<String>("DB operation completed.");
    }

    public Future<String> putDataBlocking() {
        lookBusy(5000);
        return new AsyncResult<String>("Blocking operation completed");
    }

    private void lookBusy(long duration) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
            }
        }
    }
}
