package cn.crab4j.example.monitor;

import cn.dlmyl.crab4j.core.Bus;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * 目录监听器
 *
 * @author dlmyL
 */
public class DirectoryTargetMonitor implements TargetMonitor {

    private WatchService watchService;

    private final Path path;

    private final Bus eventBus;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(Bus eventBus, String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(Bus eventBus, String targetPath, String... morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePaths);
    }

    @Override
    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        System.out.printf("The directory [%s] is monitoring... %n", path);
        this.start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    eventBus.post(new FileChangeEvent(child, kind));
                });
            } catch (Exception e) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
        System.out.printf("The directory [%s] is monitor is stopped %n", path);
    }

    @Override
    public void stopMonitor() throws Exception {
        System.out.printf("The directory [%s] monitor will be stop... %n", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.printf("The directory [%s] monitor is stopped  %n", path);
    }

}
