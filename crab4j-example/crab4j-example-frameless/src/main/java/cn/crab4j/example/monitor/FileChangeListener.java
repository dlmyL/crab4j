package cn.crab4j.example.monitor;

import cn.dlmyl.crab4j.annotation.Sub;

/**
 * 文件变更监听
 *
 * @author dlmyL
 */
public class FileChangeListener {

    @Sub
    public void onChange(FileChangeEvent event) {
        System.out.println("==================");
        System.out.printf("目标路径：[%s] %n", event.getPath());
        System.out.printf("具体事件：[%s] %n", event.getKind());
        System.out.printf("事件次数：[%s] %n", event.sequence());
        System.out.println("==================");

        // TODO 数据上报或入库

    }

}
