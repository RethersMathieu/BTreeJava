package fr.miage.fsgbd.property;

import java.lang.reflect.ParameterizedType;

abstract class Property<T> {
    protected Class<T> clazz;

    protected Property() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    public abstract T to(String value);

    public static void main(String[] args) {
        ConvertToGenericType<Integer> convert = new ConvertToGenericType<>();
        System.out.println(convert.get("0").getClass().getSimpleName());
    }
}
