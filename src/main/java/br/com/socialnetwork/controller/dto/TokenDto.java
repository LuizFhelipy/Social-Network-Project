package br.com.socialnetwork.controller.dto;

public class TokenDto {

	private String token;
	private String type;

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public TokenDto(String token, String type) {
		this.token = token;
		this.type = type;
	}

}
