# Projeto OpenVNC com JavaFX

<!-- Objetivo Inicial -->
<p>O Objetivo desse mini-projeto seria ajudar os colegas do trabalho, que utilizam o software de acesso remoto UltraVNC para fazer acesso remoto</p>

<p>Mais abaixo falarei sobre os problemas de muitos usuários ainda possuírem a versão antiga do UltraVNC Server, o que gera uma queda significativa na produtividade, quando precisamos realizar acessos remotos nas máquinas dos usuários.</p>

### Projeto Ultra VNC com JavaFX
A versão inicial do aplicativo resolve os seguintes problemas: 
- Caso o acesso remoto caia, o aplicativo reenvia a solicitação de acesso para o usuário, bastando apenas o técnico pedir para que o usuário aceite novamente o acesso.

- Só é necessário digitar a senha de rede (domínio Active Directory) uma única vez, para que o 'openvnc' a salve nas váriáveis de ambiente do Windows (váriável de ambiente do usuário que está logado).

- Não há necessidade de fornecer o usuário, visto que o programa 'lê' a envVar do Windows, "USERNAME";

- A VLAN do prédio é dividia pelos andares, exemplo 2º andar o IP termina com 2.x, onde x é o final do host, criei uns *radio-buttons* para cada andar, facilitando a vida do técnico para que não precise digitar o IP por completo.

- Enfim, espero que gostem do programa, utilizei o Scene Builder para criar a interface, na IDE NetBeans e com o "backend" sendo em Java.

- O UltraVNC aceita acesso remoto, através da linha de comando, então o que esse programinha faz, é basicamente "montar" a string de conexão e passar pro *.exe*  do vncviewer (UltraVNC).



### Principais problemas na Produtividade
- É necessário inserir o usuário e a senha de domínio, toda vez que é necessário conectar remotamente na máquina de um usuário
- Muitos usuários estão com a versão antiga do VNC Server (que recebe o acesso), e por isso, ao executar algo como administrador no Windows, como instalação de programas por exemplo, o acesso remoto cai, nos obrigando a digitar as credenciais novamente.

* Exemplo: Ao abrir o instalador de algum programa, o acesso cai, precisamos pedir para que o usuário aceite novamente o acesso remoto

#### Repetição desnecessária
<p>Precisamos digitar as credenciais para voltarmos o acesso à máquina do usuário, depois temos que fornecer as credenciais novamente para instalação do software, por exemplo, em seguida, o acesso remoto cai novamente</p>

<p>A queda do acesso, se dá ao fato das versões antigas do VNC Server não serem compatíveis totalmente com o Windows 10++</p>

