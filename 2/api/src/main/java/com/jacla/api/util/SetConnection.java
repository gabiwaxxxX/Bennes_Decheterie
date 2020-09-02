package com.jacla.api.util;

import com.jacla.api.models.Dumpster;

import java.net.*;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

public class SetConnection {
    private List<String> reachable = new ArrayList<String>();
    private static String subNetwork;
    private static String myIp;

    public SetConnection(){
        try{
            Socket socket = new Socket("www.google.fr", 80);
            myIp = socket.getLocalAddress().getHostAddress();
            System.out.println("IP Adress: "+myIp);
            if (myIp.equals("127.0.0.1")) {
                System.out.println("This PC is not connected to any network!");
            }
            else{

                subNetwork = myIp.split("\\.")[0]+"."+myIp.split("\\.")[1]+"."+myIp.split("\\.")[2];
            }
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void findArduinos(){
        getInterfaces();
        System.out.println(reachable);
        for (String element : reachable) {
            try{
                Socket socket = new Socket(element, 6666);
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                out.writeUTF(myIp);
                out.flush();
                out.close();
                socket.close();
            }
            catch(java.io.IOException e){
            }
        }

    }


    private void getInterfaces(){
        for(int i=1;i<=254;++i){
            final int j=i;
            // New thread for parallel execution
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String host=subNetwork + "." + j;
                        // Create an InetAddress object with new IP
                        InetAddress addr=InetAddress.getByName(host);
                        if (addr.isReachable(1000)){ // See if it is reachable or simply available(check time is 1s=1000ms)
                            if (!reachable.contains(host)){
                                reachable.add(host);
                            }
                        }
                    } catch (UnknownHostException e) {

                    } catch (IOException e) {

                    } catch (Exception e) {
                    }
                }
            }).start();
        }
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}