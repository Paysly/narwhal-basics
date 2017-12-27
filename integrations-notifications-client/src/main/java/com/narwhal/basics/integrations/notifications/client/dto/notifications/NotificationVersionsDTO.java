package com.narwhal.basics.integrations.notifications.client.dto.notifications;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVersionsDTO implements Serializable {

    private List<NotificationVersionDTO> versions;

}
