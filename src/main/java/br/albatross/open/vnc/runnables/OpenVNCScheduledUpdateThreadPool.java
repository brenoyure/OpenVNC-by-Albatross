package br.albatross.open.vnc.runnables;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import jakarta.inject.Qualifier;

@Documented
@Retention(RUNTIME)
@Qualifier
public @interface OpenVNCScheduledUpdateThreadPool {

}
