package com.wds.watson.configuration;

class CustomProxyConfigurationImpl implements CustomProxyConfiguration {
	
	private static final String PROXY_HOST = "my.proxy.host";
	private static final int IPROXY_PORT = 0000;
	private static final String DEFAULT_USER = "proxy-user";
	private static final String DEFAULT_PW = "proxy-pw";

	private String proxyHost;
	private int proxyPort;
	private String userName;
	private String password;
	
	CustomProxyConfigurationImpl(Builder builder){
		this.proxyHost = builder.proxyHost;
		this.proxyPort = builder.proxyPort;
		this.userName = builder.userName;
		this.password = builder.password;
	}
	
	@Override
	public String getProxyHost() {
		return proxyHost;
	}

	@Override
	public int getProxyPort() {
		return proxyPort;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	// Builder
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static final class Builder {
		
		private String proxyHost;
		private int proxyPort;
		private String userName;
		private String password;
		
		private Builder(){}
		
		public Builder withDefaultWdsConfig(){
			this.proxyHost = PROXY_HOST;
			this.proxyPort = IPROXY_PORT;
			this.userName = DEFAULT_USER;
			this.password = DEFAULT_PW;
			return this;
		}
		
		public Builder withHost(String host){
			this.proxyHost = host;
			return this;
		}
		
		public Builder withPort(int port){
			this.proxyPort = port;
			return this;
		}
		
		public Builder withUserName(String userName){
			this.userName = userName;
			return this;
		}
		
		public Builder withPassword(String password){
			this.password = password;
			return this;
		}
		
		public CustomProxyConfigurationImpl build(){
			return new CustomProxyConfigurationImpl(this);
		}
	}
}
