package br.com.bruno.main;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.metadata.Metadata;

import br.com.bruno.utils.FileUtils;
import br.com.bruno.utils.Utils;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);
	
	private static String diretorio;
	private static boolean renomear = false;
	private static boolean prefixoNumerico = false;
	
	public static void main(String args[]) throws Exception {
		LOG.info("Inicio...");
		LOG.info("Fim...");
		
		if (args.length != 1) {
			LOG.error("Informe o caminho para o arquivo de propriedades!");
			System.exit(-1);
		}
		
		App.lerProperties(args[0]);
		
		File[] files = FileUtils.getFilesListInDirectory(diretorio);
		if (files == null || files.length == 0) {
			LOG.info("Diretorio Vazio");
		}
		int index = 1;
		for (File file : files) {
			App.renomear(file, index);
			index++;
		}
	}
	
	private static void renomear(File file, int index) throws Exception {
		if (renomear) {
			StringBuilder novoNome = new StringBuilder();
			
			if (prefixoNumerico) {
				int lengthIndex = String.valueOf(index).length();
				int qtdZeros = 3 - lengthIndex;
				for (int i = 0; i < qtdZeros; i++) {
					novoNome.append("0");
				}
				novoNome.append(String.valueOf(index));
				novoNome.append(". ");
			}
			
			Metadata metadata = FileUtils.getMetadata(file);
			novoNome.append(metadata.get("title"));
			novoNome.append(".");
			novoNome.append(App.getExtensao(file));
			
			file.renameTo( new File(diretorio, novoNome.toString()) );
			LOG.info(file.getName() + " --> " + novoNome.toString());
		}
	}

	private static void lerProperties(String path) {
		try {
			Properties prop = Utils.getPropertiesByFullPath(path);
			diretorio = prop.getProperty("diretorio");
			renomear = Utils.getBooleanProperties(prop, "renomear");
			prefixoNumerico = Utils.getBooleanProperties(prop, "prefix_numerico");
		}
		catch (IOException e) {
			LOG.error("Erro ao ler arquivo properties", e);
		}
	}
	
	private static String getExtensao(File file) {
		String[] nomes = file.getName().split("\\.");
		return nomes[nomes.length-1];
	}
}
