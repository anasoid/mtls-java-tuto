package org.anasoid.mtls.tuto;/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @author : anasoid
 * Date :   4/18/24
 */

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.Socket;

public class SSLSocketEchoServer {

    static void startServer(int port) throws IOException {
        System.out.println("Starting Server");
        System.getProperties().entrySet().stream().filter(s -> s.getKey().toString().contains("javax.net.ssl")).forEach((k) -> System.out.println(k));
        System.out.println("######################");

        ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
        try (SSLServerSocket listener = (SSLServerSocket) factory.createServerSocket(port)) {
            listener.setNeedClientAuth(true);
            listener.setEnabledCipherSuites(new String[]{"TLS_AES_128_GCM_SHA256"});
            listener.setEnabledProtocols(new String[]{"TLSv1.3"});
            System.out.println("listening for messages...");
            try (Socket socket = listener.accept()) {

                InputStream is = new BufferedInputStream(socket.getInputStream());
                byte[] data = new byte[2048];
                int len = is.read(data);

                String message = new String(data, 0, len);
                OutputStream os = new BufferedOutputStream(socket.getOutputStream());
                System.out.printf("server received %d bytes: %s%n", len, message);
                String response = message + " processed by server";
                os.write(response.getBytes(), 0, response.getBytes().length);
                os.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        startServer(8443);
    }
}
