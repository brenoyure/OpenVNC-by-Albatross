package br.albatross.demo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FolderExeTest {

	private static final String VNC_HOME = "VNC_HOME";

	private static final String VNC_EXE = "vncviewer.exe";

	public static void main(String[] args) {

		Map<String, String> env = System.getenv();

		if (!env.containsKey(VNC_HOME)) {
			System.err.println("Variável de Ambiente VNC_HOME não definida ou seu valor está 'null' ou em branco.");
			return;
		}

		String vncHomeDirEnvVarKey = env.get(VNC_HOME);

		File vncHomeDir = new File(vncHomeDirEnvVarKey);

		if (!vncHomeDir.exists()) {
			System.err.println("O Direitório especificado não existe.");
			return;
		}

		if (!vncHomeDir.isDirectory()) {
			System.err.println("O local especificado não é um diretório.");
			return;
		}

		File[] filesInVncHomeDir = vncHomeDir.listFiles();
		fileExists("vncviewer.exe", filesInVncHomeDir);

		try {
			Runtime.getRuntime().exec(vncHomeDir + "\\" + VNC_EXE);
		} catch (IOException e) {
			System.err.println("O Executável '" + VNC_EXE + "' informado não é um aplicativo válido.");
			return;
		}

	}

	private static boolean fileExists(String fileName, File[] directory) {

		for (File file : directory) {
			if (file.getName().equalsIgnoreCase(fileName)) {
				return true;
			}

		}

		System.err.println("Arquivo " + fileName + " não encontrado.");
		return false;
	}

}
