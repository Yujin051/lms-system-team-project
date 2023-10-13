package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.CommentRepository;
import org.springframework.stereotype.Service;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;



}
