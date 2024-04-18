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

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class SSLScocketClient {

    static void startClient(String host, int port) throws IOException {
        System.out.println("Starting client");
        System.getProperties().entrySet().stream().filter(s -> s.getKey().toString().contains("javax.net.ssl")).forEach((k) -> System.out.println(k));
        System.out.println("######################");

        SocketFactory factory = SSLSocketFactory.getDefault();
        try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port)) {

            socket.setEnabledCipherSuites(new String[]{"TLS_AES_128_GCM_SHA256"});
            socket.setEnabledProtocols(new String[]{"TLSv1.3"});

            String message = "Hello World Message";
            System.out.println("sending message: " + message);
            OutputStream os = new BufferedOutputStream(socket.getOutputStream());
            os.write(message.getBytes());
            os.flush();

            InputStream is = new BufferedInputStream(socket.getInputStream());
            byte[] data = new byte[2048];
            int len = is.read(data);
            System.out.printf("client received %d bytes: %s%n", len, new String(data, 0, len));
        }
    }

    public static void main(String[] args) throws IOException {

        startClient("localhost", 8443);
    }
}
