package nl.broslo.brosloremotemouse.socket;

import java.io.Serializable;

/**
 * Created by Bryan on 15-12-2014.
 */
public class Test implements Serializable {

    private String test;

    public Test() {}

    public Test(String test) {
        this.test = test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return this.test;
    }

}
