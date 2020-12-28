package br.com.bruno.main;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.metadata.Metadata;

import br.com.bruno.utils.FileUtils;
import br.com.bruno.utils.Utils;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);
	
	public static void main(String args[]) throws Exception {
		LOG.info("Inicio...");
		LOG.info("Fim...");
		
		Properties prop = Utils.getPropertiesByFullPath("D:/git/Java/mp3-metadata-manipulation/src/main/resources/mp3-metadata-manipulation.properties");
		String dir = prop.getProperty("diretorio");
		
		File[] files = FileUtils.getFilesListInDirectory(dir);
		if (files == null || files.length == 0) {
			LOG.info("Diretorio Vazio");
		}
		for (File file : files) {
			Metadata metadata = FileUtils.getMetadata(file);
			LOG.info(file.getName() + " - " + metadata.get("title"));
			
		}
	}
}
