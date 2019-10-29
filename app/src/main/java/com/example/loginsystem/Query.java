package com.example.loginsystem;

public class Query {
    public static final String TABLE_NAME = "users";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME +" (\n" +
            "\tuserId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\temail TEXT(50) NOT NULL,\n" +
            "\t\"password\" TEXT(15) NOT NULL\n" +
            ")";
    public static final String insert_column = "ALTER TABLE users ADD COLUMN email INTEGER DEFAULT0";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String ADD_USER_ =  "INSERT INTO "+TABLE_NAME +"(email, password) VALUES(?, ?)";
}
