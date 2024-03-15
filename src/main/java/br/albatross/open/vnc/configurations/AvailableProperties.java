package br.albatross.open.vnc.configurations;

public interface AvailableProperties {

	String CONNECTION_USER = "VNC_USER";
	String CONNECTION_PASSWORD = "VNC_PASSWORD";

	boolean IS_LINUX_OS   =   System.getProperty("os.name").contains("Linux");
	boolean IS_WINDOWS_OS = !(System.getProperty("os.name").contains("Linux"));	

}
