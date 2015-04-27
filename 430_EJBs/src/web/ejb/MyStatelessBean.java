package web.ejb;

import javax.ejb.Stateless;

@Stateless
public class MyStatelessBean {

    private int useCount = 0;

    public void useBean() {
        useCount++;
    }

    public int getUseCount() {
        return useCount;
    }
}
