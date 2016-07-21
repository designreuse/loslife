package loslife;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asgab.entity.MemberCard;
import com.asgab.service.member.MemberCardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml"})
@ActiveProfiles("production")
public class MemberCardTest {

  @Autowired
  MemberCardService memberCardService;

  @Test
  public void getMemberCardsByEnterprised() {
    List<MemberCard> cards = memberCardService.getMemberCardsByEnterpriseId("100008101411200100");
    for (MemberCard card : cards) {
      System.out.print(card.getMemberNo() + "----");
      System.out.print(card.getCardNo() + "----");
      System.out.print(card.getMemberCardCategoryName() + "----");
      System.out.print(card.getCardValue() + "----");
      System.out.print(card.getFmtCreateDate() + "----");
      System.out.println();

    }
  }
}
