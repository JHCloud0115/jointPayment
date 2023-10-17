import org.apache.ibatis.session.SqlSession;
import org.example.Application;
import org.example.mapper.MemberMapper;
import org.example.model.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
//@MybatisTest
public class MemberMapperTest {


    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSelectMembers() {
        List<Member> memberList =memberMapper.selectMembers();
    }

    @Test
    void pwdEcn() {
        String pwd = "kedric123";
        String encodedPwd = passwordEncoder.encode(pwd); //암호화 하는부분
        System.out.println(encodedPwd);
    }

    @Test
    void pwdMatch(){
        // 기존 저장해두었던 암호화된 비밀번호
        String encodedPwd = "{bcrypt}$2a$10$xO99cg0RupsQY4PNvdPJe.neRL7JSplM8t/NQUgBRGnOM19/FbstS";
        // 검증할 비밀번호
        String newPwd = "kedric123";

        if(passwordEncoder.matches(newPwd, encodedPwd)){
            System.out.println("true");
        }else{
            System.out.println("false");
            }
        }
}
