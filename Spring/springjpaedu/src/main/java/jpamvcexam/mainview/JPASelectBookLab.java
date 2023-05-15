package jpamvcexam.mainview;

import jpamvcexam.model.vo.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JPASelectBookLab {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emptest");
        EntityManager em = factory.createEntityManager();
        TypedQuery q = null;
        Query query = null;
        List<Book> bookList;
        List<Object[]> list;
        Random random = new Random();
        boolean isNext;
        int num;

        while(true) {
            isNext = true;
            System.out.println("1. 모두 출력하기");
            System.out.println("2. 가격이 높은 순으로 출력하기");
            System.out.println("3. 20000원 이상의 도서들만 출력하기");
            System.out.println("4. id가 3번인 도서 출력하기");
            System.out.println("5. 도서명에 '자바' 또는 '스프링'를 포함하는 도서들만 출력하기");
            System.out.println("6. 분류별 도서 가격의 합을 출력하기");
            System.out.println("7. 종료\n");
            System.out.println("원하는 메뉴의 번호를 선택 : ");
            num = scan.nextInt();

            if(num == 1) {
                q = em.createQuery("select b from Book b", Book.class);
            } else if(num == 2) {
                q = em.createQuery("select b from Book b order by b.price desc", Book.class);
            } else if(num == 3) {
                q = em.createQuery("select b from Book b where b.price >= 20000", Book.class);
            } else if(num == 4) {
                Book b = em.find(Book.class, 3);
                if (b != null) {
                    System.out.println(b);
                } else {
                    System.out.println("id가 3번인 도서는 없습니다.");
                }
                isNext = false;
            } else if(num == 5) {
                q = em.createQuery("select b from Book b where b.title like :title", Book.class);
                if(random.nextBoolean()) {
                    q.setParameter("title", "%자바%");
                } else {
                    q.setParameter("title", "%스프링%");
                }
                if(q.getResultList().size() == 0) {
                    System.out.println("해당하는 도서가 없습니다.");
                    isNext = false;
                }
            } else if(num == 6) {
                query = em.createQuery("select b.kind, sum(b.price) from Book b group by b.kind");
                list = query.getResultList();
                for(Object[] vo : list) {
                    System.out.println("분류 코드 " + vo[0] + " " + vo[1]);
                }
                isNext = false;
            } else {
                scan.close();
                em.close();
                factory.close();
                break;
            }

            if(isNext) {
                bookList = q.getResultList();
                for(Object vo : bookList) {
                    System.out.println(vo);
                }
            }
        }
    }
}
