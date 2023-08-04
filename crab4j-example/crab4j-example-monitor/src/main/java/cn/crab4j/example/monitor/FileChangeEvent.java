package cn.crab4j.example.monitor;

import cn.crab4j.core.eventbus.convention.Event;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 文件变更事件
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class FileChangeEvent implements Event {

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


    private static final AtomicLong SEQUENCE = new AtomicLong(0);

    private final long sequence = SEQUENCE.getAndIncrement();

    public long sequence() {
        return sequence;
    }

}
