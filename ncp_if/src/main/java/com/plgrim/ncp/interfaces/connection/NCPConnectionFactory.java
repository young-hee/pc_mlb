package com.plgrim.ncp.interfaces.connection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.FactoryBean;

import lombok.Data;

import java.util.List;

@Data
public class NCPConnectionFactory implements FactoryBean<NCPURLConnection>{

	private String url;
	private String connectTimeout;
	private String readTimeout;
	private List<ImmutablePair<String, String>> keyPiar;


	@Override
	public NCPURLConnection getObject() throws Exception {
		NCPURLConnection connection = new NCPURLConnection();

		connection.setRequestUrl(this.url);
		connection.setConnectTimeout(this.connectTimeout);
		connection.setReadTimeout(this.readTimeout);
		connection.setKeyPiar(this.keyPiar);

		return connection;
	}

	@Override
	public Class<NCPURLConnection> getObjectType() {
		return NCPURLConnection.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
