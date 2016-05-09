package com.booktest2;

import Ice.Communicator;
import Ice.ObjectPrx;
import Ice.Util;
import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;
import com.hp.tel.ice.message.SmsServicePrx;
import com.hp.tel.ice.message.SmsServicePrxHelper;

/**
 * Created by lejing on 16/2/26.
 */
public class Client {
	public static void main(String[] args) {
		Communicator ic = Util.initialize(args);
		ObjectPrx proxy = ic.stringToProxy("SmsService:default -p 10001");

		SmsServicePrx prx = SmsServicePrxHelper.checkedCast(proxy);
		if (null == prx)
			throw new Error("Invalid proxy");

		long start = System.currentTimeMillis();
		int count = 1;
		for (int i = 0; i < count; i++) {
			prx.sendSms("hello world!");
		}
		System.out.println("tps " + count * 1000 / (System.currentTimeMillis() - start));
		ic.destroy();
	}


	public static void main2(String[] args) {
		Communicator ic = Util.initialize(args);
		ObjectPrx proxy = ic.stringToProxy("OnlineBook:default -p 10000");

		OnlineBookPrx onlineBookPrx = OnlineBookPrxHelper.checkedCast(proxy);
		if (null == onlineBookPrx)
			throw new Error("Invalid proxy");
		Message msg = new Message();
		{
			msg.name = "Mr Wang";
			msg.type = 3;
			msg.price = 99.99;
			msg.valid = true;
			msg.content = "abcdef";
		}
		long start = System.currentTimeMillis();
		int count = 500;
		for (int i = 0; i < count; i++) {
			onlineBookPrx.bookTick(msg);
		}
		System.out.println("tps " + count * 1000 / (System.currentTimeMillis() - start));
		ic.destroy();
	}
}
