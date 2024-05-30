package com.twd.SpringSecurityJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}