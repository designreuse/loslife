package com.asgab.service.business.opportunity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.BusinessOpportunity;
import com.asgab.entity.BusinessOpportunityProduct;
import com.asgab.repository.BusinessOpportunityMapper;
import com.asgab.repository.BusinessOpportunityProductMapper;

@Component
@Transactional
public class BusinessOpportunityService {

	@Autowired
	private BusinessOpportunityMapper businessOpportunityMapper;

	@Autowired
	private BusinessOpportunityProductMapper businessOpportunityProductMapper;

	public static Map<Integer, Integer> statusMap = new TreeMap<Integer, Integer>();
	public static Map<Integer, String> statusZH = new TreeMap<Integer, String>();
	public static Map<Integer, String> statusEN = new TreeMap<Integer, String>();

	static {
		statusMap.put(0, 1);
		statusMap.put(10, 2);
		statusMap.put(30, 3);
		statusMap.put(50, 4);
		statusMap.put(70, 5);
		statusMap.put(90, 6);
		statusMap.put(100, 7);

		statusZH.put(1, "丢失");
		statusZH.put(2, "跟进中");
		statusZH.put(3, "订单");
		statusZH.put(4, "订单");
		statusZH.put(5, "订单");
		statusZH.put(6, "订单");
		statusZH.put(7, "已签约");

		statusEN.put(1, "Lost");
		statusEN.put(2, "In progress");
		statusEN.put(3, "Won");
		statusEN.put(4, "Won");
		statusEN.put(5, "Won");
		statusEN.put(6, "Won");
		statusEN.put(7, "Signed");
	}

	public Page<BusinessOpportunity> search(Page<BusinessOpportunity> page) {
		List<BusinessOpportunity> businessOpportunitys = businessOpportunityMapper.search(page.getSearchMap(),
				page.getRowBounds());
		int count = businessOpportunityMapper.count(page.getSearchMap());
		page.setContent(businessOpportunitys);
		page.setTotal(count);
		return page;
	}

	public BusinessOpportunity get(Long id) {
		BusinessOpportunity businessOpportunity = businessOpportunityMapper.get(id);
		if (businessOpportunity != null) {
			businessOpportunity
					.setBusinessOpportunityProducts(businessOpportunityProductMapper.getByBusinessOpportunityId(id));
			for (int i = 0; i < businessOpportunity.getBusinessOpportunityProducts().size(); i++) {
				BusinessOpportunityProduct businessOpportunityProduct = businessOpportunity
						.getBusinessOpportunityProducts().get(i);
			}
		}
		return businessOpportunity;
	}

	public void save(BusinessOpportunity businessOpportunity) {
		businessOpportunity.setStatus(statusMap.get(businessOpportunity.getProgress()));
		businessOpportunity.setCreated_at(new Date());
		businessOpportunityMapper.save(businessOpportunity);
		for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity
				.getBusinessOpportunityProducts()) {
			if (businessOpportunityProduct != null && businessOpportunityProduct.getProduct_id() != null) {
				businessOpportunityProduct.setBusiness_opportunity_id(businessOpportunity.getId());
				businessOpportunityProduct.setCreated_at(new Date());
				businessOpportunityProductMapper.save(businessOpportunityProduct);
			}
		}
	}

	public String update(BusinessOpportunity businessOpportunity) {
		businessOpportunity.setUpdated_at(new Date());
		businessOpportunityMapper.update(businessOpportunity);
		for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity
				.getBusinessOpportunityProducts()) {
			if (businessOpportunityProduct.getId() != null) {
				businessOpportunityProduct.setUpdated_at(new Date());
				businessOpportunityProductMapper.update(businessOpportunityProduct);
			} else {
				businessOpportunityProduct.setBusiness_opportunity_id(businessOpportunity.getId());
				businessOpportunityProduct.setCreated_at(new Date());
				businessOpportunityProductMapper.save(businessOpportunityProduct);
			}
		}
		String result = null;
		// 如果进度为30 创建订单
		if (businessOpportunity.getProgress() == 30) {
			result = createOrder(businessOpportunity.getId());
		}
		return result;
	}

	public int delete(Long id) {
		List<BusinessOpportunityProduct> products = businessOpportunityProductMapper.getByBusinessOpportunityId(id);
		for (BusinessOpportunityProduct p : products) {
			p.setDeleted_at(new Date());
			businessOpportunityProductMapper.delete(p);
		}
		BusinessOpportunity delOpportunity = new BusinessOpportunity();
		delOpportunity.setId(id);
		delOpportunity.setDeleted_at(new Date());
		return businessOpportunityMapper.delete(delOpportunity);
	}

	private String createOrder(Long businessOpportunityId) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL("http://salesstg.optimix.asia/zh-cn/orders/create_order_by_business_opportunity");
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", "Token token=c576f0136149a2e2d9127b3901019855");

			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write("business_opportunity_id=" + businessOpportunityId);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
