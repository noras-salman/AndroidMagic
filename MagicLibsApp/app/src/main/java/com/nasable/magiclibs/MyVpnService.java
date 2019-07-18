package com.nasable.magiclibs;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.text.format.Formatter;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyVpnService extends VpnService {
    public static String CONNECTION = "StartService";
    public static String DISCONNECTION = "StopService";
    public static String TAG = "VPNserviceDebug";
    public static boolean work = false;
    private Thread mThread;
    private ParcelFileDescriptor mInterface;
    VpnService.Builder builder = new VpnService.Builder();

    public String getIP(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.d("getIP", ip);
        return ip;
    }

    // Services interface
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start a new session by creating a new thread.
        if (intent != null && DISCONNECTION.equals(intent.getAction())) {
            work = false;
            stopForeground(true);
            mThread.interrupt();
            stopSelf();

            return START_NOT_STICKY;
        }
        work = true;
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String ip = getIP(getApplicationContext());
                    Log.d("VPNservice", "0");
                    Log.d("VPNservice", ip);
                    builder.setSession("MyVPNService")
                            .addAddress("10.0.2.0" , 32)
                            .addDnsServer("8.8.8.8")
                            .addRoute("0.0.0.0", 0).establish();
                    mInterface = builder.establish();

                    FileInputStream in = new FileInputStream(
                            mInterface.getFileDescriptor());
                    FileOutputStream out = new FileOutputStream(
                            mInterface.getFileDescriptor());
                    Log.d("VPNservice", "1 " + (mInterface != null));
                    DatagramChannel tunnel = DatagramChannel.open();
                    Log.d("VPNservice", "2 " + (tunnel != null));
               //     Log.d("VPNservice", "3 " + tunnel.connect(new InetSocketAddress("127.0.0.1", 8087)));
                    //tunnel.connect(new InetSocketAddress("127.0.0.1", 8087));
                    tunnel.configureBlocking(false);
                    protect(tunnel.socket());
                    //e. Use a loop to pass packets.
                    ByteBuffer packet = ByteBuffer.allocate(Short.MAX_VALUE);
                    Log.d("VPNservice", "4");

                    while (!Thread.interrupted()) {
                        Log.d("VPNservice", "5");
                        // read out going

                        int length = in.read(packet.array());
                        if (length > 0) {
                            packet.limit(length);
                            tunnel.write(packet);
                            // There might be more outgoing packets.
                            Log.d("VPNservice", "6");
                        }
                        Log.d("VPNservice", "7");

                        // read from tunnel
                        length = tunnel.read(packet);

                        try {
                            debugPacket(packet);
                        }catch (Exception e){}


                        Log.d("VPNservice", "8 "+length);
                        if (length > 0) {
                            if (packet.get(0) != 0) {
                                out.write(packet.array(), 0, length);
                                Log.d("VPNservice", "9");
                            }
                            packet.clear();
                            Log.d("VPNservice", "10");
                        }

                        Log.d("VPNservice", "11");
                        Thread.sleep(100);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (mInterface != null) {
                            mInterface.close();
                            mInterface = null;
                        }
                    } catch (Exception e) {

                    }
                }
            }

        }, "MyVpnRunnable");

        //start the service
        mThread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (mThread != null) {
            mThread.interrupt();
        }
        super.onDestroy();
    }

    private void debugPacket(ByteBuffer packet)
    {
        /*
        for(int i = 0; i < length; ++i)
        {
            byte buffer = packet.get();

            Log.d(TAG, "byte:"+buffer);
        }*/



        int buffer = packet.get();
        int version;
        int headerlength;
        version = buffer >> 4;
        headerlength = buffer & 0x0F;
        headerlength *= 4;
        Log.d(TAG, "IP Version:"+version);
        Log.d(TAG, "Header Length:"+headerlength);

        String status = "";
        status += "Header Length:"+headerlength;

        buffer = packet.get();      //DSCP + EN
        buffer = packet.getChar();  //Total Length

        Log.d(TAG, "Total Length:"+buffer);

        buffer = packet.getChar();  //Identification
        buffer = packet.getChar();  //Flags + Fragment Offset
        buffer = packet.get();      //Time to Live
        buffer = packet.get();      //Protocol

        Log.d(TAG, "Protocol:"+buffer);

        status += "  Protocol:"+buffer;

        buffer = packet.getChar();  //Header checksum

        String sourceIP  = "";
        buffer = packet.get();  //Source IP 1st Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 2nd Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 3rd Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 4th Octet
        sourceIP += buffer;

        Log.d(TAG, "Source IP:"+sourceIP);

        status += "   Source IP:"+sourceIP;

        String destIP  = "";
        buffer = packet.get();  //Destination IP 1st Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 2nd Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 3rd Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 4th Octet
        destIP += buffer;

        Log.d(TAG, "Destination IP:"+destIP);

        status += "   Destination IP:"+destIP;
        /*
        msgObj = mHandler.obtainMessage();
        msgObj.obj = status;
        mHandler.sendMessage(msgObj);
        */

        //Log.d(TAG, "version:"+packet.getInt());
        //Log.d(TAG, "version:"+packet.getInt());
        //Log.d(TAG, "version:"+packet.getInt());

    }
}
