package cn.crab4j.example.monitor;

import com.crab4j.core.annotation.Subscribe;

/**
 * 文件变更监听
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.println("==================");
        System.out.printf("目标路径：[%s] %n", event.getPath());
        System.out.printf("具体事件：[%s] %n", event.getKind());
        System.out.printf("事件次数：[%s] %n", event.sequence());
        System.out.println("==================");

        // TODO 数据上报或入库

    }

}
