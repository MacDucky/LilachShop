package org.lilachshop.server;

import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.Customer;
import org.lilachshop.entities.ExampleEntity;
import org.lilachshop.entities.ExampleEnum;
import org.lilachshop.entities.Item;
import org.lilachshop.server.ocsf.AbstractServer;
import org.lilachshop.server.ocsf.ConnectionToClient;
import org.lilachshop.requests.*;

import java.util.List;

import java.util.LinkedList;
import java.util.List;

public class LilachServer extends AbstractServer {
    private static EntityFactory entityFactory;

    public LilachServer(Integer... port) {
        // default is 3000, otherwise needs to be specified.
        super(port.length > 0 ? port[0] : 3000);
        assert port.length < 2 : "Server should receive only a port.";
        try {
            entityFactory = EntityFactory.getEntityFactory();
        } catch (Exception e) {
            System.out.println("Unable to setup EntityFactory.");
            throw e;
        }
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        // todo: switch to a request class classifying.
        if (msg == null) {
            try {
                client.sendToClient("Exception: Message was null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (msg.getClass().equals(UserComplaintRequest.class)) {
            UserComplaintRequest request = (UserComplaintRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "post new complaint" -> {
                        System.out.println("posting new complaint:");
                        Complaint complaint = request.getComplaint();
                        entityFactory.createComplaint(complaint);
//                        System.out.println(complaint.getContent());
                    }
                }
            } catch (Exception e) {

            }
        } else if (msg.getClass().equals(SupportComplaintRequest.class)) {
            SupportComplaintRequest request = (SupportComplaintRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "get all complaints" -> {
                        List<Complaint> complaints = entityFactory.getAllComplaints();
                        client.sendToClient(complaints);
                    }
                    case "reply to customer complaint" -> {
                        Complaint complaint = request.getComplaint();
                        complaint.setStatus("סגור");
                        entityFactory.createComplaint(complaint);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.getClass().equals(EntityDebugRequest.class)) {
            EntityDebugRequest request = (EntityDebugRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "add customer" -> {
                        Customer customer = request.getCustomer();
                        entityFactory.addCustomer(customer);
                        client.sendToClient("ok");
                    }

                    default -> client.sendToClient("not ok");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // debug request
        else if (msg.getClass().equals(DebugRequest.class)) {
            DebugRequest request = (DebugRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "example message" -> {
                        System.out.println("Server: Received a 'example message' from client.");
                        System.out.println("Server: Sending a reply!");
                        client.sendToClient("This is a reply from LilachServer!");
                        System.out.println("Server: Message sent to client.");
                    }
                    case "write entity" -> {
                        System.out.println("Server: Writing new example entity!");
                        ExampleEntity exampleEntity = new ExampleEntity(ExampleEnum.TYPE1);
                        entityFactory.createExampleEntity(exampleEntity);
                        client.sendToClient("This is a reply from LilachServer!");
                    }
                    case "get all entities" -> client.sendToClient(entityFactory.getAllExampleEntities());

                    case "update entity1" -> {
                        int id_key = request.getIDToUpdate();
                        ExampleEnum exampleEnumToUpdate = request.getUpdateToEnum();
                        entityFactory.updateExampleEntityEnumByID(id_key, exampleEnumToUpdate);
                        client.sendToClient("This is a reply from LilachServer!");
                    }

                    case "write catalog" -> {
                        entityFactory.createCatalog();
                        client.sendToClient("Catalog is created!");
                    }

                    case "get all items" -> {
                        entityFactory.getAllItems();
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed sending reply to client.");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        System.out.println(client.toString() + "connected.");
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        System.out.println("Client disconnected.");
    }
}
