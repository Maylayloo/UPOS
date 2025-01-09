package com.example.backend.service.userService;

import com.example.backend.model.MyUser;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MyUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Admin> admin=adminRepository.findByEmail(email);
//        Optional<Professor> professor=professorRepository.findByEmail(email);
//        if(admin.isPresent()){
//           return  buildUser(admin.get().getEmail(),admin.get().getPassword(),"ADMIN");
//        }
//        else if(professor.isPresent()){
//            return buildUser(professor.get().getEmail(),professor.get().getPassword(),"PROFESSOR");
//        }
//        Optional<Student> student=studentRepository.findByEmail(email);
//        if(student.isPresent()){
//            return buildUser(student.get().getEmail(),student.get().getPassword(),"STUDENT");
//        }
//        throw new UsernameNotFoundException(email);
          Optional<MyUser> user= userRepository.findByEmail(email);
          System.out.println(user.get().getName());
          if(user.isPresent()){
              return buildUser(user.get().getEmail(),user.get().getPassword(),"USER");
          }
          throw new UsernameNotFoundException(email);

    }
    public UserDetails buildUser(String email,String password,String role){
        return User.builder()
                .username(email)
                .password(password)
                .roles(role)
                .build();
    }
}
