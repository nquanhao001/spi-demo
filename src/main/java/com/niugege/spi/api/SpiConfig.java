package com.niugege.spi.api;

/**
 * the spi configuration
 */
public class SpiConfig {
    /**
     * 名称
     */
    private String name;
    /**
     * 同组互斥
     */
    private boolean mutex;
    /**
     * spi执行顺序，值越大越靠后执行，默认：0
     */
    private int priority;

    public SpiConfig() {
    }

    public SpiConfig(String name, boolean mutex, int priority) {
        this.name = name;
        this.mutex = mutex;
        this.priority = priority;
    }

    public static SpiConfig create() {
        return new SpiConfig();
    }

    public static SpiConfig create(String name, boolean mutex, int priority) {
        return new SpiConfig(name, mutex, priority);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMutex() {
        return mutex;
    }

    public void setMutex(boolean mutex) {
        this.mutex = mutex;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "SpiConfig{"
                + "name='" + name + '\''
                + ", mutex=" + mutex
                + ", priority=" + priority +
                '}';
    }
}

