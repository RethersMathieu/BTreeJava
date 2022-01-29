package fr.miage.fsgbd.property;

public class PropertyDouble extends Property<Double>{
    @Override
    public Double to(String value) {
        return Double.valueOf(value);
    }
}
