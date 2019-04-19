package com.mingxiangchen.droidnettestclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class DroidNetSocketFactory extends SocketFactory {
    private SocketFactory systemFactory = SocketFactory.getDefault();
    private InetAddress localAddress;
    private int localPort;

    public DroidNetSocketFactory(InetAddress localAddress, int localPort) {
        this.localAddress = localAddress;
        this.localPort = localPort;
    }

    @Override
    public Socket createSocket() throws IOException {
        Socket socket = systemFactory.createSocket();
        socket.bind(new InetSocketAddress(localAddress, localPort));
        return socket;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return systemFactory.createSocket(host, port);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return systemFactory.createSocket(host, port, localHost, localPort);
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return systemFactory.createSocket(host, port);
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return systemFactory.createSocket(address, port, localAddress, localPort);
    }
}
