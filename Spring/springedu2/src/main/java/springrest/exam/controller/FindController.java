package springrest.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
public class FindController {
    @Autowired
    FriendRepository friendRepository;

    @GetMapping
    public ResponseEntity list() {
        List<Friend> list = friendRepository.findAll();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @GetMapping("/list2/{id}")
    public ResponseEntity listById(@PathVariable("id") int id) {
        Optional<Friend> friend = friendRepository.findById(id);

        if(!friend.isEmpty()) {
            return new ResponseEntity<>(friend, HttpStatus.OK);
        } else {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("BAD_ID", String.valueOf(id));
            return new ResponseEntity<>(header, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list3/{name}")
    public ResponseEntity listByName(@PathVariable("name") String name) {
        List<Friend> list = friendRepository.findByFname(name);
        if(list.size() != 0) {
                return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            try {
                header.add("BAD_NAME", URLEncoder.encode(name,"utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(header, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    @Transactional
    public ResponseEntity insert(@RequestBody Friend friend) {
        if(friend.getFname() == null || friend.getFage() == null) {
            return new ResponseEntity<>("추가에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            friendRepository.save(friend);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("추가에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity update(@RequestBody Friend friend) {
        if(friend.getId() == 0 || friend.getFname() == null || friend.getFage() == null) {
            return new ResponseEntity<>("추가에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            Friend oldEntity = friendRepository.findById(friend.getId()).get();
            oldEntity.setFname(friend.getFname());
            oldEntity.setFage(friend.getFage());
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("추가에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
            friendRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
