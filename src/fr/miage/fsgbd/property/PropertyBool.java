package fr.miage.fsgbd.property;

public class PropertyBool extends Property<Boolean>{
    @Override
    public Boolean to(String value) {
        return Boolean.valueOf(value);
    }
}
