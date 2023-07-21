package com.webstudy.webstudy;

import com.webstudy.webstudy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebstudyApplicationTests {

	@Autowired
	private BoardService boardService;

}
