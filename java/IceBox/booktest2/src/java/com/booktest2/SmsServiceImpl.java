package com.booktest2;

import Ice.*;
import IceBox.Service;
import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;
import com.hp.tel.ice.message._SmsServiceDisp;

/**
 * Created by lejing on 16/2/26.
 */
public class SmsServiceImpl extends _SmsServiceDisp implements Service {
	private Logger log;
	private ObjectAdapter adapter;

	@Override
	public void sendSms(String msg, Current __current) {
		log.trace("service", "send sms message " + msg);
		System.out.println("send msg:" + msg);
		if(msg.startsWith("hello")){
			ObjectPrx base = adapter.getCommunicator().stringToProxy("OnlineBook");
			OnlineBookPrx onlineBookPrx = OnlineBookPrxHelper.checkedCast(base);

			Message bookMsg = new Message();
			{
				bookMsg.name = "Mr Wang";
				bookMsg.type = 3;
				bookMsg.price = 99.99;
				bookMsg.valid = true;
				bookMsg.content = msg;
			}
			onlineBookPrx.bookTick(bookMsg);
		}
	}

	@Override
	public void start(String name, Communicator communicator, String[] args) {
		this.log = communicator.getLogger().cloneWithPrefix(name);

		adapter = communicator.createObjectAdapter(name);
		adapter.add(this, communicator.stringToIdentity(name));
		adapter.activate();
		log.trace("control", "started");
	}

	@Override
	public void stop() {
		log.trace("control", "stoped");
		adapter.destroy();
	}
}
