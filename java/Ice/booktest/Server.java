/**
 * Created by lejing on 16/2/26.
 */

import Ice.Communicator;
import Ice.ObjectAdapter;
import Ice.Util;

public class Server {
	public static void main(String[] args) {

		Communicator ic = Util.initialize(args);
		ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("", "default -p 10000");

		OnlineBookImpl onlineBook = new OnlineBookImpl();
		adapter.add(onlineBook, Util.stringToIdentity("OnlineBook"));
		adapter.activate();

		System.out.println("server started");
		ic.waitForShutdown();
		ic.destroy();

		System.out.println("00000000000000");
		int status = 0;
		System.exit(status);
	}
}
