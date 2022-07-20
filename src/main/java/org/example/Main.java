package org.example;

import data.Car;
import data.HiberSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in, "UTF-8");
        Session session = HiberSession.getSessionFactory().openSession();

        Car car = new Car();

        // FIRST CAR
        car.setId(3);
        car.setName("Nissan");
        car.setModel("Skyline 1995");

        // CRUD Example

        //create(car, session);
        //update(3,"Nissan","Skyline 2003",session);
        //read(session);
        //delete(1, session);


        session.close();
    }

    public static void create(Car car, Session session) {
        car.setName(car.getName());
        car.setModel(car.getModel());
        System.out.println("Car - " + car.getName() + " " + car.getModel() + ", was successfully created!");
        session.save(car);
    }

    public static void delete(int carId, Session session) {
        Transaction tx = null;
        tx = session.beginTransaction();

        Car car = session.get(Car.class,carId);
        session.remove(car);

        tx.commit();
        System.out.println("Successfully deleted car: " + car.getName() + " " + car.getModel());
    }

    public static void update(int carId,String name,String model, Session session){
        Transaction tx = null;
        tx = session.beginTransaction();

        Car car = session.get(Car.class,carId);
        car.setName(name);
        car.setModel(model);
        session.update(car);

        tx.commit();

        System.out.println("Successfully updated car: " + car.getName() + " " + car.getModel());
    }


    public static void read(Session session) {
        Query query = session.createQuery("FROM Car");
        List<Car> roles = query.list();
        for (Car c : roles) {
            System.out.println("-------------------------------");
            System.out.println("| " + c.getId() + " | " + c.getName() + " - " + c.getModel() + "   |");
            System.out.println("-------------------------------");
        }
    }
}
