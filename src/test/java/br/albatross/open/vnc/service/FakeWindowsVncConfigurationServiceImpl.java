package br.albatross.open.vnc.service;

import java.io.File;
import java.util.Optional;

import org.junit.platform.commons.JUnitException;

import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;

public class FakeWindowsVncConfigurationServiceImpl implements WindowsSpecificConfiguration {

    @Override
    public Optional<String> getVncDirectory() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getUser() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getPassword() {
        return Optional.empty();
    }

    @Override
    public void saveUser(String username) {
        throw new JUnitException("Método não implementado na classe " + this.getClass());
    }

    @Override
    public void savePassword(String plainTextPassword) {
        throw new JUnitException("Método não implementado na classe " + this.getClass());
    }

    @Override
    public String setVncDirectory(String directoryAbsolutePath) {
        return null;
    }

    @Override
    public Optional<File> autoDetectVncDirectory() {
        throw new JUnitException("Método não implementado na classe " + this.getClass());
    }

    @Override
    public void clearCredentials() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
