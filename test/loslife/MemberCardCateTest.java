package loslife;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asgab.entity.MemberCardCategory;
import com.asgab.service.member.MemberCardCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml" })
@ActiveProfiles("production")
public class MemberCardCateTest {

	@Autowired
	MemberCardCategoryService memberCardCategoryService;

	@Test
	public void test() {
		 List<MemberCardCategory>  cards = memberCardCategoryService.getMemberCardCategoryByEnterpriseId("131955001317139159");
		 for(MemberCardCategory card:cards){
			 System.out.print(card.getName()+"    ");
			 System.out.print(card.getBaseInfo_minMoney()+"    ");
			 System.out.print(card.getDecodeBaseInfo_type()+"    ");
			 System.out.print(card.getDiscount()+"    ");
			 System.out.print(card.getCardValid()+"    ");
			 System.out.print(card.getCardNoGenRule_cardNoPrefix()+"    ");
			 System.out.print(card.getActiveCardPresentMoney()+"    ");
			 System.out.print(card.getActiveCardPresentScore()+"    ");
			 System.out.print(card.getRechargePresentScore()+"    ");
			 System.out.print(card.getConsumePresentScore()+"    ");
			 System.out.print(card.getInverse_ratio()+"    ");
		 }
	}
}
