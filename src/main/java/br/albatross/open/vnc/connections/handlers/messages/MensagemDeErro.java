package br.albatross.open.vnc.connections.handlers.messages;

public class MensagemDeErro {

    public static final String VNC_FOLDER_NOT_FOUND_SUMMARY_MESSAGE = "Pasta de Instalação do VNC Viewer Não Encontrada";

    public static final String VNC_FOLDER_NOT_FOUND_DETAILED_MESSAGE = "Defina a variável de ambiente VNC_HOME como a pasta em que está localizado o .exe do VNC Viewer.\nCaso já tenha feito o procedimento, reinicie o aplicativo e tente novamente.";

    public static final String VNC_PASSWORD_NOT_VALID_SUMMARY_MESSAGE = "Senha de Rede Não Encontrada";
    
    public static final String VNC_PASSWORD_NOT_VALID_DETAILED_MESSAGE = "Defina a variável de ambiente domain_password como a sua senha de rede, que o VNC utilizará para enviar o acesso para a máquina do usuário.\nCaso já tenha feito o procedimento, reinicie o aplicativo e tente novamente.";    
    
}
