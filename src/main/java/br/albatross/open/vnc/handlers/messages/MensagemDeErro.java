package br.albatross.open.vnc.handlers.messages;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;

public final class MensagemDeErro {

    public static final String VNC_NOT_FOUND_SUMMARY_MESSAGE = getVncNotFoundSummarizedMessage();

    public static final String VNC_NOT_FOUND_DETAILED_MESSAGE = getVncNotFoundDetailedMessage();

    private static String getVncNotFoundSummarizedMessage() {
        return IS_WINDOWS_OS ? "Pasta de Instalação do UltraVNC® Viewer Não Encontrada" : "SSVNC Viewer não instalado";
    }

    private static String getVncNotFoundDetailedMessage() {
        return IS_WINDOWS_OS ? 
                "Nas Configurações do OpenVNC, defina a pasta em que está localizado o .exe do UltraVNC® Viewer." : 
                "SSVNC não encontrado ou não instalado. Caso esteja em uma distro baseada no Debian/Ubuntu,\n instale-lo utilizando a linha de comando 'sudo apt -y install ssvnc'.\n Caso já tenha realizado o procedimento, reinicie o aplicativo e tente novamente. ";
    }

}
