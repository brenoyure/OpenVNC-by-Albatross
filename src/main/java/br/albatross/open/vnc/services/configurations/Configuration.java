package br.albatross.open.vnc.services.configurations;

import java.util.Optional;

/**
 *
 * Interface that exposes the OpenVNC Settings
 *
 * @author Breno.Brito
 */
public interface Configuration {

    /**
     * Saves the User to be used when opening a remote connection
     * @param username
     */
    void saveUser(String username);

    /**
     * Saves the Password (if Supported by the VNC Connection Implementation) to be used when opening a remote connection
     * @param plainTextPassword
     */
    void savePassword(String plainTextPassword);

    /**
     *
     * Returns an optional that contains (or not) the saved user.
     *
     * @return savedUsername
     */
    Optional<String> getUser();

    /**
     *
     * Returns an optional that contains (or not) the saved password.
     * (If Supported by the VNC Connection Implementation)
     *
     * @return saved password
     */
    Optional<String> getPassword();


    /**
     * Clears the saved connection credentials, like the saved username and password
     */
    void clearCredentials();

    boolean isShowingHints();

    void showHints(boolean trueOrFalse);

    boolean isCheckForUpdatesEnabledAtStartUp();

    void setToCheckForUpdatesAtStartUpOrNot(boolean trueOrFalse);

}
