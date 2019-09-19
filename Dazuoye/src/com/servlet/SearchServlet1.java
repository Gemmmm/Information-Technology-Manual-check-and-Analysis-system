package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.dao.IWordDao;
import com.dao.WordDaoImpl;
import com.model.Word;
import com.util.BaikeScrawl;
import com.xq.algorithm.*;

/**
 * Servlet implementation class SearchServlet1
 */
@WebServlet("/SearchServlet1")
public class SearchServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin","*");
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		List<JSONObject> data = new ArrayList<>();
		String name = request.getParameter("name2");
		if(name == null || name.equals("")) {
			name = request.getParameter("name1");
		}
		IWordDao wordDao = new WordDaoImpl();
		Word word = wordDao.search(name);

		BaikeScrawl baike = new BaikeScrawl();
			String str1=baike.run(name);
			str1=BaikeScrawl.search;
			System.out.println("search"+str1);
			word = wordDao.search(name);
			json.put("name", name);
			json.put("expl", word.getExpl());
			json.put("search",str1);
			json.put("similar",CosineSimilarAlgorithm.cosSimilarityByString(word.getExpl(), str1));
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json.toJSONString());

		
		System.out.println(json);
	}

}
