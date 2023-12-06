package Model;
import java.io.Serializable;
import java.sql.Date;
public class thongtincanhan implements Serializable{
    private static final long serialVersionUID = 1L;
    private String matk;
    private String hoten;
    private Date ngaysinh;
    private String gioitinh;
    private String diachi;
    private String sdt;
    private String email;
    private String bangcap;
    public String getMatk() { return matk; }
    public void setMatk(String matk) { this.matk=matk; }
    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten=hoten; }
    public Date getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(Date ngaysinh) { this.ngaysinh=ngaysinh; }
    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh=gioitinh; }
    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi=diachi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt=sdt; }
    public String getEmail() { return email; }
    public void setEmail(String email) {this.email=email; }
    public String getBangcap() { return bangcap; }
    public void setBangcap(String bangcap) { this.bangcap=bangcap; }
    public thongtincanhan(String matk, String hoten, Date ngaysinh, String gioitinh, String diachi, String sdt, String email, String bangcap){
        this.matk=matk;
        this.hoten=hoten;
        this.ngaysinh=ngaysinh;
        this.gioitinh=gioitinh;
        this.diachi=diachi;
        this.sdt=sdt;
        this.email=email;
        this.bangcap=bangcap;
    }
    public thongtincanhan(){}
}
