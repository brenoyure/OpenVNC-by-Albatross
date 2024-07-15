package br.albatross.open.vnc.configurations;

public interface AvailableProperties {

    String CONNECTION_USER            = "VNC_USER";
    String CONNECTION_PASSWORD        = "VNC_PASSWORD";

    String APP_MAIN_WINDOW_TITLE      = "OpenVNC by Albatross";
    String APP_ICON_RESOURCE_PATH     = "acesso-remoto.png";
    String DEV_GITHUB_PAGE_LINK       = "https://github.com/brenoyure";

    String ON_WINDOWS_VNC_HOME_DIR    = "VNC_HOME_DIR";

    boolean IS_LINUX_OS   =   System.getProperty("os.name").contains("Linux");
    boolean IS_WINDOWS_OS = !(System.getProperty("os.name").contains("Linux"));

    String SHOW_HINTS_BEFORE_REMOTE = "SHOW_HINTS_BEFORE_REMOTE";

    String GITHUB_RELEASE_NAME      = "2.8.1 OpenVNC - Release";

    String CHECK_FOR_UPDATES_AT_STARTUP = "CHECK_FOR_UPDATES_AT_STARTUP";

}
