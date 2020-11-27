package interview.library.model.converter;

import javax.persistence.AttributeConverter;

public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

    private static final String STRING_VALUE_TRUE = "Y";
    private static final String STRING_VALUE_FALSE = "N";

    @Override
    public String convertToDatabaseColumn(Boolean isTrue) {
        return isTrue ? STRING_VALUE_TRUE : STRING_VALUE_FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(String str) {
        return str.equals(STRING_VALUE_TRUE);
    }
}
