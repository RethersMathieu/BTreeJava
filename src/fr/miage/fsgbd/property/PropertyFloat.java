package fr.miage.fsgbd.property;

public class PropertyFloat extends Property<Float>{
    
    @Override
    public Float to(String value) {
        return Float.valueOf(value);
    }
}
