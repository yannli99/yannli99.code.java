package com.example.learning.demo.java.core.keyword;

import java.io.*;

/**
 * @author liyan
 */
public class TransientDemo {
    static class User implements Serializable {
        private String username;
        private transient String password; // 不会被序列化

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{username='" + username + "', password='" + password + "'}";
        }

        public static void main(String[] args) {
            User user = new User("admin", "secret");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            try (ObjectInputStream ois = new ObjectInputStream(in)) {
                User deserializedUser = (User) ois.readObject();
                System.out.println(deserializedUser); // 输出: User{username='admin', password='null'}
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
