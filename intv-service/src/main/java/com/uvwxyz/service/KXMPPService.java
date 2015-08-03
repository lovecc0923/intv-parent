package com.uvwxyz.service;

import java.util.List;

import org.jivesoftware.smack.XMPPConnection;

public interface KXMPPService {

	XMPPConnection getConnection() throws Exception;

	void push(List<Object> userIdList, String body);

	void push(Object userId, String body);

	void register(String username, String password) throws Exception;
}
