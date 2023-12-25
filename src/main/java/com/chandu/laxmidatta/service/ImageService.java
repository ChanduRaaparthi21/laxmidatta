package com.chandu.laxmidatta.service;

import org.springframework.stereotype.Service;

import com.chandu.laxmidatta.model.Image;

import java.util.List;

@Service
public interface ImageService {
    public Image create(Image image);
    public List<Image> viewAll();
    public Image viewById(long id);
    public Image delete(Image image);
}
