package com.clevercinema.services;

import org.springframework.data.repository.CrudRepository;

import com.clevercinema.model.Faq;

public interface FAQService extends CrudRepository<Faq, Integer> {

}
