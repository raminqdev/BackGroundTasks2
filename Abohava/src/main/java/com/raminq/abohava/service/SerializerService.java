package com.raminq.abohava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.List;

/*
https://thepracticaldeveloper.com/java-and-json-jackson-serialization-with-objectmapper/#json-serialization-with-java
 */
@Service
public class SerializerService {

    public String serializeToJson(Object data) {
        try {
            return new ObjectMapper().writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T deserializeFromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> deserializeToListFromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(json, mapCollectionType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
