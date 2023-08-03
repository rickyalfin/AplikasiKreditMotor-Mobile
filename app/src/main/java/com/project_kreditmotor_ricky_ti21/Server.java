package com.project_kreditmotor_ricky_ti21;

public class Server {
    //public String urlServer = "10.0.2.2"; // Jika Menggunakan Emulator dari Android Studio
    public String urlServer = "192.168.56.1"; // Jika Menggunakan Emulator Lain Seperti Bluestack atau Nox Player
    //public String urlServer = "rickyalf.epizy.com";
    //public String urlServer = "santosoweb.com";

    public String urlDatabase1() {
        System.out.println("Lokasi Server :" + urlServer);
        return urlServer;
    }
}
