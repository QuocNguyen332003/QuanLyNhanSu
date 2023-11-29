package Model;
import java.io.Serializable;
import java.sql.Date;
public class nhanvien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matk;
    private String mapb;
    private String macn;
    private Date ngaybatdau;
    private String tinhtrang;
    private String congviec;
    public String getMatk() { return matk; }
    public void setMatk(String matk) {this.matk=matk; }
    public String getMapb() { return mapb; }
    public void setMapb(String mapb) { this.mapb=mapb; }
    public String getMacn() { return macn; }
    public void setMacn(String macn) { this.macn=macn; }
    public Date getNgaybatdau() { return ngaybatdau; }
    public void setNgaybatdau() { this.ngaybatdau=ngaybatdau; }
    public String getTinhtrang() { return tinhtrang; }
    public void setTinhtrang(String tinhtrang) { this.tinhtrang=tinhtrang; }
    public String getCongviec() { return congviec; }
    public void setCongviec(String congviec) { this.congviec=congviec; }

    public nhanvien(String matk, String macn, String mapb, Date ngaybatdau, String tinhtrang, String congviec){
        this.matk=matk;
        this.macn=macn;
        this.mapb=mapb;
        this.ngaybatdau=ngaybatdau;
        this.tinhtrang=tinhtrang;
        this.congviec=congviec;
    }
}
