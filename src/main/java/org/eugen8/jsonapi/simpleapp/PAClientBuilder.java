package org.eugen8.jsonapi.simpleapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.client.spi.ConnectorProvider;

public class PAClientBuilder {

	private int proxyPort = 80;
	private String proxyHost = null;
	private boolean initialized = false;

	public void getProxyInfo() {
		// use proxy info from file if exists
		if (!new File(App.PROXY_CONF_FILE).exists()) {
			initialized = true;
			return;
		}
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(App.PROXY_CONF_FILE)) {
			prop.load(input);
		} catch (IOException e) {
			System.err.println("Could not read proxy configuration file " + App.PROXY_CONF_FILE);
		}
		proxyHost = prop.getProperty(App.PROXY_HOST) != null ? prop.getProperty(App.PROXY_HOST) : null;
		proxyPort = (prop.getProperty(App.PROXY_PORT) != null || !prop.getProperty(App.PROXY_PORT).isEmpty())
				? Integer.parseInt(prop.getProperty(App.PROXY_PORT))
				: null;
		initialized = true;
	}
	
	public Client getClientInstance() {
		if(!initialized ) {
			getProxyInfo();
		}
		return ClientBuilder.newClient(new ClientConfig().connectorProvider(new ConnectorProvider() {
			//figured this out from digging through jersey source code
			@Override
			public Connector getConnector(Client client, Configuration runtimeConfig) {
				HttpUrlConnectorProvider customConnProv =  new HttpUrlConnectorProvider();
				customConnProv.connectionFactory(new ConnectionFactory() {
					
					@Override
					public HttpURLConnection getConnection(java.net.URL url) throws IOException {
						if(proxyHost!=null && !proxyHost.isEmpty()) {
							Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
							return (HttpURLConnection)url.openConnection(proxy);
						}else {
							return (HttpURLConnection)url.openConnection();
						}
					}
				});
				return customConnProv.getConnector(client, runtimeConfig);
			}
		}));
	}

}
