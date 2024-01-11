package com.example.project.service.admin;

import com.example.project.dto.user.OwnerMailDto;
import com.example.project.dto.user.RequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ITemplateEngine templateEngine;

    public Map<String, Object> mailSend(Object dto) throws MessagingException {
        Map<String, Object> map = new HashMap<>();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();

        if(dto instanceof OwnerMailDto){    //제품주인이 운송사에 운송요청서를 보낼때
            context.setVariable("form", dto);
            String html = templateEngine.process("mail/ownerApplication",context);

            helper.setTo("showseaside@gmail.com");
            helper.setSubject("에코렌트 운송요청 내역서 전달드립니다.");
            helper.setText(html,true);
            javaMailSender.send(message);
            map.put("result", "good");
        }else if (dto instanceof RequestDto){   //일반 유저가 운송사에 견적요청서를 보낼때
            context.setVariable("form", dto);
            String html = templateEngine.process("mail/userApplication",context);

            helper.setTo(((RequestDto) dto).getShippingEmail());
            helper.setSubject("페덱스 운송요청 내역서 전달드립니다.");
            helper.setText(html,true);
            javaMailSender.send(message);
            map.put("result", "good");
        }else{
            map.put("result", "failure");
        }

        return map;
    }

}
