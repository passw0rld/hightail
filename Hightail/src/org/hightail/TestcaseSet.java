package org.hightail;

import java.util.ArrayList;
import org.hightail.ui.ProblemJPanel;

public class TestcaseSet extends ArrayList<Testcase> {
    private int noOfFinishedTests = 0;
    private ProblemJPanel callback;
    private ArrayList<Thread> procSet;
        
    public void run(ProblemJPanel callback) {
        this.callback = callback;
        procSet = new ArrayList<Thread>();
        for (Testcase test : this) {
            test.setCallback(this);
            TestThread thr = new TestThread(test);
            thr.start();
            procSet.add(thr);
       }
    }
    
    public void abort() {
        // TODO
    }
    
    void notifyResultsOfSingleTestcase(int index) {
        callback.notifyResultsOfSingleTestcase(index);
        noOfFinishedTests++;
        if (noOfFinishedTests >= this.size()) {
            callback.notifyEndOfTesting();
        }
    }
}
