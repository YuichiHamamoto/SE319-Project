package com.example.track_your_fridge;

import com.example.track_your_fridge.Logic.LoginLogic;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginTest {


    @Test
    public void whenLoginCalledVerified() throws JSONException, InterruptedException {
        LoginLogic test = mock(LoginLogic.class);
        LoginActivity testLogInSuccess = new LoginActivity();

        String usernameCorrect = "cow";
        String passwordCorrect = "password";


        when(test.login(usernameCorrect,passwordCorrect)).thenReturn(true);
        Assert.assertEquals(testLogInSuccess.tryLogin("cow","password",test),true);

    }

    @Test
    public void whenLoginCalledNotVerified() throws JSONException{
        LoginLogic test = mock(LoginLogic.class);
        LoginActivity testLogInSuccess = new LoginActivity();

        String usernameCorrect = "cow";
        String passwordWrong = "Password";

        when(test.login(usernameCorrect,passwordWrong)).thenReturn(false);
        Assert.assertEquals(testLogInSuccess.tryLogin("cow","password",test),false);
    }
}
