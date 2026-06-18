package com.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

class ScheduleRepositoryTest {

    @Test
    void repositoryExtendsMongoRepositoryWithExpectedGenericTypes() {
        assertTrue(MongoRepository.class.isAssignableFrom(ScheduleRepository.class));

        Type type = ScheduleRepository.class.getGenericInterfaces()[0];
        ParameterizedType parameterizedType = (ParameterizedType) type;

        assertEquals(MongoRepository.class, parameterizedType.getRawType());
        assertEquals(Schedule.class, parameterizedType.getActualTypeArguments()[0]);
        assertEquals(String.class, parameterizedType.getActualTypeArguments()[1]);
    }
}
