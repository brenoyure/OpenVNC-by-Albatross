package br.albatross.open.vnc.handlers.messages;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;

public final class MensagemDeErro {

    public static final String VNC_NOT_FOUND_SUMMARY_MESSAGE = getVncNotFoundSummarizedMessage();

    public static final String VNC_NOT_FOUND_DETAILED_MESSAGE = getVncNotFoundDetailedMessage();

    public static final String VNC_PASSWORD_NOT_VALID_SUMMARY_MESSAGE = "Senha de Rede Não Encontrada";

    public static final String VNC_PASSWORD_NOT_VALID_DETAILED_MESSAGE = "Defina a variável de ambiente domain_password como a sua senha de rede.\nO UltraVNC® Viewer a utilizará para enviar o acesso para a máquina do usuário.\nCaso já tenha feito o procedimento, reinicie o aplicativo e tente novamente.";    
    
    private static String getVncNotFoundSummarizedMessage() {
        return IS_WINDOWS_OS ? "Pasta de Instalação do UltraVNC® Viewer Não Encontrada" : "SSVNC Viewer não instalado";
    }

    private static String getVncNotFoundDetailedMessage() {
        return IS_WINDOWS_OS ? "Defina a variável de ambiente VNC_HOME como a pasta em que está localizado o .exe do UltraVNC® Viewer.\nCaso já tenha feito o procedimento, reinicie o aplicativo e tente novamente." : "SSVNC não encontrado ou não instalado. Caso esteja em uma distro baseada no Debian/Ubuntu,\n instale-lo utilizando a linha de comando 'sudo apt -y install ssvnc'.\n Caso já tenha realizado o procedimento, reinicie o aplicativo e tente novamente. ";
    }

}
