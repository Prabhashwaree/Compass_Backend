package lk.me.compass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lk.me.compass.config.Encoder;
import lk.me.compass.dto.req.AuthReqDTO;
import lk.me.compass.dto.req.UserDTO;
import lk.me.compass.dto.res.AuthResDTO;
import lk.me.compass.entity.Role;
import lk.me.compass.entity.User;
import lk.me.compass.repo.RoleRepo;
import lk.me.compass.repo.UserRepo;
import lk.me.compass.service.AuthService;
import lk.me.compass.util.CommonUtil;
import lk.me.compass.util.JWTUtil;
import lk.me.compass.util.RandomLoginDetail;
import lk.me.compass.util.ResponseUtil;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    Encoder encoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public ResponseUtil authenticationLogin(AuthReqDTO authReqDTO) throws Exception {
        List<Object[]> userObject = userRepo.findUserByUsername(authReqDTO.getUsername());

        if (!userObject.isEmpty()) {
            Boolean isCorrect = encoder.matches(authReqDTO.getPassword().trim(), (String) userObject.get(0)[2]);

            if (isCorrect) {
                String token = jwtUtil.generateJwtToken(authReqDTO.getUsername());
                AuthResDTO authResDTO = new AuthResDTO();
                authResDTO.setId(Integer.parseInt(userObject.get(0)[0].toString()));
                authResDTO.setName(userObject.get(0)[1].toString());
                authResDTO.setToken(token);
                authResDTO.setUsername(authReqDTO.getUsername());
                authResDTO.setRoleName(userObject.get(0)[3].toString());

                return new ResponseUtil(200, "Login Successfully", authResDTO);

            } else {
                throw new RuntimeException("Incorrect username or password");
            }
        } else {
            throw new RuntimeException("Incorrect username or password");
        }
    }

    //need to change the save random password for database(LINE 72)
    @Override
    public ResponseUtil saveUser(UserDTO userDTO) throws Exception {

        Optional<Role> roleEntity = roleRepo.findById(CommonUtil.ARTIST_ID);

        if(!roleEntity.isPresent()){
            throw new RuntimeException("Could not find artist role");
        }

        UserDTO randomDetail = RandomLoginDetail.createRandomUserDetail(userDTO.getEmail());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setNic(userDTO.getNic());
        user.setContactNumber(userDTO.getContact());
        // user.setPassword(encoder.encode(randomDetail.getPassword()));
        user.setPassword(encoder.encode(randomDetail.getUsername()));
        user.setUsername(randomDetail.getUsername());
        user.setRoleId(roleEntity.get());
        user.setStatus(CommonUtil.ACTIVE); 

        System.out.println(user.getUpdatedDate());

        User savedUser = userRepo.save(user);

        if (savedUser != null) {
            return new ResponseUtil(500, "New user added successfully", randomDetail);
        } else {
            return new ResponseUtil(500, "Error,User saved unsuccessfully", null);
        }
    }
}
