package com.asgab.service.enterprise;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.Member;
import com.asgab.entity.MemberCard;
import com.asgab.entity.MemberCardCategory;
import com.asgab.entity.RecordCateService;
import com.asgab.entity.Service;
import com.asgab.entity.ServiceAttrMap;
import com.asgab.service.member.MemberCardCategoryService;
import com.asgab.service.member.MemberCardService;
import com.asgab.service.member.MemberService;
import com.asgab.service.service.ServiceService;

@Component
@Transactional
public class EnterpriseService {

  @Autowired
  private ServiceService serviceService;

  @Autowired
  private MemberCardCategoryService memberCardCategoryService;

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberCardService memberCardService;

  public XSSFWorkbook exportEnterprise(InputStream in, String enterpriseId) {
    XSSFWorkbook workbook = null;
    try {
      workbook = new XSSFWorkbook(in);
      // 经营项目
      XSSFSheet sheetService = workbook.getSheetAt(1);
      List<Service> services = serviceService.getServiceByEnterpriseId(enterpriseId);
      if (services != null && services.size() > 0) {
        // style
        XSSFCellStyle alignRight = workbook.createCellStyle();
        alignRight.setAlignment(XSSFCellStyle.ALIGN_RIGHT);

        int bodyRowIndex = 1;
        for (Service service : services) {
          int bodyColumbIndex = 0;
          XSSFRow row = sheetService.createRow(bodyRowIndex++);
          Cell cell = null;
          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getPrices_salesPrice() != null ? service.getPrices_salesPrice().doubleValue() : 0);

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getCateName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getDecodedType());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getDecodeCannotUseCard());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getDecodeNoDiscount());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getKeyValue(ServiceAttrMap.fixed_bonus));

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getDecodeStored_in_shop());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getKeyValue(ServiceAttrMap.service));

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(service.getComment());

        }
      }

      // 会员卡类型
      XSSFSheet sheetMemberCardCategory = workbook.getSheetAt(2);
      List<MemberCardCategory> cardCategorys = memberCardCategoryService.getMemberCardCategoryByEnterpriseId(enterpriseId);
      if (cardCategorys != null && cardCategorys.size() > 0) {
        int bodyRowIndex = 1;
        for (MemberCardCategory memberCardCategory : cardCategorys) {
          int bodyColumbIndex = 0;
          XSSFRow row = sheetMemberCardCategory.createRow(bodyRowIndex++);
          Cell cell = null;

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getBaseInfo_minMoney() != null ? memberCardCategory.getBaseInfo_minMoney().doubleValue() : 0);

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getDecodeBaseInfo_type());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getDiscount());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getCardValid());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getCardNoGenRule_cardNoPrefix());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(
              memberCardCategory.getActiveCardPresentMoney() != null ? memberCardCategory.getActiveCardPresentMoney().doubleValue() : 0);

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getActiveCardPresentScore());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getRechargePresentScore());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getConsumePresentScore());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(memberCardCategory.getInverse_ratio());
        }
      }

      // 计次卡服务
      XSSFSheet sheetRecordCateService = workbook.getSheetAt(3);
      List<RecordCateService> recordCateServices = memberCardCategoryService.getRecordCateServicesByEnterpriseId(enterpriseId);
      if (recordCateServices != null && recordCateServices.size() > 0) {
        int bodyRowIndex = 1;
        for (RecordCateService recordCateService : recordCateServices) {
          int bodyColumbIndex = 0;
          XSSFRow row = sheetRecordCateService.createRow(bodyRowIndex++);
          Cell cell = null;

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(recordCateService.getCardCateName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(recordCateService.getServiceName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(recordCateService.getDef_int1());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(recordCateService.getDef_int2());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(recordCateService.getDef_int3());
        }
      }

      // 会员信息
      XSSFSheet sheetMember = workbook.getSheetAt(4);
      List<Member> members = memberService.getMembersByEnterpriseId(enterpriseId);
      if (members != null && members.size() > 0) {
        int bodyRowIndex = 1;
        for (Member member : members) {
          int bodyColumbIndex = 0;
          XSSFRow row = sheetMember.createRow(bodyRowIndex++);
          Cell cell = null;

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getMemberNo());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getDecodeSex());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getFmtBirthday());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getPhoneMobile());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getFmtJoinDate());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getCurrentScore() == null ? 0 : member.getCurrentScore().doubleValue());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getTotalScore() == null ? 0 : member.getTotalScore().doubleValue());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getJob());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(member.getDescription());
        }
      }

      // 会员卡信息
      XSSFSheet sheetMemberCard = workbook.getSheetAt(5);
      List<MemberCard> cards = memberCardService.getMemberCardsByEnterpriseId(enterpriseId);
      if (cards != null && cards.size() > 0) {
        int bodyRowIndex = 1;
        for (MemberCard card : cards) {
          int bodyColumbIndex = 0;
          XSSFRow row = sheetMemberCard.createRow(bodyRowIndex++);
          Cell cell = null;

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(card.getMemberNo());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(card.getCardNo());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(card.getMemberCardCategoryName());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(card.getCardValue() == null ? 0 : card.getCardValue().doubleValue());

          cell = row.createCell(bodyColumbIndex++);
          cell.setCellValue(card.getFmtCreate_date());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return workbook;

  }

}
