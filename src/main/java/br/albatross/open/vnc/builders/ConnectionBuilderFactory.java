/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.albatross.open.vnc.builders;

/**
 *  Fábrica de Connection Builders.
 * 
 * @author breno.brito
 */
public final class ConnectionBuilderFactory {

    private static ConnectionBuilder connectionBuilder;

    /**
     * 
     * @return newInstance of <code>ConnectionBuilder</code>
     */
    public static ConnectionBuilder newInstance() {

        if (connectionBuilder == null ) {

            connectionBuilder = new VncConnectionBuilder();
        }

        return connectionBuilder;

    }

}
