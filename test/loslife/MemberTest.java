package loslife;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asgab.entity.Member;
import com.asgab.service.member.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml" })
@ActiveProfiles("production")
public class MemberTest {

	@Autowired
	MemberService memberService;

	@Test
	public void getServiceByEnterprised() {
		List<Member> members =memberService.getMembersByEnterpriseId("131955001317139159");
		for (Member member : members) {
			System.out.print(member.getName()+",");
			System.out.print(member.getDecodeSex()+",");
			System.out.print(member.getFmtBirthday()+",");
			System.out.print(member.getFmtJoinDate()+",");
			System.out.print(member.getCurrentScore()+",");
			System.out.print(member.getTotalScore()+",");
			System.out.print(member.getJob()+",");
			System.out.print(member.getDescription()+",");
			System.out.println();

		}
	}
}
