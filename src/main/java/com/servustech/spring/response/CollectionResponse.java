/**
 *
 */
package com.servustech.spring.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Andrei Groza
 *
 */
public class CollectionResponse<T> {
	private int itemsCount;
	private Collection<T> items;
	private List<String> headers;

	/**
	 * Default constructor
	 */
	public CollectionResponse() {
		this(new ArrayList<T>(), new ArrayList<String>());
	}

	/**
	 * Parameterized constructor
	 *
	 * @param items
	 */
	public CollectionResponse(final Collection<T> items) {
		this(items, new ArrayList<String>());
	}
	
	/**
	 * Parameterized constructor
	 *
	 * @param items
	 */
	public CollectionResponse(final Collection<T> items,List<String> headers) {
		this.items = items;
		this.itemsCount = items.size();
		this.headers=headers;
	}

	/**
	 * @return the itemsCount
	 */
	public int getItemsCount() {
		return itemsCount;
	}

	/**
	 * @param itemsCount
	 *            the itemsCount to set
	 */
	public void setItemsCount(
			final int itemsCount) {
		this.itemsCount = itemsCount;
	}

	/**
	 * @return the items
	 */
	public Collection<T> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(
			final Collection<T> items) {
		this.items = items;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

}
