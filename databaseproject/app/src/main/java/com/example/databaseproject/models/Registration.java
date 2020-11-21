package com.example.databaseproject.models;

import android.widget.EditText;

public class Registration {

        String FirstName;
        String LastName;
        String Email;
        String Password;

        public Registration(String FirstName, String LastName, String Email, String Password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
         this.Email = Email;
         this.Password= Password;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }
    }



