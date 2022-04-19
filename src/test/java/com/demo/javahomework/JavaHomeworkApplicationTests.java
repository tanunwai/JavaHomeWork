package com.demo.javahomework;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.demo.javahomework.POJO.CoinDeskPOJO;
import com.demo.javahomework.POJO.CurrencyNamePOJO;
import com.demo.javahomework.service.CoinDeskService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class JavaHomeworkApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	/*
	 * @Autowired private CoinDeskController coinDeskController;
	 */

	@MockBean
	private CoinDeskService coinDeskService;

	private ResponseEntity<List<CoinDeskPOJO>> coinDeskResp;

	private ResponseEntity<CurrencyNamePOJO> currencyResp;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = standaloneSetup(this.coinDeskController).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void contextLoads() throws Exception {
		System.out.println("Test the CoinDest :");
	}

	@Test
	void findNewApi() throws Exception {
		when(this.coinDeskService.findCoinDeskByName("ETH")).thenReturn(currencyResp);
		mockMvc.perform(get("/api/v2/viewnewapi?chartName=ETH").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void findAllCoindeskApi() throws Exception {
		when(this.coinDeskService.findAllCoindesk()).thenReturn(coinDeskResp);
		mockMvc.perform(get("/api/v2/listcoin").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void getApiCurrentBitcoin() throws Exception {
		mockMvc.perform(get("/api/v2/viewparsejson?url=http://api.coindesk.com/v1/bpi/currentprice.json")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	void addCoin() throws Exception {
		String jsonStr = "{\"updateTime\":\"2022/04/08 15:13:22\", \"disclaimer\":\"Ethereum is open access to digital money and data-friendly services for everyone – no matter your background or location.\", \"chartName\":\"ETH\", \"code\":\"NT\", \"symbol\":\"$NT\", \"rate\":\"10,664,124,761,206\", \"description\":\"New Taiwan Dollar\", \"rate_float\":88561.11}";
		// CoinDeskPOJO coinDeskPOJO=new CoinDeskPOJO("2022/04/08 15:13:22", null, "以太坊
		// (Ethereum) 為去中心化的開源區塊鏈系統，且擁有自己的加密貨幣「以太幣
		// (Ether)」。以太坊亦為其他許多加密貨幣運作，以及執行去中心化智能合約的平台。", "ETH", "NT", "NT", "$NT",
		// "10,664,124,761,206", "New Taiwan Dollar", 88561.11);
		// when(this.coinDeskService.addCoindesk(coinDeskPOJO)).thenReturn(coinDeskResp_String);
		mockMvc.perform(post("/api/v2/addcoin").content(jsonStr).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void updateCoin() throws Exception {
		String jsonStr = "{\n" + " \"id\": \"3eec3ad4-b796-45b0-9e85-3fa8b2f7f8f4\",\n"
				+ " \"updateTime\": \"1999/12/02 08:08:35\",\n"
				+ " \"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\n"
				+ " \"chartName\":\"Bitcoin\",\n" + " \"code\":\"USD\",\n" + " \"symbol\":\"&#36;\",\n"
				+ " \"rate\":\"43,875.1231\",\n" + " \"description\":\"United States Dollar\",\n"
				+ " \"rate_float\":43875.1231\n" + "}";
		mockMvc.perform(patch("/api/v2/updatecoin").content(jsonStr).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void deleteCoin() throws Exception {
		mockMvc.perform(delete("/api/v2/deletecoin/3eec3ad4-b796-45b0-9e85-3fa8b2f7f8f4")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print());
	}

}