package models;

import configuration.ReadProperties;

public class UserBuilder {
    private String name;
    private String email;
    private String psw;

    public UserBuilder(){
        this.email = ReadProperties.username();
        this.psw = ReadProperties.password();
    }

    public static class Builder{
        private UserBuilder newUser;

        public Builder(){
            newUser = new UserBuilder();
        }

        public Builder withEmail(String email){
            newUser.email = email;
            return this;
        }

        public Builder withName(String name){
            newUser.name = name;
            return this;
        }

        public Builder withPsw(String psw){
            newUser.psw = psw;
            return this;
        }

        public UserBuilder build(){
            return newUser;
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
    }
}
