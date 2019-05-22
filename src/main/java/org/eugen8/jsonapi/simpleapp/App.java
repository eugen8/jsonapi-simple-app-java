/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.eugen8.jsonapi.simpleapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
	public final static String PROXY_PORT = "proxyPort";
	public final static String PROXY_HOST = "proxyHost";
	public final static String VERSION_NUM="0.1";
	public final static String APP_NAME="Photo-Album";
	public static final String PROXY_CONF_FILE = "proxy.conf";

	public static void main(String[] args) {
		Integer albumId = parseAgs(args);
		if(albumId!=null) {
			RequestRunner runner = RequestRunner.getInstance();
			runner.printAlbumPhotos(albumId);
		}
		
	}

	protected static Integer parseAgs(String[] args) {
		String albumNumberStr = null;
		Integer albumId = null;

		Options options = buildOptions();

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(options, args);
			if(line.hasOption("version")) {
				System.out.println(APP_NAME+" Version: "+VERSION_NUM);
				return null;
			}else if (line.hasOption(PROXY_HOST) || line.hasOption(PROXY_PORT)) {
				String host = line.getOptionValue(PROXY_HOST);
				String port = line.getOptionValue(PROXY_PORT);
				writeProxySettingsToFile(host, port);
			}else if(line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("java -jar photo-album.jar [options] <albumId>", options);
				return null;
			}

			List<String> argsList = line.getArgList();
			if(!argsList.isEmpty()) {
				if(argsList.size()==1) {
					albumNumberStr = argsList.get(0);
				}
			}
		}catch (ParseException e) {
			System.err.println("Parsing failed: "+e.getMessage());

		}

		try {
			if(albumNumberStr!=null) {
				albumId = Integer.parseInt(albumNumberStr);
			}
		}catch (NumberFormatException e) {
			System.err.println("Could not read album number, use photo-app -help for details. "+e.getMessage());
		}


		return albumId;
	}

	private static void writeProxySettingsToFile(String host, String port) {
		try(FileWriter fw = new FileWriter(PROXY_CONF_FILE)){
			fw.write("proxyHost="+host);
			fw.write("proxyPort="+port);
			fw.write("\n");
			fw.close();
			System.out.println("Proxy configuration complete to: "+host+":"+port);
			
		}catch (IOException e) {
			System.err.println("Unable to write configuration file: "+e.getMessage());
		}
	}

	private static Options buildOptions() {
		Option help = new Option("help", "print this help message");
		Option version = new Option("version", "print version information");
		Option proxyHost = Option.builder(PROXY_HOST).hasArg().argName("host").desc("Configure proxy host").build();
		Option  proxyPort= Option.builder(PROXY_PORT).hasArg().argName("port")
				.desc("Configure proxy port. Must be called with -proxyHost in the same line.").build();
		Options options = new Options();
		options.addOption(help).addOption(version).addOption(proxyHost).addOption(proxyPort);
		return options;
	}
}
