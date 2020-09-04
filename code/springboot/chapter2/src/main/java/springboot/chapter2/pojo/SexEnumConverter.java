package springboot.chapter2.pojo;

import javax.persistence.AttributeConverter;

public class SexEnumConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum attribute) {
        return attribute.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer dbData) {
        return SexEnum.getEnumById(dbData);
    }
}
