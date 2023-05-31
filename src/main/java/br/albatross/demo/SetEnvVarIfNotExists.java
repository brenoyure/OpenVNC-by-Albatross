package br.albatross.demo;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SetEnvVarIfNotExists {

	public static void main(String[] args) {

		Map<String, String> envVars = System.getenv();

		if (!envVars.containsKey("VNC_HOME")) {

			try (Scanner keyboardInput = new Scanner(System.in)) {
				System.out.printf("Insira o caminho do .exe do UltraVNC Viewer => ");
				String vncHomeDir = keyboardInput.nextLine();

				try {
					Runtime.getRuntime().exec("setx VNC_HOME " + vncHomeDir);
					System.out.println("Local de instalação do VNC definido com sucesso.");
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		System.out.println("Local de instalação do UltraVNC Viewer já definido");
		return;

	}

}
