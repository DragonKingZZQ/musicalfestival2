package musicalfestival;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisExpireListener implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		byte[] body = message.getBody();
		byte[] channel = message.getChannel();
		String topic = new String(channel);
		String itemValue = new String(body);
		System.out.println(itemValue+"上天让我遇见你！");
	}

}
