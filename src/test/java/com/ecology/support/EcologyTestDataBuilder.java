package com.ecology.support;

import com.ecology.Ecology;

public final class EcologyTestDataBuilder {
    private String ecologistName = "Зелена Оксана Вікторівна";
    private String sensorId = "S-101";
    private String measurementDate = "2024-09-14";
    private Double paramPm25 = 18.0;
    private Double paramNo2 = 32.0;
    private Double paramPh = 7.1;
    private String dangerLevel = "Низький";
    private Double latitude = 50.4501;
    private Double longitude = 30.5234;
    private String authorityName = "Київська МДА";
    private String authorityAddress = "м. Київ, вул. Хрещатик, 36";
    private String authorityPhone = "380444670001";
    private Integer ecologistExperience = 6;

    public static EcologyTestDataBuilder anEcology() {
        return new EcologyTestDataBuilder();
    }

    public EcologyTestDataBuilder withEcologistName(String value) {
        this.ecologistName = value;
        return this;
    }

    public EcologyTestDataBuilder withSensorId(String value) {
        this.sensorId = value;
        return this;
    }

    public EcologyTestDataBuilder withDangerLevel(String value) {
        this.dangerLevel = value;
        return this;
    }

    public Ecology build() {
        return new Ecology(
            ecologistName,
            sensorId,
            measurementDate,
            paramPm25,
            paramNo2,
            paramPh,
            dangerLevel,
            latitude,
            longitude,
            authorityName,
            authorityAddress,
            authorityPhone,
            ecologistExperience
        );
    }
}
