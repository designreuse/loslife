package loslife;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asgab.entity.RecordCateService;
import com.asgab.entity.Service;
import com.asgab.entity.ServiceAttrMap;
import com.asgab.service.member.MemberCardCategoryService;
import com.asgab.service.service.ServiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml" })
@ActiveProfiles("production")
public class RecordCateServiceCategoryTest {

	@Autowired
	MemberCardCategoryService memberCardCategoryService;

	@Test
	public void getServiceByEnterprised() {
		List<RecordCateService> services =memberCardCategoryService.getRecordCateServicesByEnterpriseId("100008101411200100");
		for (RecordCateService service : services) {
			System.out.print(service.getCardCateName()+"    ");
			System.out.print(service.getServiceName()+"    ");
			System.out.print(service.getDef_int1()+"    ");
			System.out.print(service.getDef_int2()+"    ");
			System.out.print(service.getDef_int3()+"    ");
			System.out.println();

		}
	}
}
