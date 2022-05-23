package org.lilachshop.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lilachshop.entities.*;
import org.lilachshop.panels.ocsf.AbstractClient;
import org.lilachshop.requests.EntityDebugRequest;
import org.lilachshop.server.LilachServer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntitiesTest {

    static private class TempClient extends AbstractClient {
        private String result = null;

        public TempClient() {
            super("localhost", 3000);
            System.out.println("Client: Client setup complete.");
        }

        @Override
        protected void handleMessageFromServer(Object msg) {
            result = "ok";
        }

        public String getResult() {
            if (result == null)
                throw new RuntimeException("Server failed to respond");
            return result;
        }
    }

    static private class ServerBooter implements Runnable {

        public ServerBooter() {
            super();
        }

        @Override
        public void run() {
            System.out.println("Server: Setting up server...");
            LilachServer lilachServer = new LilachServer(3000);
            System.out.println("Server: Done.");
            try {
                System.out.println("Server: Listening!");
                lilachServer.listen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeAll
    static void runServer() {
        Assertions.assertDoesNotThrow(() -> {
            Thread thread = new Thread(new ServerBooter(), "Server");
            thread.start(); // run server
            Thread.sleep(5000); // Hibernate setup is long...
        });
    }

    @Test
    public void shouldSendRequestAndBeAnswered() throws IOException, InterruptedException {
        TempClient tempClient = new TempClient();
        System.out.println("Client: Connecting to server...");
        tempClient.openConnection();
        System.out.println("Client: Connected to server.");
        System.out.println("Client: Sending request to server...");
        AccountType accountType = AccountType.CHAIN;
        Address address = new Address("Lala Land", "Baker street 221b");
        CreditCard creditCard = new CreditCard("1234-1234-1234-1234",
                LocalDate.of(2028, Month.JANUARY, 1), "Utzliguz Li", 123456789L);
        Store store = new Store("lilach_akko", address);
        Customer customer = new Customer("Utzliguz123", "Li123", "Utzliguz", "Li", accountType, address, creditCard, store);

        tempClient.sendToServer(new EntityDebugRequest("add customer", customer));

        System.out.println("Client: Done.");
        Thread.sleep(1000);
        tempClient.closeConnection();
        assertEquals("ok", tempClient.getResult());
    }

}
