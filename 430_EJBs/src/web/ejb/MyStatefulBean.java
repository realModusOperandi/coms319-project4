package web.ejb;

import javax.ejb.Stateful;

@Stateful
public class MyStatefulBean {

    private int useCount = 0;

    public void useBean() {
        useCount++;
    }

    public int getUseCount() {
        return useCount;
    }
}
