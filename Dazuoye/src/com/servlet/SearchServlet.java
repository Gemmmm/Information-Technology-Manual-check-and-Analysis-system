package com.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
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
import com.xq.algorithm.Cosine;
import com.xq.algorithm.CosineSimilarAlgorithm;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SearchServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		List<JSONObject> data = new ArrayList<>();
		String name = request.getParameter("name2");
		if (name == null || name.equals("")) {
			name = request.getParameter("name1");
		}
		IWordDao wordDao = new WordDaoImpl();
		Word word = wordDao.search(name);

		BaikeScrawl baike = new BaikeScrawl();
		String str1 = baike.run(name);
		str1 = BaikeScrawl.search;
		if (str1 == null || str1.equals("")) {
			str1 = "";
		}
		System.out.println("search" + str1);
		word = wordDao.search(name);
		String str2 = word.getExpl();
		if (str2 == null || str2.equals("")) {
			str2 = "";
		}
		System.out.println("searchdb" + str2);
		String[] list1 = str1.split("，");
		String[] list2 = str2.split("，");
		double maxsimilar, temp;
		int maxi;
		double sumsimialr = 0;
		for (int i = 0; i < list1.length; i++) {
			maxi = 0;
			maxsimilar = 0;
			temp = 0;
			for (int j = 0; j < list2.length; j++) {
				temp = Cosine.getSimilarity(list1[i], list2[j]);
				if (maxsimilar < temp)
					maxsimilar = temp;
				maxi = i;
			}
			JSONObject wordi = new JSONObject();
			wordi.put("exp", list1[i]);
			System.out.println(maxsimilar);
			// sumsimialr+=maxsimilar*list1[i].length()/str1.length();
			if (maxsimilar > 0.7) {
				wordi.put("flag", "1");
				sumsimialr += 1.0 * list1[i].length() / str1.length();
			} else {
				wordi.put("flag", "0");
			}
			data.add(wordi);
		}
		System.out.println(list1.length);
		DecimalFormat df = new DecimalFormat("#.0%");
		// System.out.println(df.format(f));
		json.put("data1", data);
		json.put("search", str2);
		json.put("similar", df.format(sumsimialr));
		json.put("unsimilar", df.format(1-sumsimialr));
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toJSONString());
		System.out.println(json);
	}
	public static String[] getTextList(String str1) {
		//String str1="问哦，数据否为。水电费金额，访问偶然间。";
		String[] list1=str1.split("。");
		String[] all=new String[50];
		int k=0;
		for (int i = 0; i < list1.length; i++) {
			String[] ss=list1[i].split("，");
			for (int j = 0; j < ss.length; j++) {
				all[k]=ss[j];
				k++;
			}
		}
		String[] total=new String[k];
		for (int i = 0;  all[i]!=null; i++) {
			total[i]=all[i];
			//System.out.println(all[i]);
		}
		System.out.println(total.length);
		return total;
	}
}
