package com.uvwxyz.service.impl;

import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.uvwxyz.service.KXMPPService;

@Service
@ConfigurationProperties(prefix = "xmpp")
public class KXMPPServiceImpl implements KXMPPService, ConnectionListener {

	private ConnectionConfiguration config;
	private XMPPConnection connection;
	private String from;
	private String host;
	private String password;
	private int port;
	private String username;

	@Override
	public XMPPConnection getConnection() throws Exception {
		// 初始化连接配置
		if (null == config) {
			config = new ConnectionConfiguration(host, port);
			config.setSASLAuthenticationEnabled(false);
			config.setDebuggerEnabled(false);
			config.setSecurityMode(SecurityMode.disabled);
		}
		// 初始化连接
		if (null == connection || !connection.isConnected()) {
			connection = new XMPPConnection(config);
			connection.connect();
			connection.login(username, password);
			connection.addConnectionListener(this);

			from = username + "@" + connection.getServiceName();
		}
		return connection;
	}

	@Override
	public void push(List<Object> userIdList, String body) {
		for (Object userId : userIdList) {
			push(userId, body);
		}
	}

	@Override
	public void push(Object userId, String body) {
		try {
			XMPPConnection con = getConnection();
			Message message = new Message();
			message.setFrom(from);
			message.setTo(userId + "@" + con.getServiceName());
			message.setBody(body);
			message.setType(Type.chat);
			con.sendPacket(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void register(String username, String password) throws Exception {
		XMPPConnection con = new XMPPConnection(config);
		con.connect();
		con.getAccountManager().createAccount(username, password);
		con.disconnect();
	}

	@Override
	public void connectionClosed() {
		this.connection = null;
	}

	@Override
	public void connectionClosedOnError(Exception e) {
		this.connection = null;
	}

	@Override
	public void reconnectingIn(int seconds) {
		this.connection = null;
	}

	@Override
	public void reconnectionSuccessful() {
		this.connection = null;
	}

	@Override
	public void reconnectionFailed(Exception e) {
		this.connection = null;
	}

	public String getHost() {
		return host;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
