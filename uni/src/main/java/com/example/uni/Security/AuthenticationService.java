package com.example.uni.Security;

import com.example.uni.Dto.CredentialsDto;
import com.example.uni.Dto.UserDto;
import com.example.uni.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class AuthenticationService {

    @Value("${auth.cookie.hmac-key:secret-key}")
    private String secretKey;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto authenticate(CredentialsDto credentialsDto) throws Exception {
//        UserDto user = userRepository.findByUsername(credentialsDto.getLogin());
//
//        if (user==null)
//            throw new Exception("User not found");
//
//        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
//            log.debug("User {} authenticated correctly", credentialsDto.getLogin());
//            return userMapper.toUserDto(user);
//        }
//        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
        return null;

    }

    public UserDto findByLogin(String login) {

//        if ()

        return null;
    }


    public String createToken(UserDto userDto){
        return userDto.getId()+"&"+userDto.getUsername()+"&"+caculateHmac(userDto);
    }


    private String caculateHmac(UserDto userDto) {
        byte[] secretKeyBytes = Objects.requireNonNull(secretKey)
                .getBytes(StandardCharsets.UTF_8);

        byte[] valueBytes = (userDto.getId() + "&" + userDto.getUsername())
                .getBytes(StandardCharsets.UTF_8);

        try {
            Mac mac = Mac.getInstance("HmacSH512");
            SecretKeySpec spec = new SecretKeySpec(secretKeyBytes,"HmacSH512");
            mac.init(spec);
            mac.doFinal(valueBytes);
            byte[] hamcByte=mac.doFinal(valueBytes);
            return Base64.getEncoder().encodeToString(hamcByte);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserDto findByToken(String token) throws Exception {
        String[] parts=token.split("&");
        Long userId=Long.valueOf(parts[0]);

        String login=parts[1];

        String hmac=parts[2];

        UserDto userDto=findByLogin(login);

        if (!hmac.equals(caculateHmac(userDto)) || !userId.equals(userDto.getId())){
            throw new Exception("Invalid Cookie value");
        }

        return userDto;

    }


}
