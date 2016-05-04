/**
 * Created by lejing on 16/2/26.
 */

import Ice.Current;
import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book._OnlineBookDisp;

public class OnlineBookImpl extends _OnlineBookDisp {
	@Override
	public Message bookTick(Message msg, Current __current) {
//		if(true){
//			throw new RuntimeException("---------trace call stack");
//		}
		return msg;
	}

	@Override
	public void shutdown(Current __current) {
		__current.adapter.getCommunicator().shutdown();
	}
}
