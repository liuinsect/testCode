package updateBuffer;

import updateBuffer.impl.DemoHandler;
import updateBuffer.impl.VisitLogHandler;
import updateBuffer.impl.VisitNumHandler;

/**
 * Created by liukunyang on 15-1-28.
 */
public class Main {


    public static class VisitLog{

        String name;

        Integer num;

        public VisitLog(String name, Integer num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public String toString() {
            return "VisitLog{" +
                    "name='" + name + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        DemoHandler demoHandler = new DemoHandler();
//        for (int i = 0; i < 3; i++) {
//            UpdateBuffer.update("1",1,demoHandler);
//        }
//
//
//        VisitLogHandler visitLogHandler = new VisitLogHandler();
//        for (int i = 0; i < 2; i++) {
//            VisitLog visitLog = new VisitLog("name"+i,i);
//            UpdateBuffer.update("topicid1",visitLog,visitLogHandler);
//        }

        VisitNumHandler visitNumHandler = new VisitNumHandler();
        for (int i = 0; i < 19; i++) {
            UpdateBuffer.update("visitNumHandler",1,visitNumHandler);
        }




//        Thread.sleep(5000);
//        VisitLog visitLog = new VisitLog("name22",22);
//        UpdateBuffer.update("topicid1",visitLog,visitLogHandler);

    }
}
