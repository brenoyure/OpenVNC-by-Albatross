package br.albatross.open.vnc.services.configurations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import jakarta.inject.Qualifier;

@Documented
@Retention(RUNTIME)
@Qualifier
/**
 * 
 * Qualifier that marks an implementation of the <code>WindowsSpecificConfiguration</code>
 * interfaces as the default.
 * 
 * @author breno.brito
 */
public @interface WindowsSpecificSettings {

}
