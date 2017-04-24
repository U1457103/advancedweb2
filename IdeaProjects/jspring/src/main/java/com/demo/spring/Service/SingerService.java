package com.demo.spring.Service;

import com.demo.spring.domain.Singer;
import com.demo.spring.domain.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by web on 18/04/17.
 */
@Service
public class SingerService {
    @Autowired
    SingerRepository singerRepository;

    public Singer save(Singer u) { return singerRepository.save(u); }


    public List<Singer> findAll(){ return singerRepository.findAll();}

    public void delete(Singer user) { singerRepository.delete(user);}

}
