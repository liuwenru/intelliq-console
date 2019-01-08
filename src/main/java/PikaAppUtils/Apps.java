package PikaAppUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class Apps {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis=new Jedis(args[0],Integer.parseInt(args[1]));
        jedis.auth(args[2]);
        Subscriber subscriber = new Subscriber();
        String channel = args[3];
        SubscriberThread subscriberThread=new SubscriberThread(jedis,subscriber,channel);
        subscriberThread.start();
    }
}


class Subscriber extends JedisPubSub {
    public Subscriber() {
    }

    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",channel, subscribedChannels));
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d", channel, subscribedChannels));
    }

    @Override
    public void ping() {
        super.ping();
        System.out.println("-------");
    }
}

// 执行订阅线程
class SubscriberThread extends Thread{
    private Jedis jedis=null;
    private  Subscriber subscriber=null;
    private String channel="";
    public SubscriberThread(Jedis jedis,Subscriber subscriber,String channel){
        this.jedis=jedis;
        this.subscriber=subscriber;
        this.channel=channel;
    }
    @Override
    public void run() {
        //subscriber.ping();
        this.jedis.subscribe(subscriber,channel);
    }
}

//Redis 执行检测的线程
class SubscriberPingThread extends Thread {
    private Subscriber subscriber = null;
    private volatile boolean isExit = false;

    public SubscriberPingThread(Subscriber subscriber) {
         this.subscriber = subscriber;
    }
    @Override
    public void run() {
        while (!isExit) {
            try {
                if (subscriber != null) {
                    System.out.println("-------*****--------");
                    subscriber.ping();
                }
                Thread.sleep(5000);
            }
            catch (Exception e) {
                isExit = true;
                e.printStackTrace();
            }
            finally {
                if (isExit) {
                    break;
                }
            }
        }
    }
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }
}