package com.booktest2; /**
 * Created by lejing on 16/2/26.
 */

import Ice.Communicator;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import IceBox.Service;
import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book._OnlineBookDisp;

import java.util.Arrays;

public class OnlineBookImpl extends _OnlineBookDisp implements Service {

	private ObjectAdapter adapter;

	@Override
	public Message bookTick(Message msg, Current __current) {
		System.out.println("book content:" + msg.content);
		return msg;
	}


	private Logger getLogger() { //用来记录服务的日志信息
		return adapter.getCommunicator().getLogger();
	}

	@Override
	public void start(String name, Communicator communicator, String[] args) {
		adapter = communicator.createObjectAdapter(name);
		adapter.add(new OnlineBookImpl(), communicator.stringToIdentity(name));
		adapter.activate();
		getLogger().trace(name, "service started ,with param size " + args.length + " ,detail:"
				+ Arrays.toString(args));
	}

	@Override
	public void stop() {
		adapter.destroy();
	}
}
