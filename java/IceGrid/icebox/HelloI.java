// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

import Demo._HelloDisp;

public class HelloI extends _HelloDisp {
	HelloI(String serviceName) {
		_serviceName = serviceName;
	}

	@Override
	public void sayHello(Ice.Current current) {
		java.util.Map<String, String> env = System.getenv();
		String lang = env.containsKey("LANG") ? env.get("LANG") : "en";
		String greeting = "Hello, ";
		if (lang.equals("fr")) {
			greeting = "Bonjour, ";
		} else if (lang.equals("de")) {
			greeting = "Hallo, ";
		} else if (lang.equals("es")) {
			greeting = "Hola, ";
		} else if (lang.equals("it")) {
			greeting = "Ciao, ";
		}
		System.out.println(greeting + _serviceName);
	}

	private String _serviceName;
}
