package web.examples;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(value = 60, unit = TimeUnit.SECONDS)
public class ExampleLockingBean {

    private int value;

    @Lock(LockType.WRITE)
    public void setValue(int newValue) {
        value = newValue;
    }

    @Lock(LockType.READ)
    public int getValue() {
        return value;
    }
}
