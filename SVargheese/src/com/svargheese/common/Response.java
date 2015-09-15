package com.svargheese.common;

/**
 * Re-usable class to represent API response.
 * 
 * @author Sai Vargheese
 *
 */
public final class Response {

	public final String message;
	public final boolean isFailure;

	public Response(String message, boolean isFailure) {
		super();
		this.message = message;
		this.isFailure = isFailure;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", isFailure=" + isFailure
				+ "]";
	}
}