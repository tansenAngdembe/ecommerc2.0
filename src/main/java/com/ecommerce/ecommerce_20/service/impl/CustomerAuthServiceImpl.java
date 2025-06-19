package com.ecommerce.ecommerce_20.service.impl;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.core.service.JwtService;
import com.ecommerce.ecommerce_20.dto.Request.customer.LoginRequest;
import com.ecommerce.ecommerce_20.dto.Response.profile.ProfileResponse;
import com.ecommerce.ecommerce_20.dto.Response.token.TokenResponse;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.mapper.CustomersMapper;
import com.ecommerce.ecommerce_20.repository.CustomersRepo;
import com.ecommerce.ecommerce_20.service.CustomerAuthService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CustomerAuthServiceImpl implements CustomerAuthService {
    private final CustomersRepo customersRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomersMapper customersMapper;


   // customer login
    @Override
    public ApiResponse<?> login(LoginRequest loginRequest) {
        try{
            Customers user = customersRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()->new BadCredentialsException("Email Not Found"));
            if(user.getIsAccountLocked()){
                if(isLockedTimeExpired(user)){
                    user.setWrongPasswordAttemptCount(0);
                    user.setIsAccountLocked(false);
                    user.setLockedTime(null);
                    customersRepo.save(user);
                }else{
                    return ResponseUtil.getSuccessMessage(-1, "Account is locked, please try again later.");
                }
            }

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            if(auth.isAuthenticated()){
                user.setWrongPasswordAttemptCount(0);
                user.setIsAccountLocked(false);
                user.setLockedTime(null);
                user.setLastLoggedInAt(Instant.now());
                customersRepo.save(user);

                UserDetails customer = (UserDetails) auth.getPrincipal();
                String token = jwtService.generateToken(customer);
                TokenResponse TokenResponse = new TokenResponse(token);
                return ResponseUtil.getSuccessMessage(0, "Login Successful",TokenResponse);

            }else{
                return ResponseUtil.getSuccessMessage(-1, "Login Failed");
            }


        }catch(BadCredentialsException b){
            customersRepo.findByEmail(loginRequest.getEmail()).ifPresent(user ->{
                Integer attempts = user.getWrongPasswordAttemptCount() + 1;
                user.setWrongPasswordAttemptCount(attempts);
                if(attempts >= 5){
                    user.setIsAccountLocked(true);
                    user.setLockedTime(Instant.now());
                }
                customersRepo.save(user);
            });
            return ResponseUtil.getSuccessMessage(-1, "Invalid credentials.");

        }catch(UsernameNotFoundException e){
            return ResponseUtil.getSuccessMessage(-1, "User not found.");

        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.getSuccessMessage(-1, "Internal Server Error.");
        }

    }


    @Override
    public ApiResponse<?> profile(Principal principal) {
       try{
           Customers user = customersRepo.findByEmail(principal.getName()).orElseThrow(()->new BadCredentialsException("Email Not Found"));
           ProfileResponse profile = customersMapper.customerToProfile(user);
           return ResponseUtil.getSuccessMessage(0, "Profile fetch Successful", profile);
       }catch(Exception e){
           return null;
       }
    } 


 private boolean isLockedTimeExpired(Customers customers){
        final int LOCKED_TIME = 10;
        return customers.getLockedTime() != null &&
                customers.getLockedTime().plus(LOCKED_TIME, ChronoUnit.MINUTES).isBefore(Instant.now());
    }


}
