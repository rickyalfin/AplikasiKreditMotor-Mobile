package com.project_kreditmotor_ricky_ti21;

public class Motor extends Koneksi{
    private long id;
    Server server = new Server();

    String SERVER = server.urlDatabase1();
    String URL = "http://" + SERVER +"/jskreditmotor/tbmotor.php";
    String url = "";
    String response = "";

    public String tampilMotor() { try {
        url = URL + "?operasi=view";
        System.out.println("URL Tampil Motor: " + url);
        response = call(url);
    } catch (Exception e) {
    }
        return response;
    }

    public String tampilMotorbyIdNama() { try {
        url = URL + "?operasi=select_by_idnama";
        System.out.println("URL Tampil Kreditor: " + url);
        response = call(url);
    } catch (Exception e) {
    }
        return response;
    }

    public String insertMotor(String kdmotor, String nama, String harga) { nama = nama.replace(" ", "%20");
        try {
            url = URL + "?operasi=insert&kdmotor=" + kdmotor + "&nama=" + nama + "&harga=" + harga;
            System.out.println("URL Insert Motor : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getMotorByKdmotor(int idmotor) {
        try {
        url = URL + "?operasi=get_motor_by_kdmotor&idmotor=" + idmotor;
        System.out.println("URL Get Motor: " + url);
        response = call(url);
    } catch (Exception e) {
    }
        return response;
    }


    public String select_by_KdmotorKredit(String kdmotor) {
        try {
            url = URL + "?operasi=select_by_kdmotorkredit&kdmotor=" + kdmotor;
        System.out.println("URL Get Motor: " + url);
        response = call(url);

        } catch (Exception e) {
        }
        return response;
        }

    public String updateMotor(String idmotor, String kdmotor, String nama, String harga) {
        nama = nama.replace(" ", "%20");
        kdmotor = kdmotor.replace(" ", "%20");
        try {
            url = URL + "?operasi=update&idmotor=" + idmotor + "&kdmotor=" + kdmotor + "&nama=" + nama + "&harga=" + harga;
            System.out.println("URL Update Motor : " + url);
            response = call(url);

        } catch (Exception e) {
        }
            return response;
        }

    public String deleteMotor(int idmotor) {
        try {
            url = URL + "?operasi=delete&idmotor=" + idmotor; System.out.println("URL Hapus Motor : " + url);
            response = call(url);

        } catch (Exception e) {
        }
            return response;
        }

    public long getId() {
    // TODO Auto-generated method stub
        return id;
    //return null;
        }
}
