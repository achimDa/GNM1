package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProtectedArea_Has_MapModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("protectedarea","map");
	
private int map;
private int protectedarea;
public int getMap() {
	return map;
}
public void setMap(int map) {
	this.map = map;
}
public int getProtectedarea() {
	return protectedarea;
}
public void setProtectedarea(int protectedarea) {
	this.protectedarea = protectedarea;
}


}
