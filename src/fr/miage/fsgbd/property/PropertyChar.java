package fr.miage.fsgbd.property;

public class PropertyChar extends Property<Character>{
    @Override
    public Character to(String value) {
        return value.charAt(0);
    }
}
