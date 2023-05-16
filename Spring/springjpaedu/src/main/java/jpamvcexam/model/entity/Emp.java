package jpamvcexam.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Emp {
    @Id
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private java.util.Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    private Dept dept;

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getMgr() {
        return mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public int getSal() {
        return sal;
    }

    public int getComm() {
        return comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                ", dept=" + dept +
                '}';
    }
}
