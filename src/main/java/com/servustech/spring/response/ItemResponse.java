/**
 *
 */
package com.servustech.spring.response;

import java.util.List;

/**
 * @author Andrei Groza
 *
 */
public final class ItemResponse<T> {
	@SuppressWarnings("rawtypes")
	public static final ItemResponse	EMPTY	= new ItemResponse<>(null);

	private T							item;
	private List<String> headers;

	/**
	 * Default constructor
	 */
	public ItemResponse() {
	}

	/**
	 * Parameterized constructor
	 *
	 * @param item
	 */
	public ItemResponse(final T item) {
		this.item = item;
	}
	
	/**
	 * Parameterized constructor
	 *
	 * @param item
	 */
	public ItemResponse(final T item,List<String> headers) {
		this.item = item;
		this.headers=headers;
	}

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(
			final T item) {
		this.item = item;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	
	

}
