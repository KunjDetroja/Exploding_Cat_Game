package Card_Game;

class Login {
    String User_name;
    String Password;

    Login(String User_name, String Password) {
        this.User_name = User_name;
        this.Password = Password;
    }

    public boolean IsValid() {
        for (int i = 0; i < Game.UserDetails.size(); i++) {
            if (Game.UserDetails.get(i).User_Name.equals(User_name)
                    && Game.UserDetails.get(i).Password.equals(Password)) {
                return true;
            }
        }
        return false;
    }
}

