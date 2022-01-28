package fr.miage.fsgbd;

public class Property<T> {
    T value;

    public T setValue(String Pvalue){
        Class<?> clazz = (Class<?>) getClass().getTypeParameters()[0].getBounds()[0];
        if(clazz.isAssignableFrom(String.class)){
            value = (T) Pvalue;
        }else if (clazz.isAssignableFrom(Integer.class)) {
            value = (T) Integer.valueOf(Pvalue);
        } else if (clazz.isAssignableFrom(Boolean.class)) {
            value = (T) Boolean.valueOf(Pvalue);
        } else if (clazz.isAssignableFrom(Double.class)) {
            value = (T) Double.valueOf(Pvalue); 
        } else {
            throw new IllegalArgumentException("Bad type.");
        }
        return value;
    }
}
