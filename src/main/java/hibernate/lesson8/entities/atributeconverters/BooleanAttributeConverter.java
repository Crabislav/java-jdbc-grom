package hibernate.lesson8.entities.atributeconverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class BooleanAttributeConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        Optional.ofNullable(attribute)
                .orElseThrow(() -> new IllegalArgumentException("attribute is null"));

        return attribute ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        Optional.ofNullable(dbData)
                .orElseThrow(() -> new IllegalArgumentException("dbData is null"));

        switch (dbData) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                throw new IllegalArgumentException("Value of dbData=" + dbData + " is not supported");
        }

    }
}
