package Model;
import java.io.Serializable;
import java.sql.Date;
public class yeucau implements Serializable{
    private static final long serialVersionUID = 1L;
    private String mayeucau;
    private String matk;
    private Date ngaygui;
    private String nguoinhan;
    private String congviec;
    private String mapb;
    public String getMayeucau() { return mayeucau; }
    public void setMayeucau(String mayeucau) { this.mayeucau=mayeucau; }
    public String getMatk() { return matk; }
    public void setMatk(String matk) { this.matk=matk; }
    public Date getNgaygui() { return ngaygui; }
    public void setNgaygui(Date ngaygui) { this.ngaygui=ngaygui; }
    public String getNguoinhan() { return nguoinhan; }
    public void setNguoinhan(String nguoinhan) { this.nguoinhan=nguoinhan; }
    public String getCongviec() { return congviec; }
    public void setCongviec(String congviec) { this.congviec=congviec; }
    public String getMapb() { return mapb; }
    public void setMapb(String mapb) { this.mapb=mapb; }
    public yeucau(String mayeucau, String matk, Date ngaygui, String nguoinhan, String congviec, String mapb){
        this.mayeucau=mayeucau;
        this.matk=matk;
        this.ngaygui=ngaygui;
        this.nguoinhan=nguoinhan;
        this.congviec=congviec;
        this.mapb=mapb;
    }
}
