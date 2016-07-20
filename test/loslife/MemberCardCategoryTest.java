package loslife;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asgab.entity.Service;
import com.asgab.entity.ServiceAttrMap;
import com.asgab.service.service.ServiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml" })
@ActiveProfiles("production")
public class MemberCardCategoryTest {

	@Autowired
	ServiceService serviceService;

	@Test
	public void getServiceByEnterprised() {
		List<Service> services = serviceService.getServiceByEnterpriseId("131955001317139159");
		for (Service service : services) {
			System.out.print(service.getName() + "    " + service.getPrices_salesPrice().doubleValue() + "    " + service.getCateName() + "    "
					+ service.getDecodedType());
			System.out.print("    " + service.getKeyValue(ServiceAttrMap.cannotusecard));
			System.out.print("    " + service.getKeyValue(ServiceAttrMap.noDiscount));
			System.out.print("    " + service.getKeyValue(ServiceAttrMap.fixed_bonus));
			System.out.print("    " + service.getKeyValue(ServiceAttrMap.stored_in_shop));
			System.out.print("    " + service.getKeyValue(ServiceAttrMap.service));
			System.out.print("    " + service.getComment());
			System.out.println();

		}
	}
}
