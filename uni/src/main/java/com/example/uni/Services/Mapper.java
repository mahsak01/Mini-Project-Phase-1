package com.example.uni.Services;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Mapper<T,DTO> {




    private List<Method> dtoDeclaredMethods;
    private List<Method> entityDeclaredMethods;
    public Object convert(T entity, DTO dto, boolean entityToDTO) {

        dtoDeclaredMethods = Arrays.asList(dto.getClass().getDeclaredMethods());
        entityDeclaredMethods = Arrays.asList(entity.getClass().getDeclaredMethods());

        for (Method dtoMethod : dtoDeclaredMethods) {

            String dtoMethodName = dtoMethod.getName();
            String dtoStart = entityToDTO ? "set" : "get";
            String entityStart = entityToDTO ? "get" : "set";

            if (dtoMethodName.startsWith(dtoStart)) {

                for (Method entityMethod : entityDeclaredMethods) {

                    if (entityMethod.getName().startsWith(entityStart) &&
                            dtoMethod.getName().substring(3).equals(entityMethod.getName().substring(3))) {

                        try {

                            Object getterArgument = entityToDTO ? entity : dto;
                            Object setterArgument = entityToDTO ? dto : entity;

                            Object value = entityToDTO? entityMethod.invoke(getterArgument) : dtoMethod.invoke(getterArgument);
                            if (entityToDTO) {
                                dtoMethod.invoke(setterArgument, value);
                            } else {
                                entityMethod.invoke(setterArgument, value);
                            }
                            break;
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        if (entityToDTO==false)
            return entity;
        return dto;
    }
}
