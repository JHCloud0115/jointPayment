import org.apache.ibatis.session.SqlSession;
import org.example.Application;
import org.example.mapper.MemberMapper;
import org.example.model.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
//@MybatisTest
public class MemberMapperTest {


    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testSelectMembers() {
        List<Member> memberList =memberMapper.selectMembers();
    }

    @Test
    void name() {

    }
}
