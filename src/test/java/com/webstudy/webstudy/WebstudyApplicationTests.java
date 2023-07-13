package com.webstudy.webstudy;

import com.webstudy.webstudy.board.BoardEntity;
import com.webstudy.webstudy.board.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebstudyApplicationTests {

	@Autowired
	private BoardService boardService;

}
