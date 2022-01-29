package fr.miage.fsgbd.property;

public class PropertyInt extends Property<Integer> {

    @Override
    public Integer to(String value) {
        return Integer.valueOf(value);
    }
    
}
