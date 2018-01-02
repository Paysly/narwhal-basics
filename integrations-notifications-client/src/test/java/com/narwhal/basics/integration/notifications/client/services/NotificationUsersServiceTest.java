package com.narwhal.basics.integration.notifications.client.services;

import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.integrations.notifications.client.dto.users.NotificationUserDTO;
import com.narwhal.basics.integrations.notifications.client.dto.users.NotificationUserPagingResultDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationUserEndpoint;
import com.narwhal.basics.integrations.notifications.client.services.NotificationUsersService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class NotificationUsersServiceTest {

    @InjectMocks
    private NotificationUsersService service;
    @Mock
    private NotificationUserEndpoint endpoint;

    @Before
    public void setUp() {
        service = new NotificationUsersService();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = BadRequestException.class)
    public void getUsers_nullClientId() {
        service.getUsers(null, 100, "123", "cristianoreste@race.capital");
        //
        Mockito.verify(endpoint, never()).getUsers(anyString(), anyInt(), anyString(), anyString());
    }

    @Test
    public void getUsers_success() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        //
        NotificationUserDTO dto2 = new NotificationUserDTO();
        dto2.setPhone("54337225");
        dto2.setId("45978465167");
        //
        NotificationUserPagingResultDTO pagingResult = new NotificationUserPagingResultDTO();
        //
        ArrayList<NotificationUserDTO> list = new ArrayList<>();
        list.add(dto);
        list.add(dto2);
        //
        pagingResult.setResultList(list);
        //
        when(endpoint.getUsers(eq("clientId"), eq(100), eq("123"), eq("cristianoreste@race.capital"))).thenReturn(pagingResult);
        //
        PagingResult<NotificationUserDTO> result = service.getUsers("clientId", 100, "123", "cristianoreste@race.capital");
        //
        assertEquals(pagingResult, result);
        //
        Mockito.verify(endpoint).getUsers("clientId", 100, "123", "cristianoreste@race.capital");
    }

    @Test(expected = BadRequestException.class)
    public void getUser_nullClientId() {
        service.getUser(null, "userId");
        //
        Mockito.verify(endpoint, never()).getUser(anyString(), anyString());
    }

    @Test(expected = BadRequestException.class)
    public void getUser_nullUserId() {
        service.getUser("clientId", null);
        //
        Mockito.verify(endpoint, never()).getUser(anyString(), anyString());
    }

    @Test
    public void getUser_success() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        //
        when(endpoint.getUser(eq("clientId"), eq("userId"))).thenReturn(dto);
        //
        NotificationUserDTO result = service.getUser("clientId", "userId");
        //
        assertEquals(dto, result);
        //
        Mockito.verify(endpoint).getUser("clientId", "userId");
    }

    @Test(expected = BadRequestException.class)
    public void saveUser_nullClientId() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        service.saveUser(null, dto);
        //
        Mockito.verify(endpoint, never()).saveUser(anyString(), (NotificationUserDTO) any());
    }

    @Test(expected = BadRequestException.class)
    public void saveUser_nullDTO() {
        service.saveUser("clientId", null);
        //
        Mockito.verify(endpoint, never()).saveUser(anyString(), (NotificationUserDTO) any());
    }

    @Test
    public void saveUser_success() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        service.saveUser("clientId", dto);
        //
        Mockito.verify(endpoint).saveUser("clientId", dto);
    }

    @Test(expected = BadRequestException.class)
    public void patchUser_nullClientId() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        service.patchUser(null, "userId", dto);
        //
        Mockito.verify(endpoint, never()).patchUser(anyString(), anyString(), (NotificationUserDTO) any());
    }

    @Test(expected = BadRequestException.class)
    public void patchUser_nullDTO() {
        service.patchUser("clientId", "userId", null);
        //
        Mockito.verify(endpoint, never()).patchUser(anyString(), anyString(), (NotificationUserDTO) any());
    }

    @Test(expected = BadRequestException.class)
    public void patchUser_nullUserId() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("54337225");
        dto.setId("45978465167");
        service.patchUser("clientId", null, dto);
    }

    @Test
    public void patchUser_success() {
        NotificationUserDTO dto = new NotificationUserDTO();
        dto.setPhone("phone");
        dto.setId("id");
        dto.setFirebaseToken("firebaseToken");
        dto.setEmail("email");
        //
        when(endpoint.getUser("clientId", "userId")).thenReturn(dto);
        //
        service.patchUser("clientId", "userId", dto);
        //
        Mockito.verify(endpoint).patchUser("clientId", "userId", dto);
        Mockito.verify(endpoint).getUser("clientId", "userId");
    }

    @Test(expected = BadRequestException.class)
    public void deleteUser_nullClientId() {
        service.deleteUser(null, "userId");
        //
        Mockito.verify(endpoint, never()).deleteUser(anyString(), anyString());
    }

    @Test(expected = BadRequestException.class)
    public void deleteUser_nullUserId() {
        service.deleteUser("clientId", null);
        //
        Mockito.verify(endpoint, never()).deleteUser(anyString(), anyString());
    }

    @Test
    public void deleteUser_success() {
        service.deleteUser("clientId", "userId");
        //
        Mockito.verify(endpoint).deleteUser("clientId", "userId");
    }
}
