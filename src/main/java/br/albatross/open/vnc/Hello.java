package br.albatross.open.vnc;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Hello {

    public String heyDuke() {
        System.out.println("Hey Dukes !!!");
        return "Hey Dukes !!!";
    }

}
