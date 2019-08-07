package com.hector.reines;

public class Account {
    private String acc_id;
    private String game_name;
    private String uid;
    private String gen_id;
    private String pass_code;

    public Account(String acc_id, String game_name, String uid, String gen_id, String pass_code) {
        this.acc_id = acc_id;
        this.game_name = game_name;
        this.uid = uid;
        this.gen_id = gen_id;
        this.pass_code = pass_code;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGen_id() {
        return gen_id;
    }

    public void setGen_id(String gen_id) {
        this.gen_id = gen_id;
    }

    public String getPass_code() {
        return pass_code;
    }

    public void setPass_code(String pass_code) {
        this.pass_code = pass_code;
    }
}
