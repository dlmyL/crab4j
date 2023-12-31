package cn.crab4j.example.monitor;

import cn.dlmyl.crab4j.core.EventBus;
import cn.dlmyl.crab4j.core.Bus;
import org.junit.Test;

/**
 * 监听器测试
 *
 * @author dlmyL
 */
public class MonitorTest {

    private static final String TARGET_PATH = "C:\\Users\\Administrator\\Desktop";

    @Test
    public void test_monitor() throws Exception {
        Bus bus = new EventBus();
        bus.register(new FileChangeListener());

        TargetMonitor monitor = new DirectoryTargetMonitor(bus, TARGET_PATH);

        //Executors.newSingleThreadScheduledExecutor().schedule(() -> {
        //    try {
        //        monitor.stopMonitor();
        //    } catch (Exception e) {
        //        System.out.printf("The monitor stopped with an error，error message: %s %n",
        //                e.getCause().getMessage());
        //    }
        //}, 5, TimeUnit.MINUTES);

        monitor.startMonitor();
    }

}
