package org.nlb.security.utils;
import org.nlb.security.entity.Student;
import org.nlb.security.service.StudentService;
import org.nlb.security.utils.picture.StudentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
@Component
public class StudentServiceUtils implements UserDetailsService {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentFilter studentFilter;
    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student studentByName = studentService.getStudentByName(username);
        String basePassword= null;
        String redisPassword= null;
        try {
            redisPassword = redisTemplate.opsForValue().get(username);
            basePassword = EnconderPassword.EncoderPW(redisPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(studentByName.getPassword().equals(basePassword)){
            return new User(username,redisPassword, true, true, true, true, AuthorityUtils.createAuthorityList("root"));
        }else{
            throw new UsernameNotFoundException("密码不对");
        }
    }

}

