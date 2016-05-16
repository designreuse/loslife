package com.asgab.core.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import freemarker.template.TemplateException;

public class MailUtilTest2 {
  private static Logger log = LoggerFactory.getLogger(MailUtilTest2.class);

  @Test
  public void testMailTemplate() {
    String templateName = "my.ftl";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("content", "aaaaa");
    try {
      // MailUtil.sendMailByTemplate( "jack.sun@i-click.com","kevin.lau@i-click.com","test gmail",
      // map, templateName);
      MailUtil.sendMailAndFileByTemplate(Lists.newArrayList("iori.luo@i-click.com", "luoxxy@qq.com"), "test gmail", null, map, templateName);

    } catch (IOException e) {
      log.error(e.toString(), e);
    } catch (TemplateException e) {
      log.error(e.toString(), e);
    } catch (MessagingException e) {
      log.error(e.toString(), e);
    }
  }
  /*
   * @Test public void testMail() { try { MailUtil.sendMail("jack.sun@i-click.com", "test", "普通邮件");
   * } catch (IOException e) { log.error(e.toString(), e); } catch (MessagingException e) {
   * log.error(e.toString(), e); } }
   * 
   * @Test public void testMailAndFile() { try { String filePath = "/Users/Jack/tmp/112920_398.doc";
   * MailUtil.sendMailAndFile("jack.sun@i-click.com", "test", filePath, "普通邮件"); } catch
   * (IOException e) { log.error(e.toString(), e); } catch (MessagingException e) {
   * log.error(e.toString(), e); } }
   */
}
