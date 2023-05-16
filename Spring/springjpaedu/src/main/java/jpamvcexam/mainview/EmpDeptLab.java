package jpamvcexam.mainview;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EmpDeptLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emptest");
        EntityManager em = factory.createEntityManager();
        Random random = new Random();
        if(random.nextBoolean()) {
            System.out.print("사원명을 입력하세요 : ");
            String ename = scanner.nextLine();
            TypedQuery<String> q = em.createQuery("select d.dname from Emp e join e.dept d where e.ename = :ename", String.class);
            q.setParameter("ename", ename);
            List<String> dname = q.getResultList();
            if(dname.size() != 0) {
                System.out.println(ename + " 님의 부서명은 " + dname.get(0) + " 입니다...");
            } else {
                System.out.println("부서명을 찾을 수 없네요..ㅜㅜ");
            }
        } else {
            System.out.print("부서명을 입력하세요 : ");
            String dname = scanner.nextLine();
            TypedQuery<String> q = em.createQuery("select e.ename from Emp e join e.dept d where d.dname = :dname", String.class);
            q.setParameter("dname", dname);
            List<String> enameList = q.getResultList();
            if(enameList.size() != 0) {
                System.out.println(dname + " 부서에는 직원들입니다...");
                for(String s : enameList) {
                    System.out.println(s);
                }
            } else {
                System.out.println("직원을 찾을 수 없네요..ㅜㅜ");
            }
        }
        scanner.close();
        em.close();
        factory.close();
    }
}
