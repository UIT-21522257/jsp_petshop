package com.demo.helpers;

import javax.servlet.http.HttpServletRequest;

import com.ip2location.IPResult;

public class ConfigIP {
	public static String clientPublicIP = IPAddressUtil.getPublicIPAddress();
	public static IPResult ipconfig(HttpServletRequest request) {
		IP2LocationService ip2LocationService;
		String dbPath = request.getServletContext().getRealPath("/WEB-INF/IP2LOCATION-LITE-DB11.BIN");
        ip2LocationService = new IP2LocationService(dbPath);
		String clientPublicIP = IPAddressUtil.getPublicIPAddress();
		IPResult result = ip2LocationService.lookup(clientPublicIP);
		return result;
	}
	public static void main(String[] args) {
		
	}
}

