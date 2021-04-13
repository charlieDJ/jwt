package com.example.boot.config;

import com.example.boot.common.anno.ApiContainProperty;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author dj
 * @date 2021/3/15
 */
@Configuration
public class SwaggerModelReader implements ParameterBuilderPlugin {

    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        Optional<ApiContainProperty> containProperty = methodParameter.findAnnotation(ApiContainProperty.class);
        if (!containProperty.isPresent()) {
            return;
        }
        Class originClass = parameterContext.resolvedMethodParameter().getParameterType().getErasedType();
        //model 名称
        String name = originClass.getSimpleName() + "Model" + UUID.randomUUID().toString();
        String properties = containProperty.get().value();
        Integer annoType = 1;
        try {
            //像documentContext的Models中添加我们新生成的Class
            TypeResolver typeResolver = new TypeResolver();
            parameterContext.getDocumentationContext()
                    .getAdditionalModels()
                    .add(typeResolver.resolve(createRefModel(properties, name, originClass, annoType)));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        parameterContext.parameterBuilder()  //修改model参数的ModelRef为我们动态生成的class
                .parameterType("body")
                .modelRef(new ModelRef(name))
                .name(name);
    }


    /**
     * 根据propertys中的值动态生成含有Swagger注解的javaBeen
     */
    private Class createRefModel(String properties, String name, Class origin, Integer annoType) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(origin.getPackage().getName() + "." + name);
        try {
            Field[] fields = origin.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            List<String> dealProperties = Arrays.stream(properties.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            List<Field> dealFields = fieldList
                    .stream()
                    .filter(s -> dealProperties.contains(s.getName()))
                    .collect(Collectors.toList());
            createCtFields(dealFields, ctClass);
            return ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    /**
     * 构建字段
     * @param dealFields 需要保存的字段
     * @param ctClass 处理类
     * @throws CannotCompileException
     * @throws NotFoundException
     */
    public void createCtFields(List<Field> dealFields, CtClass ctClass) throws CannotCompileException, NotFoundException {
        for (Field field : dealFields) {
            CtField ctField = new CtField(ClassPool.getDefault().get(field.getType().getName()), field.getName(), ctClass);
            ctField.setModifiers(Modifier.PUBLIC);
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            String apiModelPropertyValue = java.util.Optional.ofNullable(annotation)
                    .map(ApiModelProperty::value)
                    .orElse("");
            //添加model属性说明
            if (isNotBlank(apiModelPropertyValue)) {
                ConstPool constPool = ctClass.getClassFile().getConstPool();
                AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                Annotation ann = new Annotation(ApiModelProperty.class.getName(), constPool);
                ann.addMemberValue("value", new StringMemberValue(apiModelPropertyValue, constPool));
                attr.addAnnotation(ann);
                ctField.getFieldInfo().addAttribute(attr);
            }
            ctClass.addField(ctField);
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
