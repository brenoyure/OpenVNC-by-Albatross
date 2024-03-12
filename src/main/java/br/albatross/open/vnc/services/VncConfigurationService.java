package br.albatross.open.vnc.services;

public class VncConfigurationService implements ConfigurationService {

    private UsuarioService usuarioService;
    
    private PasswordService passwordService;
    
    @Override
    public UsuarioService getUsuarioService() {
        
        if (usuarioService == null) {
            usuarioService = new WindowsUltraVNCUserService();
        }
        
        return usuarioService;
        
    }

    @Override
    public PasswordService getPasswordService() {

        if (passwordService == null) {
            passwordService = new WindowsUltraVNCPasswordService();
        }

        return passwordService;

    }
    
}
