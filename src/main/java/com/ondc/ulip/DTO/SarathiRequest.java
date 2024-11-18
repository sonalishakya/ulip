package com.ondc.ulip.DTO;

public class SarathiRequest {
    private String dlnumber;
    private String dob;

    public SarathiRequest(String dlnumber, String dob) {
        this.dlnumber = dlnumber;
        this.dob = dob;
    }

    public String getDlnumber() {
        return dlnumber;
    }

    public void setDlnumber(String dlnumber) {
        this.dlnumber = dlnumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "SarathiRequest{" +
                "dlnumber='" + dlnumber + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
