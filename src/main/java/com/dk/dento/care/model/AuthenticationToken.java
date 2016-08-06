package com.dk.dento.care.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AuthenticationToken {

	private String username;
	private Map<String, Boolean> modules;
	private String role;
	private String token;

	public AuthenticationToken() {
	}

	public AuthenticationToken(String username, Map<String, Boolean> modules, String role, String token) {
		this.modules = new ConcurrentHashMap<String, Boolean>(modules);
		this.role = role;
		this.username = username;
		this.token = token;
	}

	public Map<String, Boolean> getModules() {
		return this.modules;
	}

	public String getRole() {
		return role;
	}

	public void setModules(Map<String, Boolean> modules) {
		this.modules = modules;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return this.token;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}