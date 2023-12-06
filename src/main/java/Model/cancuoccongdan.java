package Model;

import java.io.Serializable;
import java.sql.Date;

public class cancuoccongdan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matk;
	private String cccd;
	private Date ngaycap;
	private String madc;

	public cancuoccongdan() {

	}

	public cancuoccongdan(String matk, String cccd) {
		this.matk=matk;
		this.cccd=cccd;
	}

	public String getMatk() {
		return matk;
	}
	
	public void setMatk(String matk) {
        this.matk=matk;
    }
	
	public String getCccd() {
		return cccd;
	}
	
	public void setCccd(String cccd) {
		this.cccd=cccd;
	}
	
	public Date getNgaycapp() {
		return ngaycap;
	}
	
	public void setNgaycap(Date ngaycap) {
		this.ngaycap=ngaycap;
	}
	
	public String getMadc() {
		return madc;
	}
	
	public cancuoccongdan(String matk, String cccd, Date ngaycap, String madc) {
		this.matk=matk;
		this.cccd=cccd;
		this.ngaycap=ngaycap;
		this.madc=madc;
	}
}


