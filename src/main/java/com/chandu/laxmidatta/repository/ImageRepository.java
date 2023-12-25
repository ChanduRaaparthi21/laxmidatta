package com.chandu.laxmidatta.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chandu.laxmidatta.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}