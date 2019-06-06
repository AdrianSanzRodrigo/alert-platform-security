package com.kschool.alertplatform.security.annotations;

import com.kschool.alertplatform.security.SecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SecurityConfiguration.class})
public @interface EnableJwtAuthentication {
}
