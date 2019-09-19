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

/**
 * Servlet implementation class FenleiServlet
 */
@WebServlet("/FenleiServlet")
public class FenleiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int[] list1={3,11,18,20,21,48,53,63,64,77,85,86,87,101,132,135,160,169,197,248};
	int[] list2={9,10,26,32,34,35,46,49,50,55,57,58,66,68,72,76,79,82,83,88,89,91,92,93,99,117,122,123,125,137,140,150,151,153,155,164,166,181,184,185,187,192,193,198,204,206,207,211,216,220,236};
	int[] list3={14,23,36,56,65,97,98,119,133,141,148,149,163,182,183,188,190,196,202,210,218,219,221,222,223,224,225,226,227};
	int[] list4={1,5,6,7,15,22,24,28,30,39,41,45,47,59,71,73,78,90,109,110,111,114,115,124,127,128,134,143,161,170,175,176,200,201,208,214,215,235};
	int[] list5={4,61,107,146,147,157,158,168,231,239};
	int[] list6={2,25,33,62,96,100,130,136,144,145,156,162,178,179,180,217,233,241,243,250};
	int[] list7={12,13,16,19,27,31,37,38,40,42,54,67,69,70,74,80,81,84,94,102,103,104,105,113,121,126,131,138,142,154,159,167,171,172,173,174,177,194,199,205,209,212,213,232,249};
	int[] list8={29,129,240,242,245};
	int[] list9={17,43,44,51,52,60,95,112,139,186,230,234,244,246,247,251};
	int[] list10={8,75,106,108,116,118,120,152,165,189,191,195,203,228,229,237,238};   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FenleiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//*[@id="main"]/div/div[2]/div[1]/div[2]/div/a
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin","*");
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		int[] list=null;
		switch (type) {
		case "1":
			list=list1;
			break;
		case "2":
			list=list2;
			break;
		case "3":
			list=list3;
			break;
		case "4":
			list=list4;
			break;
		case "5":
			list=list5;
			break;
		case "6":
			list=list6;
			break;
		case "7":
			list=list7;
			break;
		case "8":
			list=list8;
			break;
		case "9":
			list=list9;
			break;
		case "10":
			list=list10;
			break;
		default:
			System.out.println("错误");
			break;
		}
		JSONObject json = new JSONObject();
		List<JSONObject> data = new ArrayList<>();
		List<Word> words = new ArrayList<>();
		IWordDao dao = new WordDaoImpl();
		words = dao.allWords1(list);
		for(Word word:words) {
			JSONObject wordi = new JSONObject();
			wordi.put("name",word.getName());
			wordi.put("expl",word.getExpl());
			data.add(wordi);
		}
		json.put("data", data);
		System.out.println(json);
		//System.out.println(type);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toJSONString());
	}

}
