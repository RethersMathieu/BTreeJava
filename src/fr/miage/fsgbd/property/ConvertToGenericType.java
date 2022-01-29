package fr.miage.fsgbd.property;

public class ConvertToGenericType<T> {
    private final Property<?>[] properties = {
        new PropertyInt(),
        new PropertyString(),
        new PropertyFloat(),
        new PropertyDouble(),
        new PropertyChar(),
        new PropertyBool()
    };

    public T get(String arg) {
        T value = null;
        for (Property<?> p : properties) {
            try {
                value = (T) p.to(arg);
                break;
            }
            catch (Exception e) {
                value = null;
            }
        }
        return value;
    }
}
