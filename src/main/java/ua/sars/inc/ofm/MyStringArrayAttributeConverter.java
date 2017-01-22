package ua.sars.inc.ofm;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MyStringArrayAttributeConverter implements AttributeConverter<MyStringArray, String> {

    @Override
    public String convertToDatabaseColumn(MyStringArray attribute) {
        return attribute.toString();
    }

    @Override
    public MyStringArray convertToEntityAttribute(String dbData) {
        return MyStringArray.fromString(dbData);
    }
}
