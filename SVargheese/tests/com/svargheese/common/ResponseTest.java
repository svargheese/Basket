package com.svargheese.common;

import org.junit.Assert;
import org.junit.Test;

public class ResponseTest {

	@Test
	public void testResponse() {
		Response r = new Response("", false);
		Assert.assertNotNull(r);
		Assert.assertTrue(r.message.length() == 0);
		Assert.assertFalse(r.isFailure);

		r = new Response(null, true);
		Assert.assertNotNull(r);
		Assert.assertNull(r.message);
		Assert.assertTrue(r.isFailure);

		r = new Response("Something", true);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.message, "Something");
		Assert.assertTrue(r.isFailure);
	}

	@Test
	public void testToString() {
		Assert.assertNotNull(new Response(null, true).toString());
	}
}
