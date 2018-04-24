   /**
    * Class AuthenticationResult stores auth results from WS
    * stores tokens in Strings for further usage.
    */
package entity;

import com.google.gson.annotations.SerializedName;

public class AuthenticationResult {

	public AuthenticationResult(String accessToken, Integer expiresIn, String tokenType, String refreshToken, String idToken) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.tokenType = tokenType;
		this.refreshToken = refreshToken;
		this.idToken = idToken;
	}

	@SerializedName("AccessToken")
	private String accessToken;
	
	@SerializedName("ExpiresIn")
	private Integer expiresIn;
	
	@SerializedName("TokenType")
	private String tokenType;
	
	@SerializedName("RefreshToken")
	private String refreshToken;
	
	@SerializedName("IdToken")
	private String idToken;
	
	//Setters and getters

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
}
