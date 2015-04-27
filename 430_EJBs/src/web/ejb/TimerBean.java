package web.ejb;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class TimerBean {

//    @Resource
//    TimerService timerSVC;
//
//    public void setTimer(int timerDuration) {
//        Timer t = timerSVC.createTimer(timerDuration, new String("Any payload"));
//    }
//
//    @Timeout
//    public void timeout(Timer timer) {
//        System.out.println("A timeout ocurred!");
//        System.out.println(timer.getInfo()); // get payload
//    }
//
//}

    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void doEvery5Sec() { /* ... */}

    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void sendLeftAndRight() {
        System.out.println(new Date() + ": Sending messages left and right.");
    }
}
