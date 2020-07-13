package com.example.appartamentofacile;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "user_saved")
    public class User {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "user_id")
        private int id;

        @ColumnInfo(name = "username")
        private String username;

        @ColumnInfo(name = "password")
        private Integer password;

    public User(){}

        public User(@NonNull String word, @NonNull String password) {
            this.username = word;
            this.password = password.hashCode();
        }



        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public void setPassword(Integer password) {
            this.password = password;
        }

        public Integer getPassword() {
            return password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
