package br.com.bruno.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class FileUtils {
	private static final Logger LOG = LogManager.getLogger(FileUtils.class);
	
	public static File[] getFilesListInDirectory(String inputDir) {
		File dir = new File(inputDir);
		return dir.listFiles();
	}
	
	public static Metadata getMetadata(File file) throws Exception {
		try (InputStream input = new FileInputStream(file);) {
	        ContentHandler handler = new DefaultHandler();
	        Metadata metadata = new Metadata();
	        Parser parser = new Mp3Parser();
	        ParseContext parseCtx = new ParseContext();
	        parser.parse(input, handler, metadata, parseCtx);
	        
	        return metadata;
	        
		} catch (IOException | SAXException | TikaException e) {
			LOG.info("Erro getMetadata");
			throw e;
		}
	}
	
}
