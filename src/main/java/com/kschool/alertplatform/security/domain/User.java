package com.kschool.alertplatform.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Authentication {

	@JsonProperty(value = "clientId")
	private String clientId;

	@JsonProperty(value = "mobileNumber")
	private String mobileNumber;

	@JsonProperty(value = "customerName")
	private String customerName;

	@JsonProperty(value = "customerFirstSurname")
	private String customerFirstSurname;

	@JsonProperty(value = "customerSecondSurname")
	private String customerSecondSurname;

	@Override
	public String getName() {
		return customerFirstSurname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

	@Override
	public Object getCredentials() { return null; }

	@Override
	public Object getDetails() { return null; }

	@Override
	public Object getPrincipal() { return this; }

	@Override
	public boolean isAuthenticated() { return true; }

	@Override
	public void setAuthenticated(boolean b) throws IllegalArgumentException { }
}
