package jpamvcexam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dept {
    @Id
    @Column(name = "DEPTNO")
    private int deptno;
    private String dname;
    private String locCode;

    public int getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public String getLocCode() {
        return locCode;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", locCode='" + locCode + '\'' +
                '}';
    }
}
