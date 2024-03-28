package br.albatross.open.vnc.connections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.albatross.open.vnc.builders.ConnectionBuilder;
import br.albatross.open.vnc.builders.UltraVncConnectionBuilder;
import br.albatross.open.vnc.service.FakeWindowsVncConfigurationServiceWithNotNullUsernameAndPasswordImpl;
import br.albatross.open.vnc.service.FakeWindowsVncConfigurationServiceWithNotNullVncDirectoryAndNullUsernameAndPasswordImpl;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;

/**
 *  Classe para testar se a String de conexão do UltraVNC Viewer, para o Microsoft Windows está sendo gerada corretamente.
 *  
 *  Foi criada uma implementação Fake da interface de <code>Configuration</code>.
 * 
 * @author breno.brito
 */
public class UltraVNCConnectionStringTest {

	private WindowsSpecificConfiguration configuration;
	private ConnectionBuilder connectionBuilder;

	@BeforeEach
	void init() {
		configuration     = new FakeWindowsVncConfigurationServiceWithNotNullVncDirectoryAndNullUsernameAndPasswordImpl();
		connectionBuilder = new UltraVncConnectionBuilder(configuration);
	}

    @Test
    void testaSeAStringDeConexaoApenasComHostEstaRetornandooCaminhoCorretoDoVNC() {

        Connection connection = connectionBuilder.createConnection("10.40.10.100");

        String expectedString =
                "C:\\Program Files\\uvnc bvba\\UltraVnc\\vncviewer.exe -connect -autoreconnect 3 -reconnectcounter 50 10.40.10.100:5900";
        
        assertEquals(expectedString, connection.getConnectionString());

    }

    

    @Test
    void testaSeAStringDeConexaoComHostEUsuarioEstaRetornandooCaminhoCorretoDoVNC() {

        Connection connection = connectionBuilder.createConnection("10.40.10.100", "albatross18", "connection-password-123");

        String expectedString = 
                "C:\\Program Files\\uvnc bvba\\UltraVnc\\vncviewer.exe -connect -autoreconnect 3 -reconnectcounter 50 10.40.10.100:5900 -user albatross18 -password connection-password-123";

        assertEquals(expectedString, connection.getConnectionString());

    }

    @Test
    void testaSeAStringDeConexaoComDiretorioVNCHostUsuarioESenhaEstaCorreta() {

    	WindowsSpecificConfiguration configuration =  new FakeWindowsVncConfigurationServiceWithNotNullUsernameAndPasswordImpl();
    	ConnectionBuilder connectionBuilder        =  new UltraVncConnectionBuilder(configuration);

    	Connection connection = connectionBuilder.createConnection("10.40.10.100");

    	String expectedString = 
    			"C:\\Program Files\\uvnc bvba\\UltraVnc\\vncviewer.exe -connect -autoreconnect 3 -reconnectcounter 50 10.40.10.100:5900 -user albatross18 -password connection-password-123";

    	assertEquals(expectedString, connection.getConnectionString());

    }

}