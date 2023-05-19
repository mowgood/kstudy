package com.example.springedu2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class FriendTest {
    @Autowired
    FriendRepository friendRepository;

    @Test
    @Transactional
    public void 입력() {
        Friend friend = Friend.builder()
                        .fname("둘리")
                        .fage(10)
                        .build();
        System.out.println(friend.toString());
        friendRepository.save(friend);
    }

    @Test
    public void 추출() {
        List<Friend> list = friendRepository.findAll();
        list.stream().forEach(System.out::println);
    }

    @Test
    @Transactional
    public void 수정() {
        Friend oldEntity = friendRepository.findById(1).get();
        oldEntity.setFname("또치");
        oldEntity.setFage(12);
    }

    @Test
    @Transactional
    public void 삭제() {
        friendRepository.deleteById(1);
    }
}
