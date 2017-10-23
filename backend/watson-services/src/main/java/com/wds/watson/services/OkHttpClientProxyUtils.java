package com.wds.watson.services;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import com.wds.watson.configuration.CustomProxyConfiguration;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

class OkHttpClientProxyUtils {

	public static Authenticator createAuthentification(CustomProxyConfiguration c){
		Authenticator proxyAuthenticator = new Authenticator() {
			@Override
			public Request authenticate(Route route, Response response) throws IOException {
				String credential = Credentials.basic(c.getUserName(), c.getPassword());
				return response.request().newBuilder().header("Proxy-Authorization", credential).build();
			}
		};
		return proxyAuthenticator;
	}
	
	public static Proxy createProxy(CustomProxyConfiguration c){
		return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(c.getProxyHost(), c.getProxyPort()));
	}

}
