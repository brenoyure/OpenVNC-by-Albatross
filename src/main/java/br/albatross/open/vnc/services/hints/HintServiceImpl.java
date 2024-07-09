package br.albatross.open.vnc.services.hints;

import br.albatross.open.vnc.configurations.AvailableProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;

public class HintServiceImpl implements HintService<String> {

    private static final Map<Integer, String> hints = new HashMap<>();

    static {

        hints.put(1,
                "Caso esteja recebendo a mensagem de erro 'Authentication Rejected' logo ao abrir uma conexão remota, \npode indicar credenciais inválidas, expiradas ou permissões insuficientes.");

        hints.put(2,
                "O OpenVNC utiliza os softwares (Viewers) UltraVNC® e o SSVNC para funcionar corretamente");

        if (IS_WINDOWS_OS) {
            hints.put(3,
                    "O UltraVNC® Viewer possui suporte a opção de exibir os vários monitores do usuário remoto");
        }

    }

    @Override
    public String getRandomHint() {

        return hints.get(new Random().nextInt(1, hints.size() + 1));

    }

}
