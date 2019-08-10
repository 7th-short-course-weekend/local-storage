package com.rathana.localstoragedemo.data;

public class Database {

    private static Database database;
    private Database(){};
    public static Database getInstance(){
        if(database==null)
            database = new Database();
        return database;
    }

    private static InMemoryUserRepository userRepo;
    public static InMemoryUserRepository getUserRepo(){
        if(userRepo==null)
            userRepo = new InMemoryUserRepository();

        return userRepo;
    }
}
