package br.albatross.open.vnc.starters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.albatross.open.vnc.builders.ConnectionBuilder;
import br.albatross.open.vnc.builders.UltraVncConnectionBuilder;
import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.service.FakeWindowsVncConfigurationServiceImpl;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;

/**
 *  Classe para testar o Starter de conexão do UltraVNC Viewer, no Microsoft Windows.
 *  
 *  Foi criada uma implementação Fake da interface de <code>ConnectionStarter</code>.
 * 
 * @author breno.brito
 */
public class UltraVNCConnectionStarterTest {

	private WindowsSpecificConfiguration configuration;
	private ConnectionBuilder connectionBuilder;
	private ConnectionStarter connectionStarter;

	@BeforeEach
	void init() {
		configuration     = new FakeWindowsVncConfigurationServiceImpl();
		connectionBuilder = new UltraVncConnectionBuilder(configuration);
		connectionStarter = new FakeVncConnectionStarterForWindowsUltraVncViewerImpl();
	}

    @Test
    void deveLancarRuntimeExceptionCasoODiretorioDoVNCSejaNulo() {

        Connection connection = connectionBuilder.createConnection("10.40.10.100");

        String expectedString =
                "vncviewer.exe -connect -autoreconnect 3 -reconnectcounter 50 10.40.10.100:5900";

        assertEquals(expectedString, connection.getConnectionString());
        assertThrows(RuntimeException.class, () -> {
        	connectionStarter.startConnection(connection);
        });

    } 

}
