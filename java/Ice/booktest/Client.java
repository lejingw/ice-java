import Ice.Communicator;
import Ice.ObjectPrx;
import Ice.Util;
import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;

/**
 * Created by lejing on 16/2/26.
 */
public class Client {
	public static void main(String[] args) throws InterruptedException {
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
		int count = 100;
//		int count = 500000;
		for (int i = 0; i < count; i++) {
			onlineBookPrx.bookTick(msg);
		}
		System.out.println("tps " + count * 1000 / (System.currentTimeMillis() - start));
		onlineBookPrx.shutdown();

		Thread.sleep(3*1000);
		ic.destroy();
	}
}
