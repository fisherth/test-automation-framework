package com.notsruht.taf.ui.pages;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;

@Retention(RUNTIME)
@Target(TYPE)
@Component
@ScenarioScope
@Lazy
/**
 * Specifies a class as a TAF Page Object
 */
public @interface TAFPageObject {
	String value() default "";
}
