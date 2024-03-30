package br.albatross.open.vnc.connections.ssvnc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.SsVncConnection;

/**
 *  Classe para testar se a String de conexão do SSVNC Viewer, para o GNU/Linux está sendo gerada corretamente.
 *  
 *  Foi criada uma implementação Fake da interface de <code>Configuration</code>.
 * 
 * @author breno.brito
 * 
 */
public class SSVncConnectionStringTest {

    private Connection connection;

	@BeforeEach
	void init () {
	    
	    connection = new SsVncConnection("10.40.10.100");

	}

	/**
	 * 
	 * Testa se será gerada corretamente a String de conexão quando o usuário fornece apenas o Host da conexão.
	 * 
	 * @author breno.brito
	 */
    @Test
    void testaAStringDeConexaoApenasComHost() {

    	String expectedString = 
    	        "ssvncviewer -quality 2 -compresslevel 7 -16bpp 10.40.10.100:5900";

    	assertEquals(expectedString, connection.getConnectionString());

    }

    /**
     * 
     * Testa se será gerada corretamente a String de conexão quando o usuário fornece o Host e o Username.
     * 
     * @author breno.brito
     */
    @Test
    void testaAStringDeConexaoComHostEUsername() {

        connection
            .setUsername("breno.brito");

        String expectedString = 
                "ssvncviewer -quality 2 -compresslevel 7 -16bpp 10.40.10.100:5900 -mslogon breno.brito";

        assertEquals(expectedString, connection.getConnectionString());

    }

    /**
     * 
     * Testa se será gerada corretamente a String de conexão quando o usuário fornece apenas o Host mas o Username em branco (Não Nulo, String vazia).
     * 
     * @author breno.brito
     */
    @Test
    void testaAStringDeConexaoComHostEUsernameComoUmaStringVazia() {

        connection
            .setUsername("");

        String expectedString = 
                "ssvncviewer -quality 2 -compresslevel 7 -16bpp 10.40.10.100:5900";

        assertEquals(expectedString, connection.getConnectionString());

    }

    /**
     * 
     * Testa se será gerada corretamente a String de conexão quando o usuário fornece apenas o Host mas o Username em branco (Não Nulo, String vazia).
     * 
     * @author breno.brito
     */
    @Test
    void testaAStringDeConexaoComHostComoUmaStringVaziaESemUsername() {

        Connection emptyStringHostConnection = new SsVncConnection("");

        String expectedString = 
                "ssvncviewer -quality 2 -compresslevel 7 -16bpp";

        assertEquals(expectedString, emptyStringHostConnection.getConnectionString());

    }

    /**
     * 
     * Testa se será gerada corretamente a String de conexão quando o usuário fornece apenas o Host mas o Username em branco (Não Nulo, String vazia).
     * 
     * @author breno.brito
     */
    @Test
    void testaAStringDeConexaoComHostComoUmaStringVaziaEComUsernameDefinido() {

        Connection emptyStringHostWithUsernameConnection = new SsVncConnection("");
        emptyStringHostWithUsernameConnection.setUsername("breno.brito");

        String expectedString = 
                "ssvncviewer -quality 2 -compresslevel 7 -16bpp";

        assertEquals(expectedString, emptyStringHostWithUsernameConnection.getConnectionString());

    }

}
