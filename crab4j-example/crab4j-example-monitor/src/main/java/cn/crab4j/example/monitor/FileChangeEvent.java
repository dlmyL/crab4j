package cn.crab4j.example.monitor;

import com.crab4j.core.event.Event;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 文件变更事件
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class FileChangeEvent extends Event {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }

}
