package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;


    @Test
    void helloworld_3_async_calls_handle() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();


        //when
        String result = hwcfe.helloworld_3_async_calls_handle();

        //then
        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_handle_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));


        //when
        String result = hwcfe.helloworld_3_async_calls_handle();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_handle_3() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();


        //when
        String result = hwcfe.helloworld_3_async_calls_handle();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_exceptionally() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();


        //when
        String result = hwcfe.helloworld_3_async_calls_exceptionally();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_exceptionally_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));


        //when
        String result = hwcfe.helloworld_3_async_calls_exceptionally();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_whenhandle() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();


        //when
        String result = hwcfe.helloworld_3_async_calls_whenhandle();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloworld_3_async_calls_whenhandle_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));


        //when
        String result = hwcfe.helloworld_3_async_calls_whenhandle();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }
}