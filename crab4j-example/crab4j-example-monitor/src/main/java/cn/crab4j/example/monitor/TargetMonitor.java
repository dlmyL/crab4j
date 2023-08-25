package cn.crab4j.example.monitor;

/**
 * 目标监听器
 *
 * @author dlmyL
 */
public interface TargetMonitor {

    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;

}
