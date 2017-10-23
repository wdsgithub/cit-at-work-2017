package com.wds.watson.configuration;

public class CustomProxyConfigProvider {

	public static CustomProxyConfiguration createDefaultWdsProxyConfig() {
		return CustomProxyConfigurationImpl.newBuilder().withDefaultWdsConfig().build();
	}

	public static CustomProxyConfiguration createCustomProxyConfiguration(String host, int port, String user, String pw) {
		return CustomProxyConfigurationImpl.newBuilder().withHost(host).withPort(port).withUserName(user)
				.withPassword(pw).build();
	}

}
