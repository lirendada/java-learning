package singleton_pattern;

public enum Singleton {
    INSTANCE;

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
