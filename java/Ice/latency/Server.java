// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

import Demo.Ping;

public class Server extends Ice.Application {
	@Override
	public int
	run(String[] args) {
		if (args.length > 0) {
			System.err.println(appName() + ": too many arguments");
			return 1;
		}

		Ice.ObjectAdapter adapter = communicator().createObjectAdapter("Latency");
		adapter.add(new Ping(), communicator().stringToIdentity("ping"));
		adapter.activate();
		communicator().waitForShutdown();
		return 0;
	}

	public static void
	main(String[] args) {
		Server app = new Server();
		int status = app.main("Server", args, "config.server");
		System.exit(status);
	}
}
